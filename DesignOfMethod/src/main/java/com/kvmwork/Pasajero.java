/*  Pasajero hereda de Persona y añade datos específicos de pasajeros: pasaporte y categoriaViajero. Usa @SuperBuilder para
 *  acceder a campos del padre.*/
package com.kvmwork;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Pasajero extends Persona {

    private String pasaporte;
    private String categoriaViajero; // "ECONOMICA", "BUSINESS", "PRIMERA"
}