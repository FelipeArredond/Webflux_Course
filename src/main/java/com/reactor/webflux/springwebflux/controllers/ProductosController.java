package com.reactor.webflux.springwebflux.controllers;

import com.reactor.webflux.springwebflux.models.dao.ProductoDao;
import com.reactor.webflux.springwebflux.models.documents.ProductoDocument;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.spring6.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Controller
public class ProductosController {
    private final ProductoDao productoDao;

    public ProductosController(ProductoDao productoDao) {
        this.productoDao = productoDao;
    }

    @GetMapping({"/listar", "/"})
    public String listar(Model model) {
        Flux<ProductoDocument> productos = this.productoDao.findAll()
                        .map(productoDocument -> {
                            String name = productoDocument.getNombre();
                            productoDocument.setNombre(name.toUpperCase());
                            return productoDocument;
                        });
        model.addAttribute("productos", productos);
        model.addAttribute("titulo", "Listado de productos");
        return "listar";
    }

    @GetMapping("/listar-datadriver")
    public String listarDataDriver(Model model) {
        Flux<ProductoDocument> productos = this.productoDao.findAll()
                .map(productoDocument -> {
                    String name = productoDocument.getNombre();
                    productoDocument.setNombre(name.toUpperCase());
                    return productoDocument;
                }).delayElements(Duration.ofSeconds(2));
        model.addAttribute("productos", new ReactiveDataDriverContextVariable(productos,2));
        model.addAttribute("titulo", "Listado de productos");
        return "listar";
    }

    @GetMapping("/listar-full")
    public String listarFull(Model model) {
        Flux<ProductoDocument> productos = this.productoDao.findAll()
                .map(productoDocument -> {
                    String name = productoDocument.getNombre();
                    productoDocument.setNombre(name.toUpperCase());
                    return productoDocument;
                }).repeat(5000);
        model.addAttribute("productos",productos);
        model.addAttribute("titulo", "Listado de productos");
        return "listar";
    }
}
