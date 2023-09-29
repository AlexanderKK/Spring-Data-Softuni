import entities.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import utils.Logs;

import java.util.stream.Stream;

public class EmployeesWithSalaryOver50000 {

    public static void main(String[] args) {
        Logs.hide();

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery(
                "SELECT e FROM Employee e WHERE e.salary > 50000", Stream.class);

        Stream<Employee> resultStream = query.getResultStream();

        resultStream.map(Employee::getFirstName).forEach(System.out::println);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
