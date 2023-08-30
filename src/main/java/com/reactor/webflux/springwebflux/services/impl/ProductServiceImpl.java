package com.reactor.webflux.springwebflux.services.impl;

import com.reactor.webflux.springwebflux.models.dao.CategoryDao;
import com.reactor.webflux.springwebflux.models.dao.ProductoDao;
import com.reactor.webflux.springwebflux.models.documents.CategoryDocument;
import com.reactor.webflux.springwebflux.models.documents.ProductoDocument;
import com.reactor.webflux.springwebflux.services.ProductService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductoDao productoDao;
    private final CategoryDao categoryDao;

    public ProductServiceImpl(ProductoDao productoDao, CategoryDao categoryDao) {
        this.productoDao = productoDao;
        this.categoryDao = categoryDao;
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
    public Flux<CategoryDocument> findAllCategory() {
        return this.categoryDao.findAll();
    }

    @Override
    public Mono<ProductoDocument> findById(String id) {
        return this.productoDao.findById(id);
    }

    @Override
    public Mono<CategoryDocument> findByIdCategory(String id) {
        return this.categoryDao.findById(id);
    }

    @Override
    public Mono<ProductoDocument> save(ProductoDocument productoDocument) {
        return this.productoDao.save(productoDocument);
    }

    @Override
    public Mono<CategoryDocument> saveCategory(CategoryDocument categoryDocument) {
        return this.categoryDao.save(categoryDocument);
    }

    @Override
    public Mono<Void> delete(ProductoDocument productoDocument) {
        return this.productoDao.delete(productoDocument);
    }
}
