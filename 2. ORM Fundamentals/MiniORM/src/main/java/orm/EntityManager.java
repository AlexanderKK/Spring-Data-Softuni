package orm;

import orm.annotations.Column;
import orm.annotations.Entity;
import orm.annotations.Id;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class EntityManager<E> implements DBContext<E> {

    private final Connection connection;
    private static final String DEFINE_COLUMN_FORMAT = "%s %s";
    private static final String CREATE_QUERY_FORMAT = "CREATE TABLE IF NOT EXISTS %s (id INT PRIMARY KEY AUTO_INCREMENT, %s)";
    private static final String INT = "INT";
    private static final String DATE = "DATE";
    private static final String VARCHAR = "VARCHAR(45)";
    private static final String GET_ALL_COLUMN_NAMES_BY_TABLE_NAME =
            "SELECT `COLUMN_NAME` FROM `INFORMATION_SCHEMA`.`COLUMNS` WHERE `TABLE_SCHEMA` = 'custom-orm' AND `COLUMN_NAME` != 'id' AND `TABLE_NAME` = ?";
    private static final String ADD_COLUMN_FORMAT = "ADD COLUMN %s %s";
    private static final String ALTER_TABLE_FORMAT = "ALTER TABLE %s %s";
    private static final String DELETE_QUERY_FORMAT = "DELETE FROM %s WHERE %s = %s";

    public EntityManager(Connection connection) {
        this.connection = connection;
    }

    private E createEntity(Class<E> entityType, ResultSet resultSet) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if(!resultSet.next()) {
            return null;
        }

        //Create empty object
        E entity = entityType.getDeclaredConstructor().newInstance();

        //Fill entity with all values
        Field[] declaredFields = entityType.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if(!declaredField.isAnnotationPresent(Column.class) &&
                    !declaredField.isAnnotationPresent(Id.class)) {
                continue;
            }

            Column annotation = declaredField.getAnnotation(Column.class);

            //Set fieldName                       ? 'id'                    : 'user_name or age or ...'
            String fieldName = annotation == null ? declaredField.getName() : annotation.name();
            String value = resultSet.getString(fieldName);

            //Fill each field
            entity = this.fillData(entity, declaredField, value);
        }

        return entity;
    }

    private E fillData(E entity, Field field, String value) throws IllegalAccessException {
        field.setAccessible(true);

        if(field.getType() == Long.class || field.getType() == long.class) {
            field.setLong(entity, Long.parseLong(value));
        } else if(field.getType() == Integer.class || field.getType() == int.class) {
            field.set(entity, Integer.parseInt(value));
        } else if(field.getType() == LocalDate.class) {
            field.set(entity, LocalDate.parse(value));
        } else if(field.getType() == String.class) {
            field.set(entity, value);
        } else {
            throw new ORMException("Unsupported type " + field.getType());
        }

        return entity;
    }

    private Field getId(Class<?> entity) {
        return Arrays.stream(entity.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow(() -> new ORMException("Entity does not have primary key"));
    }

    private boolean doInsert(E entity) throws IllegalAccessException, SQLException {
        String tableName = this.getTableName(entity.getClass());
        String fieldList = this.getDBFieldNamesWithoutIdentity(entity.getClass());
        String valueList = this.getValuesWithoutIdentity(entity);

        String sql = String.format("INSERT INTO %s (%s) VALUES(%s)",
                tableName, fieldList, valueList);

        return this.connection.prepareStatement(sql).execute();
    }

    private boolean doUpdate(E entity, Field primaryKey) throws IllegalAccessException, SQLException {
        String fieldList = this.getDBFieldNamesWithoutIdentity(entity.getClass());
        String valueList = this.getValuesWithoutIdentity(entity);

        String tableName = this.getTableName(entity.getClass());
        StringBuilder setValues = getSetValues(fieldList, valueList);
        String primaryColumn = primaryKey.getName();
        Object primaryValue = primaryKey.get(entity);

        String sql = String.format("UPDATE `%s` SET %s WHERE `%s` = %s",
                tableName, setValues, primaryColumn, primaryValue);

        return this.connection.prepareStatement(sql).execute();
    }

    private static StringBuilder getSetValues(String fieldList, String valueList) {
        StringBuilder setValuesStr = new StringBuilder();

        String[] fields = fieldList.split(", ");
        String[] values = valueList.split(", ");

        for (int i = 0; i < fields.length; i++) {
            setValuesStr
                    .append(fields[i]).append(" = ")
                    .append(values[i]).append(", ");
        }

        setValuesStr = new StringBuilder(setValuesStr.substring(0, setValuesStr.length() - 2));

        return setValuesStr;
    }

    private String getTableName(Class<?> clazz) {
        Entity annotation = clazz.getAnnotation(Entity.class);

        if(annotation == null) {
            throw new ORMException("Provided class does not have Entity annotation");
        }

        return annotation.name();
    }

    private List<Field> getDBFieldsWithoutIdentity(Class<?> entityClass) {
        return Arrays.stream(entityClass.getDeclaredFields())
                .filter(f -> f.getAnnotation(Column.class) != null)
                .collect(Collectors.toList());
    }

    private String getDBFieldNamesWithoutIdentity(Class<?> entityClass) {
        return getDBFieldsWithoutIdentity(entityClass).stream()
                .map(f -> "`" + f.getAnnotation(Column.class).name() + "`")
                .collect(Collectors.joining(", "));
    }

    private String getValuesWithoutIdentity(E entity) throws IllegalAccessException {
        Field[] declaredFields = entity.getClass().getDeclaredFields();
        List<String> result = new ArrayList<>();

        for (Field declaredField : declaredFields) {
            if(declaredField.getAnnotation(Column.class) == null) {
                continue;
            }

            declaredField.setAccessible(true);
            Object value = declaredField.get(entity);
            result.add("\"" + value.toString() + "\"");
        }

        return String.join(", ", result);
    }

    private String getSQLType(Class<?> type) {
        if(type == Integer.class || type == int.class) {
            return INT;
        } else if(type == LocalDate.class) {
            return DATE;
        }

        return VARCHAR;
    }

    private String getSQLColumnName(Field field) {
        return field.getAnnotationsByType(Column.class)[0].name();
    }

    private List<KeyValuePair> getAllFieldsAndTypesInKeyValuePairs(Class<?> entityClass) {
        return getDBFieldsWithoutIdentity(entityClass).stream()
                .map(f -> new KeyValuePair(getSQLColumnName(f), getSQLType(f.getType())))
                .collect(Collectors.toList());
    }

    private String getSQLColumnNamesForNewFields(E entity, String tableName) throws SQLException {
        Class<?> entityClass = entity.getClass();
        final Set<String> sqlColumnsNames = getSQLColumnNames(entityClass, tableName);
        final List<Field> allFieldsWithoutId = getDBFieldsWithoutIdentity(entityClass);

        List<String> newFieldsStmt = new ArrayList<>();
        for (Field field : allFieldsWithoutId) {
            final String sqlColumnName = getSQLColumnName(field);

            if(!sqlColumnsNames.contains(sqlColumnName)) {
                final String sqlType = getSQLType(field.getType());

                String addedStmt = String.format(ADD_COLUMN_FORMAT, sqlColumnName, sqlType);

                newFieldsStmt.add(addedStmt);
            }
        }

        return String.join(", ", newFieldsStmt);
    }

    private Set<String> getSQLColumnNames(Class<?> entityClass, String tableName) throws SQLException {
        Set<String> allFields = new HashSet<>();

        final PreparedStatement getAllFieldsStmt =
                this.connection.prepareStatement(GET_ALL_COLUMN_NAMES_BY_TABLE_NAME);

        getAllFieldsStmt.setString(1, tableName);

        final ResultSet resultSet = getAllFieldsStmt.executeQuery();

        while(resultSet.next()) {
            allFields.add(resultSet.getString(1));
        }

        return allFields;
    }

    @Override
    public void doCreate(Class<E> entityClass) throws SQLException {
        final String tableName = getTableName(entityClass);

        final List<KeyValuePair> fieldsNamesAndTypes = getAllFieldsAndTypesInKeyValuePairs(entityClass);

        final String fieldsNamesAndTypesFormat = fieldsNamesAndTypes.stream()
                .map(keyValuePair -> String.format(DEFINE_COLUMN_FORMAT,
                        keyValuePair.getKey(), keyValuePair.getValue()))
                .collect(Collectors.joining(", "));

        final String sql = String.format(CREATE_QUERY_FORMAT, tableName, fieldsNamesAndTypesFormat);

        final PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
        preparedStatement.execute();
    }

    @Override
    public void doAlter(E entity) throws SQLException {
        final String tableName = getTableName(entity.getClass());
        final String stmtNewFieldsToColumns = getSQLColumnNamesForNewFields(entity, tableName);

        final String alterQuery = String.format(ALTER_TABLE_FORMAT, tableName, stmtNewFieldsToColumns);

        final PreparedStatement preparedStatement = this.connection.prepareStatement(alterQuery);

        preparedStatement.execute();
    }

    @Override
    public void doDelete(E entity) throws IllegalAccessException, SQLException {
        final String tableName = getTableName(entity.getClass());

        final Field idField = getId(entity.getClass());
        final String idColumnName = idField.getName();
        final String idValue = getFieldValue(entity, idField).toString();

        String deleteQuery = String.format(DELETE_QUERY_FORMAT, tableName, idColumnName, idValue);

        PreparedStatement preparedStatement = this.connection.prepareStatement(deleteQuery);

        preparedStatement.execute();
    }

    private Object getFieldValue(E entity, Field field) throws IllegalAccessException {
        field.setAccessible(true);

        return field.get(entity).toString();
    }

    @Override
    public boolean persist(E entity) throws SQLException, IllegalAccessException {
        Field primaryKey = getId(entity.getClass());
        primaryKey.setAccessible(true);

        Object value = primaryKey.get(entity);
        if(value == null || Long.parseLong(String.valueOf(value)) <= 0L) {
            return doInsert(entity);
        }

        return doUpdate(entity, primaryKey);
    }

    @Override
    public boolean update(E entity) throws IllegalAccessException, SQLException {
        Field primaryKey = getId(entity.getClass());
        primaryKey.setAccessible(true);
        Object valuePk = primaryKey.get(entity);

        if(valuePk == null || (long) valuePk <= 0L) {
            doInsert(entity);
        }

        primaryKey = getId(entity.getClass());
        primaryKey.setAccessible(true);

        String tableName = getTableName(entity.getClass());
        String pkName = primaryKey.getName();
        valuePk = primaryKey.get(entity);

        StringBuilder setValuesToFields = new StringBuilder("SET ");

        for (Field field : entity.getClass().getDeclaredFields()) {
            if(field.isAnnotationPresent(Column.class)) {
                field.setAccessible(true);
                Object value = field.get(entity);

                String columnName = field.getAnnotation(Column.class).name();
                String columnValue = value.toString();

                setValuesToFields.append(String.format("%s = '%s', ", columnName, columnValue));
            }
        }

        String setValues = setValuesToFields.substring(0, setValuesToFields.length() - 2);

        String sql = String.format("UPDATE %s %s WHERE %s = %s",
                tableName, setValues, pkName, valuePk);

        return this.connection.prepareStatement(sql).execute();
    }

    @Override
    public Iterable<E> find(Class<E> entityType) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return find(entityType, " ");
    }

    @Override
    public Iterable<E> find(Class<E> entityType, String where) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String tableName = getTableName(entityType);

        String sql = String.format("SELECT * FROM %s %s",
                tableName,
                where == null || where.trim().equals("") ? "" : "WHERE " + where);

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<E> result = new ArrayList<>();

        E entity = this.createEntity(entityType, resultSet);
        while(entity != null) {
            result.add(entity);
            entity = this.createEntity(entityType, resultSet);
        }

        return result;
    }

    @Override
    public E findFirst(Class<E> entityType) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return findFirst(entityType, null);
    }

    @Override
    public E findFirst(Class<E> entityType, String where) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String tableName = getTableName(entityType);

        String sql = String.format("SELECT * FROM %s %s LIMIT 1",
                tableName,
                where == null || where.trim().equals("") ? "" : "WHERE " + where);

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        return this.createEntity(entityType, resultSet);
    }

}
