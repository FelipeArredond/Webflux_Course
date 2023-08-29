package com.reactor.webflux.springwebflux.models.dao;


import com.reactor.webflux.springwebflux.models.documents.ProductoDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductoDao extends ReactiveMongoRepository<ProductoDocument, String> {
}
