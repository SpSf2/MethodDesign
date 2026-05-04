//  está construida con la misma filosofía que Libro
package com.kvmwork;

import lombok.Builder;

@Builder
public record Revista(        // Dicho de forma muy simple:
                              // un Libro tiene categoria, numeroPaginas, precio;
        String titulo,        // una Revista tiene numeroEdicion, mesPublicacion;
        String autor,         // pero ambos tienen titulo, autor, anioPublicacion.
        int anioPublicacion,  // Y eso basta para tratarlos como MaterialBiblioteca.
        int numeroEdicion,
        String mesPublicacion
) implements MaterialBiblioteca {
}
