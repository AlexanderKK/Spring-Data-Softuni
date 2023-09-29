import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("football-betting");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        System.out.println("entityManager.isOpen() = " + entityManager.isOpen());

        entityManager.getTransaction().begin();

        // TODO

        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
