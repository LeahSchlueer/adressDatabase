package com.hermes.adressDatabase.customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<Customer> getAllCustomers(Long id);

    void saveCustomer(Customer customer);

    void deleteById(int id);

    Optional<Customer> findById(int id);

    void deleteAll(Long id);

}
