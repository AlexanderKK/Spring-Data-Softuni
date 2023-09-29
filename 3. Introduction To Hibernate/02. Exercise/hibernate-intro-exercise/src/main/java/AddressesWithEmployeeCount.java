import entities.Address;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import utils.Logs;

import java.util.List;
import java.util.stream.Collectors;

public class AddressesWithEmployeeCount {

    private static final String PRINT_ADDRESSES_FORMAT = "%s, %s - %d employees%n";

    public static void main(String[] args) {
        Logs.hide();

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        List<Address> resultList = entityManager.createQuery(
                "SELECT a FROM Address a", Address.class)
                .getResultList();

        List<Address> sortedAddresses = resultList.stream()
                .sorted((f, s) -> s.getEmployees().size() - f.getEmployees().size())
                .limit(10)
                .collect(Collectors.toList());

        sortedAddresses.forEach(a -> {
            System.out.printf(PRINT_ADDRESSES_FORMAT,
                    a.getText(),
                    capitalize(a.getTown().getName()),
                    a.getEmployees().size());
        });

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private static String capitalize(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }

}
