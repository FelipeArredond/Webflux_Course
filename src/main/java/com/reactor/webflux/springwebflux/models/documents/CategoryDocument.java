package com.reactor.webflux.springwebflux.models.documents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categorias")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDocument {
    @Id
    String id;
    String nombre;
}
