package bg.softuni;

import bg.softuni.entities.sales.Product;
import bg.softuni.entities.sales.Sale;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashSet;
import java.util.logging.Level;

import java.util.logging.*;

public class Main {

    public static void main(String[] args) {
        Logger.getLogger("org.hibernate").setLevel(Level.WARNING);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hibernate-inheritance-exercise");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

//        Product product = new Product();
//        product.setName("Milk");
//        product.setPrice(BigDecimal.valueOf(20));
//
//        Sale sale1 = new Sale();
//        sale1.setDate(Date.valueOf("2020-09-20"));
//
//        Sale sale2 = new Sale();
//        sale2.setDate(Date.valueOf("2020-09-21"));
//
////        product.getSales().add(sale);
//        sale1.setProduct(product);
//        sale2.setProduct(product);
//
//        entityManager.persist(product);
//        entityManager.persist(sale1);
//        entityManager.persist(sale2);

//        Product productFromDB = entityManager.find(Product.class, 11);
//        productFromDB.getSales().stream().map(Sale::getId).forEach(System.out::println);

        entityManager.getTransaction().commit();
        entityManager.clear();
    }

}
