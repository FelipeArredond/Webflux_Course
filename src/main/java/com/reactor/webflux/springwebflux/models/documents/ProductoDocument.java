package com.reactor.webflux.springwebflux.models.documents;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Document(collection = "productos")
public class ProductoDocument {

    @Id
    private String id;

    private String nombre;

    private Double price;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createAt;

    public ProductoDocument(String nombre, Double price) {
        this.nombre = nombre;
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductoDocument{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", price=" + price +
                ", createAt=" + createAt +
                '}';
    }

    public ProductoDocument() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
