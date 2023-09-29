import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import utils.Logs;

import java.util.Scanner;

public class ContainsEmployee {

    public static void main(String[] args) {
        Logs.hide();

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        System.out.print("Enter name: ");

        String[] name = new Scanner(System.in).nextLine().split(" ");

        String firstName = name[0];
        String lastName = name[1];

        Long matchesCount = entityManager.createQuery(
                        "SELECT COUNT(*) FROM Employee e WHERE e.firstName = :fn AND e.lastName = :ln", Long.class)
                .setParameter("fn", firstName)
                .setParameter("ln", lastName)
                .getSingleResult();

        System.out.println(matchesCount == 0 ? "No" : "Yes");

        entityManager.close();
    }

}
