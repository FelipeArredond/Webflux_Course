package com.reactor.webflux.springwebflux.models.dao;

import com.reactor.webflux.springwebflux.models.documents.CategoryDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CategoryDao extends ReactiveMongoRepository<CategoryDocument, String> {
}
