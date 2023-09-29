import entities.Address;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import utils.Logs;

import java.util.Scanner;

public class AddAddressUpdateEmployee {

    public static void main(String[] args) {
        Logs.hide();

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Address addressToEmployee = new Address();
        addressToEmployee.setText("Vitoshka 15");

        System.out.print("Enter last name of employee: ");
        String employeeLastName = new Scanner(System.in).nextLine();

        entityManager.persist(addressToEmployee);

        int updateCount = entityManager.createQuery(
                "UPDATE Employee e SET e.address = :newAddr WHERE e.lastName = :ln")
                .setParameter("newAddr", addressToEmployee)
                .setParameter("ln", employeeLastName)
                .executeUpdate();

        System.out.println(updateCount);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
