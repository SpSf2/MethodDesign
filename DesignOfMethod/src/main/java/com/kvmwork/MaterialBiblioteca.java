package com.kvmwork;
/* Qué hace esta interfaz
Esta interfaz obliga a que cualquier clase o record que la implemente tenga estas tres operaciones:
String titulo();
String autor();
int anioPublicacion();
Eso significa que, aunque Libro y Revista sean distintos, ambos deben poder responder:

cuál es su título,    cuál es su autor,     cuál es su año de publicación.

Así conseguimos meterlos juntos en una colección como esta: List<MaterialBiblioteca> materiales = new ArrayList<>();
*/
public interface MaterialBiblioteca {  //Por qué los métodos se escriben así:  String titulo();
    String titulo();         // no tiene cuerpo. Solo declara la firma del método. Eso en una interfaz significa: 
    String autor();          // “quien me implemente, debe tener este método
    int anioPublicacion();
}         // Además, observa que se llama titulo() y no getTitulo(). los record generan automáticamente métodos get 