package com.reactor.webflux.springwebflux;

import com.reactor.webflux.springwebflux.models.dao.ProductoDao;
import com.reactor.webflux.springwebflux.models.documents.ProductoDocument;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Flux;

import java.util.Date;

@SpringBootApplication
@Slf4j
public class SpringWebfluxApplication implements CommandLineRunner {

    private final ProductoDao productoDao;

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public SpringWebfluxApplication(ProductoDao productoDao, ReactiveMongoTemplate reactiveMongoTemplate) {
        this.productoDao = productoDao;
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringWebfluxApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        reactiveMongoTemplate.dropCollection("productos").subscribe();
        Flux.just(
                new ProductoDocument("TV Panasonic Pantalla LCD", 700.5),
                new ProductoDocument("TV Panasonic Pantalla LCD", 700.5),
                new ProductoDocument("TV Panasonic Pantalla LCD", 700.5)
        )
                .flatMap(productoDocument -> {
                    productoDocument.setCreateAt(new Date());
                    return productoDao.save(productoDocument);
                })
                .subscribe(productoDocument -> log.info("Insert: " + productoDocument.toString()));
    }
}
