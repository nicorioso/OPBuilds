package com.conexion.sqlite.Repository;

import com.conexion.sqlite.Domain.Carts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartsDBA extends JpaRepository<Carts, Integer> {
}
