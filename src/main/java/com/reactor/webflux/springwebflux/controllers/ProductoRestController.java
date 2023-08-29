package com.reactor.webflux.springwebflux.controllers;

import com.reactor.webflux.springwebflux.models.dao.ProductoDao;
import com.reactor.webflux.springwebflux.models.documents.ProductoDocument;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/api/productos")
public class ProductoRestController {
    private final ProductoDao productoDao;

    public ProductoRestController(ProductoDao productoDao) {
        this.productoDao = productoDao;
    }

    @GetMapping
    public Flux<ProductoDocument> index(){
        return this.productoDao.findAll().doOnNext(productoDocument -> log.info(productoDocument.toString()));
    }

    @GetMapping("/{id}")
    public Mono<ProductoDocument> findById(@PathVariable String id){
        return this.productoDao.findById(id).doOnNext(productoDocument -> log.info(productoDocument.toString()));
    }
}
