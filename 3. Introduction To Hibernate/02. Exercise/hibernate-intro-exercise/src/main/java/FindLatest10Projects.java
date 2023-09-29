import entities.Project;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import utils.Logs;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class FindLatest10Projects {

    public static void main(String[] args) {
        Logs.hide();

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        StringBuilder sBuilder = new StringBuilder();

        entityManager
                .createQuery("FROM Project p ORDER BY p.startDate DESC, p.name ASC", Project.class)
                .setMaxResults(10)
                .getResultList()
                .forEach(p -> {
                    sBuilder.append("Project name: ").append(p.getName())
                            .append(System.lineSeparator());
                    sBuilder.append("\t\tProject Description: ").append(p.getDescription())
                            .append(System.lineSeparator());
                    sBuilder.append("\t\tProject Start Date: ").append(
                            p.getStartDate().format(
                                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.getDefault()))
                            ).append(System.lineSeparator());
                    sBuilder.append("\t\tProject End Date: ").append(p.getEndDate())
                            .append(System.lineSeparator());
                });

        System.out.println(sBuilder);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
