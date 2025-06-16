package com.conexion.sqlite.Repository;

import com.conexion.sqlite.Domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDBA extends JpaRepository<Customer, Integer> {

}