package app.controler;

import app.domain.Product;
import app.service.ProductService;

import java.util.List;

public class ProductController {
    ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    public Product save(String title, double price){
        Product product =new Product(price, title);
        return service.save(product);
    }

    public List<Product> getAll(){
        return service.getAllActiveProducts();
    }

    public Product getById(Long id){
        return service.getById(id);
    }

    public void update(Long id, double newPrice){
        Product product = new Product (id,newPrice);
        service.update(product);
    }

    public void deleteByTitle(String title){
        service.deleteByTitle(title);
    }

    public void restoreById (Long id){
        service.restoreById(id);
    }
    public long getProductsCount(){
        return service.getActiveProductsTotalCount();
    }

    public double getProductTotalCost(){
        return service.getActiveProductsTotalCost()
    }


    public double getProductAveragePrice(){
        return service.getActiveProductsAveragePrice();
    }
}
