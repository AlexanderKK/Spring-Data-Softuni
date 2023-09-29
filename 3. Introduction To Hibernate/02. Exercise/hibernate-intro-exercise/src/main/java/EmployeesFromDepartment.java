import entities.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import utils.Logs;

public class EmployeesFromDepartment {

    private static final String PRINT_EMPLOYEE_DEPARTMENT_FORMAT = "%s %s from %s - $%.2f%n";

    public static void main(String[] args) {
        Logs.hide();

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        final String department = "Research and Development";

        entityManager.createQuery(
                "SELECT e FROM Employee AS e " +
                        "WHERE e.department.name LIKE ?1 " +
                        "ORDER BY e.salary, e.id", Employee.class)
                .setParameter(1, department)
                .getResultList()
                .forEach(e -> System.out.printf(
                        PRINT_EMPLOYEE_DEPARTMENT_FORMAT,
                        e.getFirstName(),
                        e.getLastName(),
                        e.getDepartment().getName(),
                        e.getSalary()));

        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
