package com.reactor.webflux.springwebflux.services.impl;

import com.reactor.webflux.springwebflux.models.dao.ProductoDao;
import com.reactor.webflux.springwebflux.models.documents.ProductoDocument;
import com.reactor.webflux.springwebflux.services.ProductService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
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

    @Override
    public Mono<ProductoDocument> findById(String id) {
        return this.productoDao.findById(id);
    }

    @Override
    public Mono<ProductoDocument> save(ProductoDocument productoDocument) {
        return this.productoDao.save(productoDocument);
    }

    @Override
    public Mono<Void> delete(ProductoDocument productoDocument) {
        return this.productoDao.delete(productoDocument);
    }
}
