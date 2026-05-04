package com.kvmwork;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
/*El tipo de la lista es MaterialBiblioteca, no Libro ni Revista, porque queremos que admita distintos subtipos bajo una misma 
 * referencia común, es decir, polimorfismo. */
        List<MaterialBiblioteca> materiales = new ArrayList<>();//Crea una variable llamada materiales que apunta a una lista vacía.
        		                        //  Porque quieres una sola colección que pueda guardar distintos subtipos: Libro y Revista.
        materiales.add(Libro.builder() //  Crea un objeto Libro con el builder y lo mete en la lista materiales.
                .titulo("Java Backend Moderno")
                .autor("Ana Torres")
                .anioPublicacion(2022)
                .categoria(CategoriaLibro.PROGRAMACION)
                .numeroPaginas(450)
                .precio(39.99)
                .build());

        materiales.add(Libro.builder()
                .titulo("Spring en Accion")
                .autor("Luis Gómez")
                .anioPublicacion(2021)
                .categoria(CategoriaLibro.PROGRAMACION)
                .numeroPaginas(520)
                .precio(42.50)
                .build());

        materiales.add(Libro.builder()
                .titulo("SQL para Todos")
                .autor("Ana Torres")
                .anioPublicacion(2020)
                .categoria(CategoriaLibro.BASES_DE_DATOS)
                .numeroPaginas(300)
                .precio(29.99)
                .build());

        materiales.add(Libro.builder()
                .titulo("Microservicios Reales")
                .autor("Carlos Ruiz")
                .anioPublicacion(2024)
                .categoria(CategoriaLibro.ARQUITECTURA)
                .numeroPaginas(610)
                .precio(49.90)
                .build());

        materiales.add(Libro.builder()
                .titulo("Docker y Kubernetes")
                .autor("Laura Pérez")
                .anioPublicacion(2023)
                .categoria(CategoriaLibro.DEVOPS)
                .numeroPaginas(410)
                .precio(44.00)
                .build());

        materiales.add(Revista.builder()
                .titulo("Tech Monthly")
                .autor("Editorial Tech")
                .anioPublicacion(2024)
                .numeroEdicion(15)
                .mesPublicacion("ENERO")
                .build());

        materiales.add(Revista.builder()
                .titulo("Tech Monthly")
                .autor("Editorial Tech")
                .anioPublicacion(2024)
                .numeroEdicion(16)
                .mesPublicacion("FEBRERO")
                .build());

        materiales.add(Revista.builder()
                .titulo("Arquitectura Hoy")
                .autor("Editorial Pro")
                .anioPublicacion(2023)
                .numeroEdicion(8)
                .mesPublicacion("NOVIEMBRE")
                .build());                    // Crea un objeto Biblioteca que tiene: un nombre, un Libro como: libroDestacado. 
                                              // Por qué se hace: Para mostrar composición: un objeto contiene otro objeto
        Biblioteca biblioteca = Biblioteca.builder()  // Biblioteca biblioteca: variable de tipo Biblioteca
                .nombre("Biblioteca Central Backend") 
                .libroDestacado(Libro.builder()  // asigna un objeto Libro.  Dentro de .libroDestacado(...) se construye ese 
                        .titulo("Patrones de Diseño en Java")                                   // libro en el momento
                        .autor("Elena Martín")
                        .anioPublicacion(2021)
                        .categoria(CategoriaLibro.ARQUITECTURA)
                        .numeroPaginas(390)
                        .precio(37.80)
                        .build())
                .build();

        System.out.println("========== BIBLIOTECA ==========");  // Por qué al imprimir el objeto sale algo legible
        System.out.println(biblioteca);      // Porque Biblioteca es un record, y los records generan automáticamente toString().
        																					
        //  llamada al método auxiliar:  Llama al método imprimeDetalles y le pasa el libro destacado de la biblioteca.
        System.out.println("\n========== RECORD PATTERN MANUAL CON INSTANCEOF ==========");
        imprimeDetalles(biblioteca.libroDestacado());
        												
        										// Qué significa System.out::println      Es una referencia a método.
        System.out.println("\n========== LISTADO ORIGINAL =========="); //  cuando la lambda solo llama a un método, puedes pasar 
        materiales.forEach(System.out::println);                        //  la referencia a ese método directamente.

        Set<String> autoresLibros = new HashSet<>();
        Set<String> mesesRevistas = new HashSet<>(); // HashSet: implementación concreta de Set, <String>: contendrá cadenas

        												//  for + switch con pattern matching
        for (MaterialBiblioteca material : materiales) {
            switch (material) {
                case Libro libro -> autoresLibros.add(libro.autor());
                case Revista revista -> mesesRevistas.add(revista.mesPublicacion());
                default -> {
                }
            }
        }

        System.out.println("\n========== SET RESULTANTES ==========");
        System.out.println("Autores de libros sin duplicados:");
        System.out.println(autoresLibros);

        System.out.println("Meses de publicación de revistas sin duplicados:");
        System.out.println(mesesRevistas);

        																			//  mapa para contar por categoría
        Map<CategoriaLibro, Integer> cantidadLibrosPorCategoria = new HashMap<>();// mapa que relaciona una categoría con una cantidad
        										// Porque Map se usa cuando quieres relación clave -> valor, contar, agrupar o indexar.
        for (MaterialBiblioteca material : materiales) {  //llenar el mapa con if instanceof, Recorre materiales, se queda solo con 
            if (material instanceof Libro libro) { //los libros y cuenta cuántos hay por categoría: comprueba si material es un Libro
                CategoriaLibro clave = libro.categoria();// si lo es, crea la variable libro. Guarda en "clave" la categoría del libro
                cantidadLibrosPorCategoria.put(          //  El método put mete o reemplaza un valor en el mapa.
                        clave,
                        cantidadLibrosPorCategoria.getOrDefault(clave, 0) + 1
                );                                          // busca el valor actual de esa categoría, si no existe, devuelve 0
            }                                     // Este patrón es muy importante con Map:  saco la clave, miro cuánto había
                                                 // si no había, parto de 0.    sumo 1.     vuelvo a guardar
        }
                                                    // imprimir el mapa: Recorre todas las entradas del mapa e imprime clave y valor.
        System.out.println("\n========== MAPA: CANTIDAD DE LIBROS POR CATEGORIA ==========");
        cantidadLibrosPorCategoria.forEach(
                (categoria, cantidad) -> System.out.println(categoria + " -> " + cantidad)
        );
        /* Qué significa la lambda:  (categoria, cantidad) -> ... Es una expresión lambda de dos parámetros. De dónde viene
Son una forma simplificada de implementar el único método abstracto de una interfaz funcional.
Aquí forEach del Map espera una acción que reciba:  clave,  valor.  y tú se la das con esa lambda.     */
        																							// Stream + groupingBy
        Map<String, List<Libro>> librosPorAutor = materiales.stream()  // Convierte la lista general en un mapa donde:
                .filter(Libro.class::isInstance)                      // clave: autor, valor: lista de libros de ese autor
                .map(Libro.class::cast)                     //  materiales.stream()  Convierte la colección en un flujo o pipeline
                .collect(java.util.stream.Collectors.groupingBy(Libro::autor));
        							/**.filter(Libro.class::isInstance)  Deja pasar solo los elementos que son instancia de Libro.
        							 * .map(Libro.class::cast)  Convierte cada elemento filtrado al tipo Libro.
        							 * .collect(...)  Operación terminal que recoge el resultado final del stream.
							* Collectors.groupingBy(Libro::autor)  Agrupa los libros por el valor que devuelve autor(). Es decir:
							* mira cada libro,  toma su autor como clave,  mete el libro en la lista de ese autor  */
