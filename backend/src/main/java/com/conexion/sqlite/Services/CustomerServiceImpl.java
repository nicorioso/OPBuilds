package com.conexion.sqlite.Services;More actions

import com.conexion.sqlite.Domain.Customer;
import com.conexion.sqlite.Repository.CustomerDBA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerDBA customerDBA;

    @Override
    public List<Customer> getCustomer() {
        return customerDBA.findAll();
    }

    @Override
    public Optional<Customer> getCustomerById(Integer id) {
        Optional<Customer> customer = customerDBA.findById(id);
        if (customer.isPresent()) {
            return customer;
        }else {
            return Optional.empty();
        }
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerDBA.save(customer);
    }

    @Override
    public Customer updateCustomer(Integer id, Customer customer) {
        return customerDBA.findById(id).map(existing -> {
            existing.setName(customer.getName());
            existing.setEmail(customer.getEmail());
            existing.setPassword(customer.getPassword());
            return customerDBA.save(existing);
        }).orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + id));
    }

    @Override
    public Customer patchCustomer(Integer id, Customer customer) {
        Customer customerExist = customerDBA.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe el producto con el id: " + id));
        if (customer.getName() != null) {
            customerExist.setName(customer.getName());
        }
        if (customer.getEmail() != null) {
            customerExist.setEmail(customer.getEmail());
        }
        if (customer.getPassword() != null) {
            customerExist.setPassword(customer.getPassword());
        }
        return customerDBA.save(customerExist);
    }Add commentMore actions

    public void deleteCustomer(Integer id) {
        customerDBA.deleteById(id);
    }
}