package app.domain;

import java.util.Objects;

public class Product {
    private Long id;
    private String title;
    private double price;
    private boolean active;

    public Product(double price, String title) {
        this.price = price;
        this.title = title;
    }

    public Product(Long id, double price) {
        this.id = id;
        this.price = price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isActive() {
        return active;
    }

    public double getPrice() {
        return price;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(price, product.price) == 0 && active == product.active && Objects.equals(id, product.id) && Objects.equals(title, product.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, price, active);
    }

    @Override
    public String toString() {
        return String.format("Product: id -%d, title - %s, price - %.2f, active - %s",
                id, title, price, active ? "yes" : "no");
    }
}
