import entities.Address;
import entities.Employee;
import entities.Town;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import utils.Logs;

import java.util.List;
import java.util.Scanner;

public class RemoveAddressesAndTownByTownName {

    public static void main(String[] args) {
        Logs.hide();

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        System.out.print("Enter town: ");
        String townName = new Scanner(System.in).nextLine();

        //Get Town
        Query queryGetTown = entityManager.createQuery("FROM Town t WHERE t.name = :tn", Town.class)
                .setParameter("tn", townName);

        Town townToDelete = (Town) queryGetTown.getSingleResult();

        //Get Addresses by town
        List<Address> addressesToDelete = entityManager.createQuery(
                "FROM Address a WHERE a.town.id = :tnId", Address.class)
                .setParameter("tnId", townToDelete.getId())
                .getResultList();

        int removedAddressesCount = addressesToDelete.size();

        // Find employees with addresses to delete
        List<Employee> employeesWithAddressesToDelete = entityManager.createQuery(
                        "FROM Employee e WHERE e.address IN :addresses", Employee.class)
                .setParameter("addresses", addressesToDelete)
                .getResultList();

        // Remove the reference to the address from employees
        employeesWithAddressesToDelete.forEach(e -> e.setAddress(null));

        // Remove addresses
        addressesToDelete.forEach(entityManager::remove);

        // Remove the town
        entityManager.remove(townToDelete);

        System.out.printf("%d %s in %s deleted",
                removedAddressesCount,
                removedAddressesCount == 1 ? "address" : "addresses",
                townToDelete.getName());

        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
