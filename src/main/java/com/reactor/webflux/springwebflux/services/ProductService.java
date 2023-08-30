package com.reactor.webflux.springwebflux.services;

import com.reactor.webflux.springwebflux.models.documents.CategoryDocument;
import com.reactor.webflux.springwebflux.models.documents.ProductoDocument;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
    Flux<ProductoDocument> findAll();
    Flux<CategoryDocument> findAllCategory();
    Mono<ProductoDocument> findById(String id);
    Mono<CategoryDocument> findByIdCategory(String id);
    Mono<ProductoDocument> save(ProductoDocument productoDocument);
    Mono<CategoryDocument> saveCategory(CategoryDocument categoryDocument);
    Mono<Void> delete(ProductoDocument productoDocument);
}
