package com.conexion.sqlite.Repository;

import com.conexion.sqlite.Domain.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsDBA extends JpaRepository<Products, Integer> {
}
