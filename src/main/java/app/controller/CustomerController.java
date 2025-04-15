package app.controller;

import app.domain.Customer;
import app.service.CustomerService;

import java.util.List;
/*
 // Контроллер - это четвёртый слой, и он должен взаимодействовать с третьим.
// Контроллер никогда не должен напрямую обращаться ко второму слою, т.е. репозиторию.
// Поэтому в контроллере мы определяем поле, которое будет содержать объект
// сервиса, чтобы контроллер в своём коде мог к нему обращаться и вызывать его методы.

 */
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    public Customer save(String name){
        Customer customer = new Customer(name);
        return customerService.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerService.getAllActiveCustomers();
    }

    public Customer getCustomerById(Long id){
        return customerService.getActiveCustomerById(id);
    }

    public void customerUpdate(Long id, String newName){
        Customer customer = new Customer(id, newName);
        customerService.update(customer);
    }

    public void deleteCustomerById(Long id){
        customerService.deleteById(id);
    }

    public void deleteCustomerByName(String name){
        customerService.deleteByName(name);
    }

    public void restoreCustomerById(Long id){
        customerService.restoreById(id);
    }
    public long getCustomerCount(){
        return customerService.getActiveCustomersNumber();
    }

    public double getCustomersCartTotalCostById(Long id){
        return customerService.getCustomersCartTotalCostById(id);
    }

    public double getAveragePriceOfCustomersCart(Long id){
        return customerService.getAveragePriceOfCustomersCart(id);
    }

    public void addProductToCustomersCart(Long customerId, Long productId){
        customerService.addProductToCustomersCart(customerId,productId);
    }

    public void removeProductFromCustomersCart(Long customerId, Long productId){
        customerService.removeProductFromCustomersCart(customerId,productId);
    }

    public void clearCustomersCart(Long customerId){
        customerService.clearCustomersCart(customerId);
    }

}
