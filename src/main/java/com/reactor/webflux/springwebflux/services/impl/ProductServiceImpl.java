package com.reactor.webflux.springwebflux.services.impl;

import com.reactor.webflux.springwebflux.models.dao.ProductoDao;
import com.reactor.webflux.springwebflux.models.documents.ProductoDocument;
import com.reactor.webflux.springwebflux.services.ProductService;
import reactor.core.publisher.Flux;

public class ProductServiceImpl implements ProductService {

    private final ProductoDao productoDao;

    public ProductServiceImpl(ProductoDao productoDao) {
        this.productoDao = productoDao;
    }

    @Override
    public Flux<ProductoDocument> findAll() {
        Flux<ProductoDocument> productos = this.productoDao.findAll()
                .map(productoDocument -> {
                    String name = productoDocument.getNombre();
                    productoDocument.setNombre(name.toUpperCase());
                    return productoDocument;
                });
        return productos;
    }
}
