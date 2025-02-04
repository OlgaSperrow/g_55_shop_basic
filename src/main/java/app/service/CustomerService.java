package app.service;

import app.domain.Customer;

import java.util.List;

public interface CustomerService {
    Customer save(Customer customer);
    List<Customer> getAllActiveCustomer();
    Customer getActiveCustomerById(Long id);
    void update(Customer customer);
    void deleteById(Long id);
    void deleteByName(String name);
    void restoreById(Long id);
    int getActiveCustomerNumber();
    double getCustomerCartTotalCostById(Long customerId);
    double getAveragePriceOfCustomersCart(Long customerId);
    void addProductToCustomersCart(Long customerId, Long productId);
    void removeProductFromCustomerCart(Long customerId, Long productId);
    void clearCustomerCart(Long customerId);

}
