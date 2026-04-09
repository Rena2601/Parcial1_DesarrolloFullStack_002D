/**El model como tal abstrae los objetos de la vida real, permitiendo
 * crear objetos con sus respectivos atributos, este es el unico package que no necesita
 * tener informacion de los otros, puesto que desde aqui los datos van hacia otras partes
 */

package com.example.QuickOrder.model;
/**Los importes de Jakarta permiten hacer validaciones para que los campos a rellenar
*sean obligatorios, mas adelante en el codigo se puede apreciar que arriba de cada atributo
 * va su respectiva anotacion para que esta vigile de alguna forma lo solicitado
*/
import jakarta.validation.constraints.NotNull;
import jakarta .validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PastOrPresent;


/** Estos 4 importes tienen como fin ultimo ahorrar codigo, puesto que automatiza el codigo
 * a la hora de tener que poner los constructores, getter y setter.
 */
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * El java.util.LocalDate permite registrar la fecha completa pero sin especificar la hora
 */
import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor

public class Pedido {

    /**
     * Como se puede ver aqui estan los atributos, y tambien un mensaje predeterminado en
     * caso de que el usuario ingrese algun dato de forma erronea
     */
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
    public LocalDate fechaPedido;
}