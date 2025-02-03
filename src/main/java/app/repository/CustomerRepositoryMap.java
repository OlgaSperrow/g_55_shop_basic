package app.repository;

import app.domain.Customer;

import java.util.*;

public class CustomerRepositoryMap implements CustomerRepositiry{
    private Map<Long,Customer> dataBase =new HashMap<>();
    private long currentId =0;

    @Override
    public Customer save(Customer customer) {
         customer.setId(++currentId);
         dataBase.put(currentId,customer);
         return customer;
    }

    @Override
    public Customer findById(Long id) {
        return dataBase.get(id);
    }

    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(dataBase.values());
    }

    @Override
    public void update(Customer customer) {
        Long id =customer.getId();
        String newName = customer.getName();
        Customer existedCustomer =findById(id);

        if(existedCustomer != null){
            existedCustomer.setName(newName);
        }

    }

    @Override
    public void deleteById(Long id) {

        dataBase.remove(id);

    }

    public static void main(String[] args) {
       CustomerRepositiry repository = new CustomerRepositoryMap();
   }
}
