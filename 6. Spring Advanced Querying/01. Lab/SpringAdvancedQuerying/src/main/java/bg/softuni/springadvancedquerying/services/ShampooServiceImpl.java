package bg.softuni.springadvancedquerying.services;

import bg.softuni.springadvancedquerying.entities.Shampoo;
import bg.softuni.springadvancedquerying.entities.Size;
import bg.softuni.springadvancedquerying.repositories.ShampooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Service
public class ShampooServiceImpl implements ShampooService {

    private ShampooRepository shampooRepository;

    @Autowired
    public ShampooServiceImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }

    @Override
    public Set<Shampoo> findAllBySizeOrderById(Size size) {
        return this.shampooRepository.findAllBySizeOrderById(size)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Set<Shampoo> findAllBySizeOrLabelIdOrderByPrice(Size sizeOfShampoo, long labelId) {
        return shampooRepository.findAllBySizeOrLabelIdOrderByPrice(sizeOfShampoo, labelId)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Set<Shampoo> findAllByPriceGreaterThanOrderByPriceDesc(BigDecimal price) {
        return this.shampooRepository.findAllByPriceGreaterThanOrderByPriceDesc(price)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public long countByPriceLessThan(BigDecimal price) {
        return shampooRepository.countByPriceLessThan(price)
                .orElse(0L);
    }

    @Override
    public Set<Shampoo> findAllByIngredients(Set<String> ingredients) {
        return shampooRepository.findAllByIngredients(ingredients)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Shampoo> findAllByIngredientsCountLessThan(int ingredientsCount) {
        return this.shampooRepository.findAllByIngredientsCountLessThan(ingredientsCount)
                .orElseThrow(NoSuchElementException::new);
    }

}
