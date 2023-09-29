import entities.Account;
import entities.Student;
import entities.User;
import orm.Connector;
import orm.EntityManager;
import orm.annotations.Entity;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException, NoSuchFieldException {
//        long beforeConnection = System.currentTimeMillis();
//
//        Connector.createConnection("root", "impeachment*", "soft_uni");
//
//        long afterConnection = System.currentTimeMillis();
//
//        System.out.println("Time is " + (afterConnection - beforeConnection));
//
//        long beforeConnection2 = System.currentTimeMillis();
//
//        Connector.createConnection("root", "impeachment*", "soft_uni");
//
//        long afterConnection2 = System.currentTimeMillis();
//
//        System.out.println("Time is " + (afterConnection2 - beforeConnection2));
//
//        Connection connection = Connector.getConnection();
//
//        System.out.println(connection.isValid(0) ? "Connection successful" : "Error while connecting to the SQL Server");
//
//        EntityManager<User> userManager = new EntityManager<>(connection);
////
////        User user = new User("First", 28, LocalDate.now());
////
////        userManager.persist(user);
////
//        EntityManager<Student> studentManager = new EntityManager<>(connection);
//        Student student = new Student("name");
//
//        System.out.println(studentManager.persist(student));
//
//        User first = userManager.findFirst(User.class);
//
//        System.out.println(userManager.persist(first));
//
//        System.out.println(first.getId() + " " + first.getUsername());
//
//        Student first1 = studentManager.findFirst(Student.class, "`name` = 'name1'");
//
//        System.out.println(first1.getId() + " " + first1.getName());
//
//        userManager
//                .find(User.class, "age > 18 AND registration_date > '2023-09-01'")
//                .forEach(System.out::println);
//
//        userManager
//                .find(User.class)
//                .forEach(System.out::println);
//
//        System.out.println();
//
//        first.setUsername("Updated");
//        first.setAge(25);
//        userManager.update(first);
//
//        User updatedUser = userManager.findFirst(User.class);
//        System.out.println(updatedUser);
//
//        updatedUser = userManager.findFirst(User.class, "user_name LIKE 'updated'");
//        System.out.println(updatedUser);
//
//        updatedUser = userManager.findFirst(User.class, "id = 1");
//        System.out.println(updatedUser);

        Connection connection = Connector.getConnection();
        final EntityManager<Account> accountManager = new EntityManager<>(connection);

        Account account = new Account("Alex", LocalDate.now(), 100);
//        accountManager.doCreate(Account.class);
//
//        accountManager.persist(accountManager.findFirst(Account.class));

        accountManager.doAlter(account);

        final EntityManager<User> userManager = new EntityManager<>(connection);
        userManager.doCreate(User.class);

        User user = new User("Mike", 20, LocalDate.now());
        userManager.persist(user);

        Iterable<User> users = userManager.find(User.class);

        User firstUser = users.iterator().next();
        userManager.doDelete(firstUser);
    }

}
