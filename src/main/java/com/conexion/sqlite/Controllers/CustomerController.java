package com.conexion.sqlite.Controllers;

import com.conexion.sqlite.Domain.Customer;
import com.conexion.sqlite.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/Customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<?> getAllCustomers() {
        return ResponseEntity.ok(customerService.getCustomer());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Integer id) {
        Optional<Customer> customerOpt = customerService.getCustomerById(id);

        return customerOpt
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> postCustomer(@RequestBody Customer customer) {
        Customer nuevo = customerService.createCustomer(customer);
        URI location = URI.create("/Customer/" + nuevo.getCustomer_id());
        return ResponseEntity.created(location).body(nuevo);
    }

}
