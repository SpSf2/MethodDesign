/* primero se construye la clase padre Producto, porque ahí metes lo común a todos los objetos del problema: 
 * nombre, marca y modelo; además, en tus apuntes se explica justamente así, como base para que Telefono y 
 * Coche hereden y luego una sola List<Producto> pueda guardar ambos tipos.*/
package com.kvmwork;

public abstract class Producto {

    private String nombre;  // Tu Producto:    Tu clase hace estas 3 cosas bien:
    private String marca;   // Define la parte común de todos los productos: nombre, marca, modelo.
    private String modelo;

 /* El constructor existe para que, en el momento de crear un objeto, ese objeto ya nazca con sus datos cargados.
  *  En tu caso, cuando más adelante hagas algo como new Telefono("iPhone 15", "Apple", "Pro", 10), la parte
común nombre, marca y modelo se inicializa gracias al constructor de Producto, que luego será invocado desde 
el hijo con super(...). */
    public Producto(String nombre, String marca, String modelo) {
        this.nombre = nombre;        
        this.marca = marca;
        this.modelo = modelo;
    }
 /**los getters sirven para obtener el valor de los atributos privados. Como los campos están declarados private, 
  * no puedes acceder directamente desde fuera con algo como:   producto.nombre     */

    public String getNombre() {
        return nombre;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

  //Obliga a los hijos a decir qué tipo de producto son mediante getTipo().
    public abstract TipoProducto getTipo();

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                '}';
    }
}