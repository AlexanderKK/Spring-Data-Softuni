package bg.softuni.springadvancedquerying.repositories;

import bg.softuni.springadvancedquerying.entities.Shampoo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomShampooRepositoryImpl implements CustomShampooRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void create(Shampoo shampoo) {
        entityManager.persist(shampoo);
    }

}
