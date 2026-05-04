package com.kvmwork;

import lombok.Builder;

@Builder
public record Libro(    //Los componentes del record
		String titulo,  // Dentro del paréntesis definimos los componentes del record:    String titulo
						// Guarda el título del libro. Como el record genera automáticamente el método accesor, luego podrás hacer:
		String autor,   //libro.titulo() y no getTitulo(), que es una diferencia que se remarcan entre clase normal y record.
        int anioPublicacion,
        CategoriaLibro categoria,
        int numeroPaginas,
        double precio
) implements MaterialBiblioteca {
}/* Aquí estamos diciendo que Libro cumple el contrato de la interfaz MaterialBiblioteca. Eso significa que debe poder responder a:
titulo()      autor()     anioPublicacion()

Y como precisamente esos tres componentes ya existen en el record, Java considera que Libro ya satisface la interfaz sin que tú 
tengas que escribir métodos extra.*/
