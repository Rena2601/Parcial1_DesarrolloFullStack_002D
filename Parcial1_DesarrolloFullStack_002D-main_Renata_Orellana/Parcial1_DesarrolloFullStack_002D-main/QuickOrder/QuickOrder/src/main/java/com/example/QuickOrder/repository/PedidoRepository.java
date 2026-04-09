package com.example.QuickOrder.repository;
/**El repositorio puede explicarse como la analogía de un estante de libros, el repositorio
 * como tal es el estante, que almacena aquellos objetos que existen gracias a la clase del
 * Model, en este caso el repositorio guarda aquellos objetos que son creados gracias a
 * los atributos y clases del model
 */

/**
 * Para que los archivos se comuniquen entre si es necesario importar las otros packages con
 * sus files
 */

import com.example.QuickOrder.model.Pedido;
import org.springframework.stereotype.Repository;

/**Aqui el importe va con un asterisco, por ende ayuda a usar cualquier funcion que tenga
*java.util :)*/

import java.util.*;

@Repository

public class PedidoRepository {

    /**
     * En este apartado del repositorio se genera una nueva lista para ingresar todos
     * los nuevos pedidos que esten existentes
     */
    private List<Pedido> pedidos = new ArrayList<>();

    /**El metodo siguiente contiene los paramentros necesarios para guardar un objetos con
     * atributos necesarios, de este modo si cumple con los parametros se usa una
     * funcion para agregar a la lista pedidos un nuevo pedido
     */
    public Pedido guardarPedido(Pedido nuevoPedido){
        pedidos.add(nuevoPedido);
        return nuevoPedido;
    }

    /** obtener todos permite tomar la lista existente y retornar como respuesta
     * todos aquellos objetos guardados en la lista del repositorio
    */
    public List<Pedido> obtenerTodos(){
        return pedidos;
    }

    /** Este metodo toma algun id que inserte el usuario y va filtrando aquellos pedido/s que
     * concuerden con el id ingresado, si se encuentra se mostrará
    */
    public Optional<Pedido> buscarPorId(Long id){
        return pedidos.stream()
                .filter (p -> p.getId().equals(id))
                .findFirst();
    }

    /**Para actualizar el pedido se busca por el id y se insertaran nuevos parametros,
     * que seran llamados datos nuevos, despues se simplifica el nombre del pedido por P,
     * entonces va a buscar el pedido que sea desea actualizar por el id, una vez hecho eso,
     * los datos modificados si es que cumplen con las validaciones seran reemplazados por
     * los nuevos datos ingresados
    */
    public void actualizarPedido(Long id, Pedido datosNuevos) {
        for (int P = 0; P < pedidos.size(); P++) {
            if (pedidos.get(P).getId().equals(id)) {
                pedidos.set(P, datosNuevos);
                return;
            }
        }
    }
    /**Metodo que busca el pedido y lo elimina, si es que hay un id
     * concordante, por eso se usa la sentencia if y el parametro del id
    */
    public boolean eliminarPedido(Long id) {
        return pedidos.removeIf(P -> P.getId().equals(id));
    }

}