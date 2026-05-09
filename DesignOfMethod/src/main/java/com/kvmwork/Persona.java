/* Primero construimos Persona, porque ahí van los datos comunes de Pasajero y Tripulante: id, nombre, apellidos, 
 * fechaNacimiento, nacionalidad. */
package com.kvmwork;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public class Persona {
	
	    
	    private String id;
	    private String nombre;
	    private String primerApellido;
	    private String segundoApellido;
	    private LocalDate fechaNacimiento;
	    private String nacionalidad;
	}


