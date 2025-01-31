package app.repository;

import app.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryList implements ProductRepository {
    private List<Product> dataBase = new ArrayList<>();
    private long currentId = 0;

    @Override
    public Product save(Product product) {

        product.setId(++currentId);
        dataBase.add(product);
        return product;
    }

    @Override
    public Product findById(Long id) {
        return dataBase
                .stream()
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Product> findAll() {
        return dataBase;
    }

    @Override
    public void update(Product product) {
        Long id = product.getId();
        double newPrice = product.getPrice();
        Product excistedProduct = findById(id);
        if (excistedProduct != null) {
            excistedProduct.setPrice(newPrice);
        }


    }

    @Override
    public void deleteById(Long id) {
        dataBase.removeIf(x->x.getId().equals(id));

    }
}
