package com.reactor.webflux.springwebflux.controllers;

import com.reactor.webflux.springwebflux.models.dao.ProductoDao;
import com.reactor.webflux.springwebflux.models.documents.ProductoDocument;
import com.reactor.webflux.springwebflux.services.ProductService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.thymeleaf.spring6.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Controller
@Slf4j
public class ProductosController {
    private final ProductService productService;

    public ProductosController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping({"/listar", "/"})
    public String listar(Model model) {
        Flux<ProductoDocument> productos = this.productService.findAll();
        model.addAttribute("productos", productos);
        model.addAttribute("titulo", "Listado de productos");
        return "listar";
    }

    @GetMapping("/form")
    public Mono<String> crear(Model model) {
        model.addAttribute("producto", new ProductoDocument());
        model.addAttribute("titulo", "Formulario de producto");
        model.addAttribute("boton", "Crear");
        return Mono.just("form");
    }

    @GetMapping("/form/{id}")
    public Mono<String> editar(@PathVariable String id, Model model) {
        return this.productService.findById(id)
                .doOnNext(productoDocument -> {
                    log.info(productoDocument.toString());
                    model.addAttribute("titulo", "Editar Producto");
                    model.addAttribute("producto", productoDocument);
                    model.addAttribute("boton", "Editar");
                })
                .defaultIfEmpty(new ProductoDocument())
                .flatMap(productoDocument -> {
                    if(productoDocument.getId() == null){
                        return Mono.error(new InterruptedException("No existe el producto"));
                    }
                    return Mono.just(productoDocument);
                })
                .then(Mono.just("form"))
                .onErrorResume(ex -> Mono.just("redirect:/listar?error=no+existe+el+producto"));
    }

    @PostMapping("/form")
    public Mono<String> guardar(@Valid ProductoDocument productoDocument) {
        return this.productService.save(productoDocument).then(Mono.just("redirect:/listar"));
    }

    @GetMapping("/listar-datadriver")
    public String listarDataDriver(Model model) {
        Flux<ProductoDocument> productos = this.productService.findAll();
        model.addAttribute("productos", productos);
        model.addAttribute("titulo", "Listado de productos");
        return "listar";
    }

    @GetMapping("/listar-full")
    public String listarFull(Model model) {
        Flux<ProductoDocument> productos = this.productService.findAll().repeat(5000);
        model.addAttribute("productos", productos);
        model.addAttribute("titulo", "Listado de productos");
        return "listar";
    }
}