//  imprimir el mapa agrupado
        System.out.println("\n========== MAPA: LIBROS AGRUPADOS POR AUTOR ==========");
        librosPorAutor.forEach((autor, libros) -> {
            System.out.println("Autor: " + autor);
            libros.forEach(libro -> System.out.println("   " + libro.titulo()));
        });            /** Recorre el mapa por autor e imprime los títulos de sus libros. Qué significa?    autor: clave del mapa
                        * libros: valor del mapa, que aquí es List<Libro>.  Lambda con llaves,  Aquí usas:
						* (autor, libros) -> {
						*    ...
						*  }                             porque tienes más de una instrucción dentro.  Internamente
	* Por cada autor: imprime el nombre del autor, recorre la lista de libros de ese autor, imprime el título de cada libro  */

        double precioPromedioLibros = materiales.stream()
                .filter(Libro.class::isInstance)          // Deja pasar solo los elementos que son instancia de Libro.
                .map(Libro.class::cast)
                .mapToDouble(Libro::precio)               // Toma cada libro y extrae su precio como double.
                .average()                                // Calcula el precio promedio de los libros.
                .orElse(0.0);  // Si hay promedio, lo devuelve.  Si no hay ningún libro, devuelve 0.0.

        System.out.println("\nPrecio promedio de los libros: " + precioPromedioLibros);

        Libro libroMasCaro = materiales.stream()  
                .filter(Libro.class::isInstance)
                .map(Libro.class::cast)           
                .max(ComparadoresMateriales.POR_PRECIO)
                .orElse(null);

        System.out.println("\nLibro más caro:");
        System.out.println(libroMasCaro);

 /** Obtiene una nueva lista de libros ordenados de mayor a menor precio.  
Qué significa .sorted(...)     Ordena el flujo según el comparador que le pases.
Qué significa .toList()      Convierte el resultado final del stream en una lista.
cuando quieres ordenar sin alterar el orden natural o sin usar Collections.sort directamente sobre la colección, 
puedes convertir a stream y usar sorted(...)*/
        List<Libro> librosOrdenadosPorPrecioDesc = materiales.stream()
                .filter(Libro.class::isInstance)
                .map(Libro.class::cast)
                .sorted(ComparadoresMateriales.POR_PRECIO_DESC)
                .toList();

        System.out.println("\n========== LIBROS ORDENADOS POR PRECIO DESC ==========");
        librosOrdenadosPorPrecioDesc.forEach(System.out::println);
        
