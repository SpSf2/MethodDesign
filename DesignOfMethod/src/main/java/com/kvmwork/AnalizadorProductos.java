/** Ahora toca justo ese paso: el recorrido con switch y pattern matching, que es donde el ejercicio ya se 
 * resuelve de verdad: el recorrido con switch y pattern matching, que es el núcleo del ejercicio. Esta es la
 *  parte donde usas la herencia para distinguir tipos y llenas los dos Set.

Primero declaras los dos Set que van a contener la información sin duplicados:
 * */
package com.kvmwork;

import com.kvmwork.Coche;
import com.kvmwork.Producto;
import com.kvmwork.Telefono;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AnalizadorProductos {

    public static Set<String> obtenerMarcasTelefonosSinDuplicados(List<Producto> productos) {
        Set<String> marcas = new HashSet<>();  //Creo un Set vacío.

        for (Producto producto : productos) {             // Recorro todos los productos.
            if (producto instanceof Telefono telefono) {  // Si el producto es un teléfono, 
                marcas.add(telefono.getMarca());          // guardo su marca.
            }
        }

        return marcas;                                    // Al final devuelvo el conjunto de marcas.
    }

    public static Set<LocalDate> obtenerFechasCochesSinDuplicados(List<Producto> productos) {
        Set<LocalDate> fechas = new HashSet<>();

        for (Producto producto : productos) {
            if (producto instanceof Coche coche) {
                fechas.add(coche.getFechaMatriculacion());
            }
        }

        return fechas;
    }
// Aquí aparece ya el corazón de la parte Map del curso: no solo quieres guardar datos sin repetir, sino contarlos
// por clave. La clave del mapa es la marca (String) y el valor es cuántos teléfonos hay de esa marca (Long).

    public static Map<String, Long> contarTelefonosPorMarca(List<Producto> productos) {
        Map<String, Long> conteo = new HashMap<>();  // mapa vacio: un HashMap guarda pares clave -> valor.

        for (Producto producto : productos) {
            if (producto instanceof Telefono telefono) {  //conteo.put = coloca en conteo o agregar a conteo
                conteo.put(telefono.getMarca(), conteo.getOrDefault(telefono.getMarca(), 0L) + 1);
            }  //Esto hace tres cosas: busca si la marca ya existe en el mapa; si no existe, usa 0L por defecto;
               // suma 1 y vuelve a guardar el nuevo total.  Ejemplo mental: primer Apple -> no existe -> 0 + 1 = 1;
        }  // segundo Apple -> ya existe con 1 -> 1 + 1 = 2.

        return conteo;
    }
/* Este método ya no cuenta, sino que agrupa. La clave vuelve a ser la marca, pero el valor ya no es un número,
 *  sino una lista de productos de esa marca.  Por eso el tipo es más complejo:  Map<String, List<Producto>>
Esto significa:           "Apple" -> lista con todos los productos Apple;  
"Seat" -> lista con todos los productos Seat;             "Ford" -> lista con todos los productos Ford.*/
    public static Map<String, List<Producto>> agruparProductosPorMarca(List<Producto> productos) {
        Map<String, List<Producto>> agrupados = new HashMap<>();

        for (Producto producto : productos) {
            agrupados
                    .computeIfAbsent(producto.getMarca(), k -> new ArrayList<>())
                    .add(producto);
        }/*Esto quiere decir: “si todavía no existe una lista para esta marca, créala ahora”. Y enseguida después
         haces .add(producto), o sea, agregas el producto a la lista correspondiente.*/
        return agrupados;
    }
/* Aquí cambias de operación: ya no filtras para meter en Set, ni cuentas con Map, sino que filtras y luego 
 * ordenas. Primero construyes una lista que contenga solo teléfonos, porque no tendría sentido ordenar coches 
 * por stock si el stock es una propiedad exclusiva de Telefono.*/
    public static List<Telefono> obtenerTelefonosOrdenadosPorStockDesc(List<Producto> productos) {
        List<Telefono> telefonos = new ArrayList<>();

        for (Producto producto : productos) {
            if (producto instanceof Telefono telefono) {
                telefonos.add(telefono);
            }
        } /* Esto se lee así:  
					Comparator.comparingInt(...): crea un comparador basado en un entero;
    						• Telefono::getStock: ese entero será el stock;
    									• .reversed(): invierte el orden para que vaya de mayor a menor.*/
        telefonos.sort(Comparator.comparingInt(Telefono::getStock).reversed());
        return telefonos;
    }
/* Este método hace una búsqueda simple: recorre la lista y devuelve el primer producto cuya marca coincida con
 *  la marca buscada. El uso de equalsIgnoreCase hace que la comparación no dependa de mayúsculas o minúsculas,
 *   así "Apple", "apple" o "APPLE" se consideren iguales.*/
    public static Producto buscarPrimerProductoPorMarca(List<Producto> productos, String marca) {
        for (Producto producto : productos) {
            if (producto.getMarca().equalsIgnoreCase(marca)) {
                return producto;
            }
        }
        return null;
    }
}
/*  Te dejo la traducción más útil:

new HashMap<>() = “prepara una tabla vacía clave -> valor”

getOrDefault(clave, valorPorDefecto) = “dame el valor de esa clave; si no existe, usa este otro”

computeIfAbsent(clave, …) = “si esa clave aún no existe, créala con un valor inicial”
Truco mental
Para no liarte:
    • Si el valor del mapa es un número, casi seguro usarás getOrDefault(...) para contar.
    • Si el valor del mapa es una lista o un set, casi seguro usarás computeIfAbsent(...)  para agrupar.
*/
