package app.repository;

import app.domain.Product;

import java.util.List;

public class ProductRepositoryMap implements ProductRepository {

    @Override
    public Product save(Product product) {
        return null;
    }

    @Override
    public Product findById(Long id) {
        return null;
    }

    @Override
    public List<Product> findAll() {
        return List.of();
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
