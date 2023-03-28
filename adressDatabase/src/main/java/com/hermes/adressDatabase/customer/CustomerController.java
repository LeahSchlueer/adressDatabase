package com.hermes.adressDatabase.customer;

import com.hermes.adressDatabase.user.User;
import com.hermes.adressDatabase.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@Slf4j
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    UserService userService;

    @GetMapping("/customer")
    public String getCustomerAdresses(Model model, boolean reinstance) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(email).orElse(null);
        if (user == null) {
            model.addAttribute("customers", new ArrayList<>());
        } else {
            model.addAttribute("customers", customerService.getAllCustomers(user.getLoginId()));
        }
        if (!model.containsAttribute("customer") || reinstance) {
            Customer customer = new Customer();
            customer.setLoginId(customer.getLoginId());
            model.addAttribute(customer);
        }
        return "customer";
    }

    @GetMapping("/customer/{id}")
    public String getSingleCustomerAdress(Model model, @PathVariable int id) {
        Optional<Customer> customer = customerService.findById(id);
        model.addAttribute(customer.get());
        return "singlecustomer";
    }

    @PostMapping("/customer/new")
    public String newCustomer(@Valid Customer customer, BindingResult bindingResult, Model model) {
        boolean reinstance = false;
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors();
        } else {
            reinstance = true;
            String userName = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userService.findByEmail(userName).orElse(null);
            customer.setLoginId(user.getLoginId());
            customerService.saveCustomer(customer);
        }
        return getCustomerAdresses(model, reinstance);
    }

    @Transactional
    @PostMapping("/customer/{id}/update")
    public String update(Customer customer, Model model, @PathVariable int id) {
        customer.setId(customer.getId());
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(userName).orElse(null);
        customer.setLoginId(user.getLoginId());
        customerService.saveCustomer(customer);
        return getCustomerAdresses(model, true);
    }

    @Transactional
    @PostMapping("/customer/delete/{id}")
    public String deleteCustomer(@PathVariable int id, Model model) {
        customerService.deleteById(id);
        return getCustomerAdresses(model, false);
    }

    @Transactional
    @PostMapping("/customer/deleteUser")
    public String deleteUser() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user  = userService.findByEmail(userName).orElse(null);
        customerService.deleteAll(user.getLoginId());
        userService.delete(user);
        return "start";
    }


}
