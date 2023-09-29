import entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import relations.PlateNumber;
import relations.Truck;
import utils.Log;

import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Log.hide();

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("hibernate-inheritance");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

//        Vehicle car = new Car("Ford Focus", "Petrol", 5);
//        Vehicle bike = new Bike();
//        Vehicle plane = new Plane("Airbus", "Kerosene", 200);
//
//        entityManager.persist(car);
//        entityManager.persist(bike);
//        entityManager.persist(plane);

//        Car carFromDB = entityManager.find(Car.class, 1);
//        System.out.println(carFromDB.getSeats() + " " + carFromDB.getModel());

//        PlateNumber plateNumber1 = new PlateNumber("123");
//        PlateNumber plateNumber2 = new PlateNumber("1234");
//        Truck truck1 = new Truck(plateNumber1);
//        Truck truck2 = new Truck(plateNumber2);
//
//        entityManager.persist(plateNumber1);
//        entityManager.persist(plateNumber2);
//        entityManager.persist(truck1);
//        entityManager.persist(truck2);

        Article article = new Article("New Article");
        User author = new User("Peter");
        article.setAuthor(author);

        author.addArticle(article);
        author.addArticle(article);
        author.addArticle(article);

//        author.getArticles().stream().map(Article::getText).forEach(System.out::println);

        entityManager.persist(article);
        entityManager.persist(author);

        StringBuilder sb = new StringBuilder();
        entityManager.createQuery("FROM User a", User.class)
                .getResultStream()
                .forEach(u -> {
                    sb.append(u.getName()).append(" ");
                    sb.append(u.getArticles().stream()
                            .map(Article::getText)
                            .collect(Collectors.joining(", ")));
                    sb.append(System.lineSeparator());
                });

        String result = sb.toString().trim();
        System.out.println(result);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
