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

    @PutMapping("/{id}")Add commentMore actions
    public ResponseEntity<?> updateCustomer(@PathVariable Integer id, @RequestBody Customer updatedCustomer) {
        Optional<Customer> customerOpt = customerService.getCustomerById(id);
        if (customerOpt.isPresent()) {
            Customer existingCustomer = customerOpt.get();
            existingCustomer.setName(updatedCustomer.getName());
            existingCustomer.setEmail(updatedCustomer.getEmail());
            existingCustomer.setPassword(updatedCustomer.getPassword());
            // Agrega más campos si los tienes en tu entidad

            Customer saved = customerService.createCustomer(existingCustomer); // reutilizamos createCustomer para guardar
            return ResponseEntity.ok(saved);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchCustomer(@PathVariable Integer id, @RequestBody Customer customer) {
        try {
            Customer patch = customerService.patchCustomer(id, customer);
            return ResponseEntity.ok(patch);
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el producto con el id " + customer.getId());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Integer id) {Add commentMore actions
        Optional<Customer> customerOpt = customerService.getCustomerById(id);
        if (customerOpt.isPresent()) {
            customerService.deleteCustomer(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
