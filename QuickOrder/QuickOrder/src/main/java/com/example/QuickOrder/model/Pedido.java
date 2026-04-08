package com.example.QuickOrder.model;

import jakarta.validation.constraints.NotNull;
import jakarta .validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PastOrPresent;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor

public class Pedido {
    @Positive(message = "El id debe ser positivo")
    public Long id;
    @NotBlank(message = "El nombre no puede estar vacío")
    public String nombreCliente;
    @NotBlank(message = "La descripción no puede estar vacía")
    public String descripcion;
    @NotNull(message = "El estado es obligatorio")
    public Estado estado;
    @NotNull(message = "El tipo de pedido es obligatorio")
    public TipoPedido tipoPedido;
    @Positive(message = "El monto total no puede ser negativo")
    public Double montoTotal;
    @PastOrPresent @NotNull (message = "Error: El campo esta vacío o en una fecha que no existe")
    public LocalDate fechaPedido;
}