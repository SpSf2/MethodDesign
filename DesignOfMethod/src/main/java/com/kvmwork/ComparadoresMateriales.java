package com.kvmwork;

import java.util.Comparator;

public class ComparadoresMateriales {  //  Define una clase utilitaria que contiene comparadores reutilizables.
// Por qué se hace:  	Para no escribir comparadores largos una y otra vez dentro del Main.
	
	public static final Comparator<Libro> POR_PRECIO =
            Comparator.comparingDouble(Libro::precio);	// (Libro::precio) cuando me den un Libro, extrae su precio
	
    public static final Comparator<Libro> POR_PRECIO_DESC =      //  Crea un comparador de Libro que ordena 
            Comparator.comparingDouble(Libro::precio).reversed();//  por precio de mayor a menor.
    													
    public static final Comparator<Libro> POR_CATEGORIA_Y_TITULO =
            Comparator.comparing((Libro l) -> l.categoria().name())
                    .thenComparing(Libro::titulo);

    public static final Comparator<MaterialBiblioteca> POR_ANIO_Y_TITULO =
            Comparator.comparingInt(MaterialBiblioteca::anioPublicacion)
                    .thenComparing(MaterialBiblioteca::titulo);

    private ComparadoresMateriales() {
    }
}
