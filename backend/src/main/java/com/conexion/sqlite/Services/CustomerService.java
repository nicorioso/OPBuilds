package com.conexion.sqlite.Services;

import com.conexion.sqlite.Domain.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    public List<Customer> getCustomer();
    Optional<Customer> getCustomerById(Integer id);
    public Customer createCustomer(Customer customer);
}
