/*  Vuelo es la ficha de cada vuelo. Guarda sus datos básicos y después servirá para relacionarlo con las reservas. */
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
public class Vuelo {

    private String codigo;
    private String origen;
    private String destino;
    private LocalDate fechaVuelo;
    private Integer capacidadMaxima;
    private EstadoVuelo estado;
}
/** Ejemplo mental de uso:

Vuelo vuelo1 = Vuelo.builder()
    .codigo("IB2034")
    .origen("Madrid")
    .destino("Roma")
    .fechaVuelo(LocalDate.of(2026, 6, 10))
    .capacidadMaxima(180)
    .estado(EstadoVuelo.PROGRAMADO)
    .build();  */