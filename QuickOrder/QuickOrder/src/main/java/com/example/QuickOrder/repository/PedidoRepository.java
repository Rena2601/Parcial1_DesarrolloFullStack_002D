package com.example.QuickOrder.repository;

import com.example.QuickOrder.model.Pedido;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository

public class PedidoRepository {

    private List<Pedido> pedidos = new ArrayList<>();

    public Pedido guardarPedido(Pedido nuevoPedido){
        pedidos.add(nuevoPedido);
        return nuevoPedido;
    }

    public List<Pedido> obtenerTodos(){
        return pedidos;
    }

    public void actualizarPedido(int id, Pedido datosNuevos) {
        for (int Pedido = 0; Pedido < pedidos.size(); Pedido++) {
            if (pedidos.get(Pedido).getId() == id) {
                pedidos.set(Pedido, datosNuevos);
            }
        }
    }

    public boolean eliminarPedido(int id){
        return pedidos.removeIf(nuevoPedido -> nuevoPedido.getId()==id);
    }

}