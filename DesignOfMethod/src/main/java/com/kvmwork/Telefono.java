/*  La idea de Telefono es: “esto es un Producto, pero además tiene stock”. En el material aparece con 
 * esa misma lógica, añadiendo solo el atributo específico y reutilizando nombre, marca y modelo desde 
 * la clase padre.  */
package com.kvmwork;
/* Qué hace extends: Telefono es un Producto. Eso significa que hereda todo lo que ya estaba bien 
 * resuelto en el padre: nombre, marca, modelo, sus getters, y la idea general del objeto.*/
public class Telefono extends Producto {

    private int stock;

    public Telefono(String nombre, String marca, String modelo, int stock) {
        super(nombre, marca, modelo);//como Telefono ya hereda nombre, marca y modelo, no los vuelve a crear;
        this.stock = stock;//simplemente se los pasa al padre para que el padre los construya correctamente.
    }

    public int getStock() {
        return stock;
    }
 // Clase Abstracta:  “si me preguntas qué soy, respondo TELEFONO”.
    @Override
    public TipoProducto getTipo() {
        return TipoProducto.TELEFONO;
    }
/* toString() en el hijo: Aquí el toString() ya no muestra solo la parte común, sino también la específica.
 *  Por eso en Telefono añades stock, y además aprovechas los getters heredados para mostrar nombre, marca 
 *  y modelo.*/
    @Override
    public String toString() {
        return "Telefono{" +
                "nombre='" + getNombre() + '\'' +
                ", marca='" + getMarca() + '\'' +
                ", modelo='" + getModelo() + '\'' +
                ", stock=" + stock +
                '}';
    }
}
