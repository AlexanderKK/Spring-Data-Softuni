import entities.Town;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import utils.Logs;

import java.util.List;

public class ChangeCasing {

    private static final String UPDATE_TOWNS_WHERE_LENGTH_LESS_THAN_OR_EQUALS_5 =
            "UPDATE Town t SET t.name = UPPER(t.name) WHERE CHAR_LENGTH(t.name) <= 5";

    public static void main(String[] args) {
//        Jakarta Persistence API (Hibernate)
//
//        Configuration configuration = new Configuration();
//        configuration.configure();
//
//        SessionFactory sessionFactory = configuration.buildSessionFactory();
//        Session session = sessionFactory.openSession();
//
//        session.beginTransaction();
//
//        session.getTransaction().commit();
//        session.close();


//        Java Persistence API (javax)
//
        Logs.hide();

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = entityManagerFactory.createEntityManager();


        entityManager.getTransaction().begin();

//        Operations With Database
//

        //SQL
//        entityManager.createQuery(UPDATE_TOWNS_WHERE_LENGTH_LESS_THAN_OR_EQUALS_5)
//                .executeUpdate();

//        HQL
        final Query towns = entityManager.createQuery("SELECT t FROM Town t", Town.class);
        final List<Town> resultList = towns.getResultList();

        for (Town town : resultList) {
            String townName = town.getName();
            if(townName.length() <= 5) {
                town.setName(townName.toUpperCase());

                entityManager.persist(town);
            }
        }
        resultList.forEach(System.out::println);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
