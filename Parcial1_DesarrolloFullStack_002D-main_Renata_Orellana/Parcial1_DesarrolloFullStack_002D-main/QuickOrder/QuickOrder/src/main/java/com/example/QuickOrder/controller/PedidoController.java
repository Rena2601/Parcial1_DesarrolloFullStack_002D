package com.example.QuickOrder.controller;
/**Esta es la parte interesante ya que con esto permite que los datos viajen de un lugar a otro
 *segun lo solicite el usuario, es importante porque le hace seguimiento a los datos que se
 * manejan en postman, ya que mappea los pedidos y sus id's, es por esto que tambien requiere
 * que las rutas de dichas variables sean admitidas por el controller, para que haga que los datos
 * viajen sin perderse ni tener problemas.
 * En cuanto al codigo se ven los importes de las demas clases, y otros importes del spring frame
 * work que son importantisimos para lograr un correcto uso desde afuera, que es el uso de los
 * usuarios
 */

import com.example.QuickOrder.model.Estado;
import com.example.QuickOrder.model.Pedido;
import com.example.QuickOrder.service.PedidoService;

import jakarta.validation.Valid;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**Estas anotaciones sirven para poner el mapeo sobre el codigo que sera escrito y el REST
 * que tambien es importante para que el controller haga su trabajo
 */
@RestController
@RequestMapping("/pedidos")

public class PedidoController {


    /**el private final ayuda al encapsulamiento de datos correctamente y los
     * constructores que se ven mas abajo tambien ayudan a que se administre mejor
     * el intercambio de datos entre packages
     */
    private final PedidoService service;

    public PedidoController(PedidoService service){
        this.service = service;
    }


    /**Aqui es donde entra todo lo que es mapeo en Postman, el Get mapping sirve para los metodos get,
     * como el de listar, obtener objetos identificados por el id y el ultimo metodo get que esta al final
     * en la linea 80 que es el de conseguir aquellos objetos filtrados por el estado del pedido
     */
    @GetMapping
    public List<Pedido> listar(){
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido>obtener(@PathVariable Long id){
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    /**El post mapping permite el mapeo de la creacion de los objetos, entonces se llama el metodo del
     * service que fue importado para completar las acciones
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido crear(@Valid@RequestBody Pedido pedido){
        return service.crear(pedido);
    }

    /**El put lo puse porque me sirve para cambiar parcial o totalmente los valores de los atributos de un objeto
     * entonces en el postman al poner le metodo put permite el mapeo del id para editarlo
     */
    @PutMapping("/{id}")
    public ResponseEntity<Pedido> actualizar(@PathVariable Long id,@Valid @RequestBody Pedido pedido) {
        if (service.editar(id, pedido)) {
            return ResponseEntity.ok(pedido);
        }
        return ResponseEntity.notFound().build();
    }


    /** Este es similar, lo que hace es llamar al metodo del service mapeando el id, encontrandola y posteriormente
     * eliminando el objeto encontrado del repositorio, en este caso el pedido
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id){
        service.borrar(id);
    }

    @GetMapping("/estado/{estado}")
    public List<Pedido> listarPorEstado(@PathVariable Estado estado){
        return service.filtrarPorEstado(estado);
    }

}