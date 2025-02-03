package app.service;

import app.domain.Product;
import app.exceptions.ProductNotFoundException;
import app.exceptions.ProductSaveException;
import app.exceptions.ProductUpdateException;
import app.repository.ProductRepository;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository repositiry;

    public ProductServiceImpl(ProductRepository repositiry) {
        this.repositiry = repositiry;
    }

    @Override
    public Product save(Product product) {
        if (product == null) {
            throw new ProductSaveException("Product can not be null");
        }

        String title = product.getTitle();
        if (title == null || title.length() < 3) {
            throw new ProductSaveException("Product title should be at least 3 characters long");
        }

        if (product.getPrice() < 0) {
            throw new ProductSaveException("Product price cannot be negative");
        }

        product.setActive(true);

        return repositiry.save(product);
    }

    @Override
    public List<Product> getAllActiveProducts() {
        return repositiry.findAll()
                .stream()
                .filter(x -> x.isActive())
                .toList();
    }

    @Override
    public Product getById(Long id) {
        Product product = repositiry.findById(id);
        if (product == null || !product.isActive()) {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }
        return product;
    }

    @Override
    public void update(Product product) {
        if (product == null) {
            throw new ProductUpdateException("Product can not be null");
        }

        Long id = product.getId();
        if (id == null || id < 1) {
            throw new ProductUpdateException("Product should be positiv");
        }

        if (product.getPrice() < 0) {
            throw new ProductUpdateException("Product price cannot be negative");
        }
        repositiry.update(product);

    }

    @Override
    public void deleteById(Long id) {
        getById(id).setActive(false);

    }

    @Override
    public void deleteByTitle(String title) {
        Product product = getAllActiveProducts()
                .stream()
                .filter(x -> x.getTitle().equals(title))
                .findFirst()
                .orElse(null);

        if (product == null) {
            throw new ProductNotFoundException("Product with title " + title + " not found");
        }
        product.setActive(false);

    }

    @Override
    public void restoreById(Long id) {
        Product product = repositiry.findById(id);
        if(product==null){
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }
        product.setActive(true);
    }

    @Override
    public long getActiveProductsTotalCount() {
        return getAllActiveProducts().size();
    }

    @Override
    public double getActiveProductsTotalCost() {
        return getAllActiveProducts()
                .stream()
                .mapToDouble(x->x.getPrice())
                .sum();
    }

    @Override
    public double getActiveProductsAveragePrice() {

        long productCount =getActiveProductsTotalCount();
        if(productCount==0){
            return 0;
        }
        return getActiveProductsTotalCost()/productCount;
    }
}
