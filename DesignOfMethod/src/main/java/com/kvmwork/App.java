package com.kvmwork;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class App {

    public static void main(String[] args) {
    												//<?>, los símbolos < > indican que la lista trabaja con tipos genéricos.
    	List<? super Persona> listadoPersonas = new ArrayList<>(); // ?, esto significa “un tipo desconocido”.
    												/* List<? super Persona>, esto permite meter objetos Persona y también
    												 *  objetos hijos de Persona, como Pasajero y Tripulante*/
    	Pasajero pasajero1 = Pasajero.builder() // esto arranca el builder de la clase Pasajero. Como Pasajero hereda de 
    		    .id("P001")                     // Persona y tiene @SuperBuilder, este builder también conoce los campos heredados.
    		    .nombre("Carlos")
    		    .primerApellido("Martinez")
    		    .segundoApellido("Lopez")
    		    .fechaNacimiento(LocalDate.of(1998, Month.MARCH, 14))
    		    .nacionalidad("ESPAÑOLA")
    		    .pasaporte("X1234567")
    		    .categoriaViajero("ECONOMICA") //  esto asigna la categoría del billete
    		    .build();
    	//  aquí declaras una variable llamada tripulante1 de tipo Tripulante.
    	Tripulante tripulante1 = Tripulante.builder() // Tripulante.builder(), esto arranca el builder de la clase Tripulante.
    		    .id("T001")
    		    .nombre("Laura")
    		    .primerApellido("Gomez")
    		    .segundoApellido("Ruiz")
    		    .fechaNacimiento(LocalDate.of(1985, Month.JULY, 21))
    		    .nacionalidad("ESPAÑOLA")
    		    .cargo(Cargo.PILOTO)
    		    .salario(new BigDecimal("3200.50"))
    		    .fechaIncorporacion(LocalDate.of(2015, Month.JANUARY, 10))
    		    .baseOperativa("MADRID")
    		    .build();
    	
    	/*Meter objetos en la lista genérica
    	Ahora metemos pasajero1 y tripulante1 en listadoPersonas. Aquí ves por qué List<? super Persona> es perfecta para esto.*/
    	listadoPersonas.add(pasajero1);
    	listadoPersonas.add(tripulante1);
    //	listadoPersonas.add(pasajero1);, esto añade el objeto pasajero1 al final de la lista. Igual para tripulante1
    	
    /*	Crear objetos Vuelo
    	Ahora vamos a crear 3 vuelos de ejemplo, igual que hacías con los Empleado. Usamos el builder de Vuelo.  */
    	Vuelo vuelo1 = Vuelo.builder()
    	    .codigo("IB-2034")
    	    .origen("MAD")
    	    .destino("FCO")
    	    .fechaVuelo(LocalDate.of(2026, Month.JUNE, 10))
    	    .capacidadMaxima(180)
    	    .estado(EstadoVuelo.PROGRAMADO)
    	    .build();

    	Vuelo vuelo2 = Vuelo.builder()
    	    .codigo("IB-2056")
    	    .origen("BCN")
    	    .destino("PAR")
    	    .fechaVuelo(LocalDate.of(2026, Month.JUNE, 11))
    	    .capacidadMaxima(150)
    	    .estado(EstadoVuelo.EMBARCANDO)
    	    .build();

    	Vuelo vuelo3 = Vuelo.builder()
    	    .codigo("IB-2078")
    	    .origen("MAD")
    	    .destino("LON")
    	    .fechaVuelo(LocalDate.of(2026, Month.JUNE, 12))
    	    .capacidadMaxima(200)
    	    .estado(EstadoVuelo.CANCELADO)
    	    .build();
    /*  Lista de vuelos
		Ahora creamos la lista específica para vuelos y metemos los 3 que acabamos de crear. */
		
		List<Vuelo> vuelos = new ArrayList<>();
		vuelos.add(vuelo1);
		vuelos.add(vuelo2);
		vuelos.add(vuelo3);
		
	/*  Lista de reservas
		Ahora creamos la lista de reservas y metemos algunos objetos Reserva de ejemplo. Esto une pasajeros con vuelos. */
		List<Reserva> reservas = new ArrayList<>();
		
		Reserva reserva1 = Reserva.builder()
		    .pasajero(pasajero1)
		    .vuelo(vuelo1)
		    .numeroAsiento("12A")
		    .precio(new BigDecimal("199.99"))
		    .build();
		
		Reserva reserva2 = Reserva.builder()
			    .pasajero(pasajero1)
			    .vuelo(vuelo1)
			    .numeroAsiento("13B")
			    .precio(new BigDecimal("249.99"))
			    .build();

		Reserva reserva3 = Reserva.builder()
		    .pasajero(pasajero1)  // tripulante como pasajero (posible en aerolíneas)
		    .vuelo(vuelo2)
		    .numeroAsiento("5C")
		    .precio(new BigDecimal("399.50"))
		    .build();

			reservas.add(reserva1);
			reservas.add(reserva2);
			reservas.add(reserva3);
		
		/*  contar reservas por vuelo:Ahora hacemos la primera operación real del enunciado: cantidad de reservas por vuelo. 
	 * Esto usa groupingBy + counting()*/
		Map<String, Long> reservasPorVuelo = reservas.stream()// esto convierte la lista en un "flujo" por donde pasan los 
															  //  elementos uno a uno.
		    .collect(groupingBy(							 // -> operador de lambda; separa parámetro de acción.
		        reserva -> reserva.getVuelo().getCodigo(),  // reserva.getVuelo(), esto saca el objeto Vuelo de la reserva.
		        counting()									// .getCodigo(), esto saca el código del vuelo.
		    ));
//esto declara un Map. la clave String, será la nacionalidad. List<Pasajero>, el valor será una lista de pasajeros de esa nacionalidad.
		Map<String, List<Pasajero>> pasajerosPorNacionalidad = listadoPersonas.stream() // .filter, esto sirve para filtrar. 
			    .filter(obj -> obj instanceof Pasajero) //obj, es el nombre que le damos a cada objeto que va pasando por el stream.
			    .map(obj -> (Pasajero) obj)        // ->, este operador es una lambda; separa el parámetro de lo que haces con él.
			    .collect(groupingBy(Pasajero::getNacionalidad));// obj instanceof Pasajero, esto significa: “comprueba si el objeto 
//actual es una instancia de Pasajero.   déjame pasar solo los objetos que sean pasajeros; los demás sácalos fuera
// .map(obj -> (Pasajero) obj),  aquí conviertes cada objeto que ya pasó el filtro en un Pasajero		
/** .map, esto sirve para transformar un elemento en otro.  obj, sigue siendo cada objeto que va pasando.     ->, lambda.
(Pasajero) obj, esto es un cast; le dices a Java: “este objeto trátalo como un Pasajero”*/		
//.collect(groupingBy(Pasajero::getNacionalidad));, aquí recoges el resultado agrupando por nacionalidad.

/* Consulta: tripulantes por cargo
Aquí hacemos exactamente el mismo patrón que con pasajeros: lista mixta, filter, cast con map, y agrupación con groupingBy.
*/
		Map<Cargo, List<Tripulante>> tripulantesPorCargo = listadoPersonas.stream()// mismo patrón que con pasajeros
		    .filter(obj -> obj instanceof Tripulante)
		    .map(obj -> (Tripulante) obj)
		    .collect(groupingBy(Tripulante::getCargo));
		
/**Consulta: vuelos agrupados por destino y estado
Ahora hacemos una agrupación anidada, o sea, un Map dentro de otro Map. Primero agrupamos por destino y, dentro de cada destino,
 agrupamos por estado del vuelo.
*/
Map<String, Map<EstadoVuelo, List<Vuelo>>> vuelosPorDestinoYEstado = vuelos.stream()// String, la primera clave será el destino.
    .collect(groupingBy(                      // Map<EstadoVuelo, List<Vuelo>>, el valor de esa primera clave será otro mapa. 
        Vuelo::getDestino,                    // EstadoVuelo, la segunda clave será el estado.
        groupingBy(Vuelo::getEstado)          //List<Vuelo>, el valor final será la lista de vuelos que cumplen ambas cosas.
    ));                                       // Vuelo::getDestino, de cada vuelo, sácame el destino
                                              // groupingBy(Vuelo::getEstado)    dentro de cada destino, ahora separa por estado:
																				//PROGRAMADO, EMBARCANDO,COMPLETADO, CANCELADO
/*Consulta: ingreso total por vuelo
Ahora calculamos cuánto dinero ha entrado por cada vuelo a partir de la lista de reservas. Aquí usamos groupingBy + summingDouble.*/
		Map<String, Double> ingresoTotalPorVuelo = reservas.stream()
		    .collect(groupingBy(
		        reserva -> reserva.getVuelo().getCodigo(),// saca el objeto Vuelo de la reserva. Saca el código del vuelo.
		        summingDouble(reserva -> reserva.getPrecio().doubleValue())// para cada reserva del mismo vuelo, sácame el precio 
		    ));	// reserva -> ..., lambda que indica qué valor numérico hay que sumar.			// y súmalo
		
/* Consulta: vuelo con mayor recaudación
		Ahora vamos a sacar el vuelo que más dinero ha generado. Para eso usamos el Map que ya calculaste antes, 
		ingresoTotalPorVuelo, y buscamos la entrada con el valor más alto.  */
		Optional<Map.Entry<String, Double>> vueloConMayorRecaudacion = ingresoTotalPorVuelo.entrySet()
			    .stream()
			    .max(Map.Entry.comparingByValue());
		/*	vueloConMayorRecaudacion.ifPresent(entry -> 
		    System.out.println("Vuelo con Mayor recaudación = Código: " + entry.getKey() + ", total: " + entry.getValue())
este codigo se pegó abajo en los print para mantener el orden. Pero va aquí, según diseño				
  .ifPresent, esto significa: “si el Optional tiene valor, haz esto”.  entry, este es el nombre que le das a la entrada encontrada.
entry.getKey(), saca la clave, o sea, el código del vuelo.  entry.getValue(), saca el valor, o sea, la recaudación. */

/* Consulta: porcentaje de ocupación por vuelo
Ahora calculamos qué porcentaje de ocupación tiene cada vuelo. La idea es: número de reservas de ese vuelo / capacidad máxima 
del vuelo × 100. */

		Map<String, Double> porcentajeOcupacionPorVuelo = vuelos.stream() // collect(toMap(...)), recoge el resultado en un Map.
		    .collect(toMap(   // toMap, esto sirve para construir un mapa indicando qué será la clave y qué será el valor.
		        Vuelo::getCodigo,  // Vuelo::getCodigo, esto dice: “usa el código del vuelo como clave del mapa”.
		        vuelo -> {  // este es el nombre del objeto Vuelo que va pasando por el stream.      -> lambda
		            long totalReservas = reservas.stream() // esto crea una variable para guardar cuántas reservas tiene ese vuelo.
		                .filter(reserva -> reserva.getVuelo().getCodigo().equals(vuelo.getCodigo()))
		                .count();//.equals(vuelo.getCodigo()), compara ese código con el código del vuelo que estás procesando ahora.
		            				// déjame solo las reservas cuyo código de vuelo sea igual al vuelo actual

		
		            return (totalReservas * 100.0) / vuelo.getCapacidadMaxima();
		        }
		    ));     /*calcula qué parte de la capacidad está ocupada y conviértela en porcentaje”.
Esta tubería se lee así:
“recorre todos los vuelos, usa su código como clave del mapa, y para cada vuelo calcula el porcentaje de ocupación contando
 cuántas reservas le pertenecen y dividiéndolas por su capacidad máxima”.*/

/* Consulta: reservas ordenadas por precio:   Ahora ordenamos la lista de reservas por precio. Aquí usamos stream(), sorted() y 
 * un Comparator.                                                     List<Reserva> reservasOrdenadasPorPrecio =, esto declara una*/
		List<Reserva> reservasOrdenadasPorPrecio = reservas.stream() // lista nueva donde se guardará el resultado ordenado.
		    .sorted(Comparator.comparing(Reserva::getPrecio)) // .sorted, esto sirve para ordenar.
		    .collect(Collectors.toList());  //              Comparator.comparing(...), esto crea un comparador basado en un campo.
//esto recoge el stream ya ordenado y lo convierte otra vez en lista.             Reserva::getPrecio, esto significa: 
/** Si quisieras ordenar de mayor a menor, sería así:                             “usa el precio de cada reserva para comparar”.
List<Reserva> reservasOrdenadasPorPrecio = reservas.stream()
    .sorted(Comparator.comparing(Reserva::getPrecio).reversed())
    .collect(Collectors.toList());  */
		


			

        System.out.println("Simulador de vuelos");
        System.out.println(listadoPersonas);
        System.out.println("Reservas por vuelo: " + reservasPorVuelo);
        System.out.println("Pasajeros por nacionalidad: " + pasajerosPorNacionalidad);
        System.out.println("Tripulantes por cargo: " + tripulantesPorCargo);	
        System.out.println("Vuelos por destino y estado: " + vuelosPorDestinoYEstado);
        System.out.println("Ingreso total por vuelo: " + ingresoTotalPorVuelo);
        
        	vueloConMayorRecaudacion.ifPresent(entry -> 
	    System.out.println("Vuelo con Mayor recaudación = Código: " + entry.getKey() + ", total: " + entry.getValue()));
        System.out.println("Porcentaje de ocupación por vuelo: " + porcentajeOcupacionPorVuelo);
        System.out.println("Reservas ordenadas por precio: " + reservasOrdenadasPorPrecio);
        System.out.println();
        
        /* MODIFICACION PARA MOSTRAR LOS PRINTS MAS ORDENADOS*/
        System.out.println("Simulador de vuelos 'SALIDA MODIFICADA'\n");
        System.out.println(listadoPersonas);

        System.out.println("\nReservas por vuelo:");          // aquí recorres un Map simple con clave y valor.
        reservasPorVuelo.entrySet().forEach(entry ->       // entrySet(), te da cada pareja clave-valor por separado.
            System.out.println("Vuelo: " + entry.getKey() + " | Total reservas: " + entry.getValue())
        );

        System.out.println("\nPasajeros por nacionalidad:");
        pasajerosPorNacionalidad.entrySet().forEach(entry ->
            System.out.println("Nacionalidad: " + entry.getKey() + " | Pasajeros: " + entry.getValue())
        );                        // entry.getKey(), saca la clave.                   entry.getValue(), saca el valor

        System.out.println("\nTripulantes por cargo:");		// aquí recorres un Map simple con clave y valor.
        tripulantesPorCargo.entrySet().forEach(entry ->		// entrySet(), te da cada pareja clave-valor por separado.
            System.out.println("Cargo: " + entry.getKey() + " | Tripulantes: " + entry.getValue())
        );						  // entry.getKey(), saca la clave.                   entry.getValue(), saca el valor

        System.out.println("\nVuelos por destino y estado:");
        vuelosPorDestinoYEstado.entrySet().forEach(entryDestino -> { // aquí recorres un Map simple con clave y valor.
            System.out.println("Destino: " + entryDestino.getKey()); // esto se imprime primero
            entryDestino.getValue().entrySet().forEach(entryEstado ->  // aquí recorres el Map interno con clave y valor.
                System.out.println("  Estado: " + entryEstado.getKey() + " | Vuelos: " + entryEstado.getValue())// print debajo
            );  //  aquí recorres un Map dentro de otro Map, por eso hay un segundo entrySet()
        });

        System.out.println("\nIngreso total por vuelo:");		  // aquí recorres un Map simple con clave y valor.
        ingresoTotalPorVuelo.entrySet().forEach(entry ->		// entrySet(), te da cada pareja clave-valor por separado.
            System.out.println("Vuelo: " + entry.getKey() + " | Ingreso: " + entry.getValue())
        );							// entry.getKey(), saca la clave.        entry.getValue(), saca el valor

        vueloConMayorRecaudacion.ifPresent(entry ->
            System.out.println("\nVuelo con mayor recaudación = Código: " + entry.getKey() + ", total: " + entry.getValue())
        );

        System.out.println("\nPorcentaje de ocupación por vuelo:");
        porcentajeOcupacionPorVuelo.entrySet().forEach(entry ->
            System.out.println("Vuelo: " + entry.getKey() + " | Ocupación: " + entry.getValue() + "%")
        );

        System.out.println("\nReservas ordenadas por precio:");
        reservasOrdenadasPorPrecio.forEach(System.out::println);  // aquí no hay entrySet() porque eso es una List, no un Map
               
    }
}
/*  Sí, entrySet() te puede dar un formato más claro si lo recorres tú en vez de imprimir el mapa entero de golpe.
Qué tienes ahora
Cuando haces esto:
System.out.println("Pasajeros por nacionalidad: " + pasajerosPorNacionalidad);
Java usa el toString() del Map, y por eso te sale todo en una sola línea con corchetes y llaves internas.
Eso está bien para probar rápido, pero se ve más “bruto” que “presentado”.

Cómo quedaría con entrySet()       Por ejemplo:
												System.out.println("Pasajeros por nacionalidad:");
												pasajerosPorNacionalidad.entrySet().forEach(entry -> 
												    System.out.println(entry.getKey() + " -> " + entry.getValue())
												);
Eso imprime algo más legible como:
ESPAÑOLA -> [Pasajero(...)]
Por qué se ve mejor
    • entrySet(), te da cada pareja clave-valor por separado.                	• entry.getKey(), saca la clave.
    		• entry.getValue(), saca el valor.                       • forEach, aplica una acción a cada entrada del mapa.
Dónde usar entrySet()
Tiene sentido usarlo en las variables que son Map, por ejemplo:
    • reservasPorVuelo     • pasajerosPorNacionalidad           • tripulantesPorCargo            • vuelosPorDestinoYEstado
    • ingresoTotalPorVuelo                  • porcentajeOcupacionPorVuelo
Dónde no hace falta
No hace falta en:
    • listadoPersonas,  porque eso es una List, no un Map.
    • reservasOrdenadasPorPrecio,  porque también es una List.
    • vueloConMayorRecaudacion,  porque eso ya lo estás mostrando con ifPresent(...), que en este caso queda bien.*/