package com.conexion.sqlite.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestControllerAdd commentMore actions

public class HelloController {
    @GetMapping("/")
    public String hello() {
        return "¡Hola desde Spring Boot con SQLite!";
    }
}