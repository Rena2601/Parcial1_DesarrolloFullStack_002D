package com.example.QuickOrder.service;

import com.example.QuickOrder.model.Pedido;
import com.example.QuickOrder.repository.PedidoRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class PedidoService {

    private final PedidoRepository repository;
    public PedidoService(PedidoRepository repository){
        this.repository = repository;
    }

    public List<Pedido> listarTodos(){
        return repository.obtenerTodos();
    }

    public Pedido crear(Pedido pedido){
        return repository.guardarPedido(pedido);
    }

    public void editar(int id, Pedido datosNuevos){
        repository.actualizarPedido(id, datosNuevos);
    }

    public boolean borrar(int id){
        return repository.eliminarPedido(id);
    }

}