package com.reactor.webflux.springwebflux.services;

import com.reactor.webflux.springwebflux.models.documents.ProductoDocument;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public interface ProductService {
    Flux<ProductoDocument> findAll();
}
