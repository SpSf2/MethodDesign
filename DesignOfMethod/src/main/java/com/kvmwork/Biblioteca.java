/*  Biblioteca representa una biblioteca concreta, por ejemplo “Biblioteca Central”, y guarda además un Libro destacado. 
 * Eso encaja con lo que ya has visto en tus apuntes sobre records anidados, donde un record puede tener como componente otro
 *  record,  * por ejemplo Library conteniendo un Book.
 *  Aquí defines un record, no una clase tradicional. Por tanto, Java vuelve a generarte automáticamente:

constructor completo,  accesores con el nombre del componente:  equals()    hashCode()     toString()

Además, sigues trabajando con datos inmutables, que es justamente uno de los puntos fuertes del record      */
package com.kvmwork;

import lombok.Builder;

@Builder
public record Biblioteca( 
        String nombre,        // Es el nombre de la biblioteca. Java genera automáticamente el método:  nombre()
        Libro libroDestacado  // Este es el punto interesante del archivo. Aquí no estás guardando un dato simple como String 
)  {                          // o int, sino un objeto completo de tipo Libro. Eso convierte a Biblioteca en un ejemplo claro 
}// de composición: un objeto contiene otro objeto.
/*  Este record no está pensado solo para “guardar una biblioteca”, sino para preparar el terreno para algo que tú ya has visto: 
 * la desestructuración de records anidados con pattern matching.     */ 
 