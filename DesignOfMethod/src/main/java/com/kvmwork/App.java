/*  Bien. Ahora viene la parte donde empieza realmente el ejercicio: crear la colección principal. En tus 
 * apuntes aparece así de forma muy clara: se usa List<Producto> productos = new ArrayList<>(); porque una 
 * lista de Producto puede guardar objetos Producto y también objetos de sus subtipos, como Telefono y Coche;
 *  eso es justamente el polimorfismo aplicado a colecciones.*/
package com.kvmwork;

import com.kvmwork.Coche;
import com.kvmwork.Producto;
import com.kvmwork.Telefono;
import com.kvmwork.AnalizadorProductos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {
    					// List<Producto>: la variable está pensada para almacenar elementos del tipo Producto.
        List<Producto> productos = new ArrayList<>();// new ArrayList<>(): estás reservando memoria creando una 
        											// lista modificable, a la que luego sí puedes hacer add
//Lo importante es esto: como Telefono y Coche heredan de Producto, Java permite meterlos en una List<Producto>
        productos.add(new Telefono("iPhone 15", "Apple", "Pro", 10));
        productos.add(new Telefono("Galaxy S24", "Samsung", "Ultra", 5));
        productos.add(new Telefono("iPhone 14", "Apple", "Plus", 7));
        productos.add(new Telefono("Redmi Note 14", "Xiaomi", "Note 14", 12));

        productos.add(new Coche("Coche 1", "Seat", "Ibiza", LocalDate.of(2020, 5, 10)));
        productos.add(new Coche("Coche 2", "Ford", "Focus", LocalDate.of(2021, 8, 20)));
        productos.add(new Coche("Coche 3", "Seat", "Leon", LocalDate.of(2020, 5, 10)));

        System.out.println("LISTADO ORIGINAL");
        productos.forEach(System.out::println);

        System.out.println("\nMARCAS DE TELEFONOS SIN DUPLICADOS");
        System.out.println(AnalizadorProductos.obtenerMarcasTelefonosSinDuplicados(productos));

        System.out.println("\nFECHAS DE COCHES SIN DUPLICADOS");
        System.out.println(AnalizadorProductos.obtenerFechasCochesSinDuplicados(productos));

        System.out.println("\nCONTEO DE TELEFONOS POR MARCA");
        System.out.println(AnalizadorProductos.contarTelefonosPorMarca(productos));

        System.out.println("\nPRODUCTOS AGRUPADOS POR MARCA");
        System.out.println(AnalizadorProductos.agruparProductosPorMarca(productos));

        System.out.println("\nTELEFONOS ORDENADOS POR STOCK DESC");
        System.out.println(AnalizadorProductos.obtenerTelefonosOrdenadosPorStockDesc(productos));

        System.out.println("\nPRIMER PRODUCTO DE MARCA APPLE");
        System.out.println(AnalizadorProductos.buscarPrimerProductoPorMarca(productos, "Apple"));
    }
}
