package com.conexion.sqlite.Services;

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
    public Optional<Customer> getCustomerById(int id) {
        Optional<Customer> customer = customerDBA.findById(id);

        if (customer.isPresent()) {
            return customer;
        }else {
            return Optional.empty();
        }

    }
}
