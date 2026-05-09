/* Reserva es una clase clave porque une dos objetos del sistema: un Pasajero y un Vuelo. Es decir, aquí se relacionan clases
 *  entre sí.
 */
package com.kvmwork;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public class Reserva {

    private Pasajero pasajero; // aquí se guarda una referencia a un objeto Pasajero.
    private Vuelo vuelo;       // aquí se guarda una referencia a un objeto Vuelo. O sea, una reserva sabe para qué vuelo es.
    private String numeroAsiento;
    private BigDecimal precio;
}
/**  En humano:  Reserva funciona como un puente: conecta un pasajero con un vuelo. No guarda solo ids sueltos, sino objetos
 *  completos. Eso luego te deja hacer cosas como reserva.getVuelo().getCodigo() o reserva.getPasajero().getNombre().
 *  Ejemplo mental:

 * Reserva r1 = Reserva.builder()
    .pasajero(p1)   esto mete dentro de la reserva el objeto pasajero que ya creaste antes.
    .vuelo(v1)      esto mete dentro de la reserva el objeto vuelo.
    .numeroAsiento("12A")
    .precio(new BigDecimal("199.99"))
    .build();
 */