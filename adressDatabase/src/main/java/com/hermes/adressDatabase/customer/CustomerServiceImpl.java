package com.hermes.adressDatabase.customer;

import com.hermes.adressDatabase.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public List<Customer> getAllCustomers(Long id) {
        List<Customer> allCustomers = new ArrayList<>();
        customerRepository.findByLoginId(id).forEach(allCustomers ::add);
        return allCustomers;
    }

    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void deleteById(int id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Optional<Customer> findById(int id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer;
    }

    @Override
    public void deleteAll(Long id) {
        customerRepository.deleteAllByLoginId(id);
    }


}