/**  Ordena todos los materiales primero por año y, si empatan, por título.    Por qué es útil:  Porque este comparador trabaja 
 * sobre el tipo común MaterialBiblioteca, no solo sobre Libro. Eso significa que sirve tanto para libros como para revistas, 
 * ya que ambos tienen anioPublicacion() y titulo() por el contrato común.  */
        List<MaterialBiblioteca> materialesOrdenadosPorAnioYTitulo = materiales.stream()
                .sorted(ComparadoresMateriales.POR_ANIO_Y_TITULO)
                .toList();

        System.out.println("\n========== MATERIALES ORDENADOS POR AÑO Y TITULO ==========");
        materialesOrdenadosPorAnioYTitulo.forEach(System.out::println);
    }

    static void imprimeDetalles(MaterialBiblioteca obj) {
        if (obj instanceof Libro libro) {
            System.out.println("Título del libro: " + libro.titulo());
            System.out.println("Autor del libro: " + libro.autor());
            System.out.println("Categoría: " + libro.categoria());
        } else if (obj instanceof Revista revista) {
            System.out.println("Título de la revista: " + revista.titulo());
            System.out.println("Edición: " + revista.numeroEdicion());
            System.out.println("Mes: " + revista.mesPublicacion());
        } else {
            System.out.println("Tipo no reconocido");
        }
    }
}
/**  Método imprimeDetalles: *  
 * Primer if:   if (obj instanceof Libro libro) {    Qué hace:  Comprueba si obj es un Libro y, si lo es, crea la variable libro.
 * Por qué es mejor que el cast antiguo?   Antes se hacía algo así:
if (obj instanceof Libro) {
    Libro libro = (Libro) obj;
}
Ahora Java lo hace más compacto con pattern matching.  Dentro del bloque: 
System.out.println("Título del libro: " + libro.titulo());
System.out.println("Autor del libro: " + libro.autor());
System.out.println("Categoría: " + libro.categoria());
	Muestra campos del libro usando los accessors del record.   
	
    } else if (obj instanceof Revista revista) {      Hace lo mismo, pero para una revista.
    
} else {
    System.out.println("Tipo no reconocido");
}
Si no es ni libro ni revista, imprime un mensaje por defecto.*/