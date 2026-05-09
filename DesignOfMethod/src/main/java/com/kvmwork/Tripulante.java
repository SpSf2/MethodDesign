/*Tripulante hereda de Persona y añade lo específico del personal de la aerolínea: cargo, salario, fecha de incorporación 
 * y base operativa. Igual que en tu ejemplo de Empleado extends Persona, aquí metemos herencia + Lombok + @SuperBuilder. */
package com.kvmwork;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Tripulante extends Persona {

    private Cargo cargo;
    private BigDecimal salario;
    private LocalDate fechaIncorporacion;
    private String baseOperativa;
}