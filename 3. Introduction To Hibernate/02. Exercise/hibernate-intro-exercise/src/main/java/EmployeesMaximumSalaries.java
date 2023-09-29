import entities.Department;
import entities.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import utils.Logs;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

public class EmployeesMaximumSalaries {

    public static void main(String[] args) {
        Logs.hide();

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Map<String, BigDecimal> departmentsMaxSalaries = new LinkedHashMap<>();

        entityManager.createQuery("FROM Department d", Department.class)
                        .getResultList()
                        .forEach(d -> {
                            BigDecimal maxSalary = d.getEmployees().stream()
                                    .map(Employee::getSalary)
                                    .max(Comparator.naturalOrder())
                                    .orElse(BigDecimal.valueOf(0));

                            if(maxSalary.compareTo(BigDecimal.valueOf(30000)) <= 0 ||
                               maxSalary.compareTo(BigDecimal.valueOf(70000)) >= 0) {
                                departmentsMaxSalaries.put(d.getName(), maxSalary);
                            }
                        });

        departmentsMaxSalaries.forEach(
                (depName, maxSalary) -> System.out.println(depName + " " + maxSalary));

        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
