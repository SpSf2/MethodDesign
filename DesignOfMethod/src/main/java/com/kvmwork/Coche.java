/*  Clase Coche
La misma idea se repite con Coche: sigue siendo un Producto, pero su dato propio ya no es stock, sino
 fechaMatriculacion. usa LocalDate para representar la fecha correctamente.*/
package com.kvmwork;

import java.time.LocalDate;

public class Coche extends Producto {

    private LocalDate fechaMatriculacion;

    public Coche(String nombre, String marca, String modelo, LocalDate fechaMatriculacion) {
        super(nombre, marca, modelo);
        this.fechaMatriculacion = fechaMatriculacion;
    }

    public LocalDate getFechaMatriculacion() {
        return fechaMatriculacion;
    }                                                //Patrón que debes memorizar
                                                 // Aquí ya aparece un patrón que te sirve para muchos ejercicios
    @Override                                    // del curso: • Clase padre: contiene lo común.
    public TipoProducto getTipo() {              //  • Clase hija: añade lo específico.
    	return TipoProducto.COCHE;               // • Constructor del hijo: llama a super(...).
    }                                  // • El hijo implementa los métodos abstractos que el padre dejó pendientes.
    
    @Override
    public String toString() {
        return "Coche{" +
                "nombre='" + getNombre() + '\'' +
                ", marca='" + getMarca() + '\'' +
                ", modelo='" + getModelo() + '\'' +
                ", fechaMatriculacion=" + fechaMatriculacion +
                '}';
    }
}