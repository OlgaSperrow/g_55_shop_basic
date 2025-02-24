package app.repository;

import app.domain.Product;

import java.util.List;

public interface ProductRepository {

    //CRUD- create, read, update, delete

    Product save (Product product);
    Product findById(Long id);
    List<Product> findAll();
    void update (Product product);
    void deleteById(Long id);
}
