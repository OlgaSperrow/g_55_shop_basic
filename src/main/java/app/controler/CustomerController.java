package app.controler;

import app.domain.Customer;
import app.service.CustomerService;

import java.util.List;

public class CustomerController {
    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    public Customer save(String name){
        Customer customer =new Customer(name);
        return customerService.save(customer);
    }

    public List<Customer> getAllActiveCustomer(){
        return customerService.getAllActiveCustomer();
    }

    public Customer getCustomerById(Long id){
        return customerService.getActiveCustomerById(id);
    }

    public void  customerUpdate(Long id, String newName ){
        Customer customer = new Customer(id,newName);
        customerService.update(customer);
    }

    public void deleteCustomerById(Long id){
        customerService.deleteById(id);
    }

    public void deleteByName(String name){
        customerService.deleteByName(name);
    }

    public void restoreCustomerById(Long id){
        customerService.restoreById(id);
    }

    public int getCustomerNumber(){
        return customerService.getActiveCustomerNumber();
    }

    public double getCustomerCartTotalCostById(Long customerId){
        return customerService.getCustomerCartTotalCostById(customerId);
    }

    public double getAveragePriceOfCustomersCart(Long customerId){
        return customerService.getAveragePriceOfCustomersCart(customerId);
    }

    public void addProductToCustomersCart(Long customerId, Long productId){
        customerService.addProductToCustomersCart(customerId,productId);
    }

    public void removeProductFromCustomerCart(Long customerId, Long productId){
        customerService.removeProductFromCustomerCart(customerId,productId);
    }

    public void clearCustomerCart(Long customerId){
        customerService.clearCustomerCart(customerId);
    }

}
