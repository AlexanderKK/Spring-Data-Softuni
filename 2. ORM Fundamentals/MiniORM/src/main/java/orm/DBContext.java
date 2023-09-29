package orm;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface DBContext<E> {

    void doCreate(Class<E> entityClass) throws SQLException;

    void doAlter(E entity) throws SQLException;

    void doDelete(E entity) throws IllegalAccessException, SQLException;

    boolean persist(E entity) throws SQLException, IllegalAccessException;

    boolean update(E entity) throws IllegalAccessException, SQLException, NoSuchFieldException;

    Iterable<E> find(Class<E> entityType) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    Iterable<E> find(Class<E> entityType, String where) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    E findFirst(Class<E> entityType) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    E findFirst(Class<E> entityType, String where) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

}
