package com.reactor.webflux.springwebflux.services;

import com.reactor.webflux.springwebflux.models.documents.ProductoDocument;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
    Flux<ProductoDocument> findAll();
    Mono<ProductoDocument> findById(String id);
    Mono<ProductoDocument> save(ProductoDocument productoDocument);
    Mono<Void> delete(ProductoDocument productoDocument);
}
