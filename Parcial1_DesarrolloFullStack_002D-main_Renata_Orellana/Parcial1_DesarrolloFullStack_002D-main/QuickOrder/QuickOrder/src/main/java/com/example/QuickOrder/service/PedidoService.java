/**El service contiene la logica del negocio y es sumamente importante que este
 * bien hecho, porque es donde se establecen las validaciones correspondientes, los limites
 * del sistema y media los servicios que se prestaran al usuario al tener el poder de manejar
 * los datos que estan interconectados entre los files
 */

package com.example.QuickOrder.service;

/**Los primeros 4 importes son necesarios para que este file tenga los datos necesarios
 * clases, tambien se usa el spring frame work para que facilite las herramientas de codigo
 * a la hora de armar el service, y los ultimos 4 importes sirven para organizar el tema de
 * las fechas, y recolectar los objetos filtrados por el usuario
 */

import com.example.QuickOrder.model.Pedido;
import com.example.QuickOrder.model.Estado;
import com.example.QuickOrder.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class PedidoService {
    /**El repositorio se establece como final para evitar posible problemas
     * asi mismo se pone el next Id como 1L, señalando el inicio de los id que se iran
     * generando de forma automatica para evitar redundancia de datos
*/
    private final PedidoRepository repository;
    private Long nextId = 1L;

/**En los siguientes 3 conjuntos de codigo que se ven: el PedidoService permite
 * generar el constructor o traerlo por asi decirlo, el listarTodos, agarra todos los objetos del
 * repository y retorna la lista completa actual, y obtener por Id permite buscar un Id, insertarlo
 * para que el sistema te lo traiga y te muestre los datos relacionados
    */
    public PedidoService(PedidoRepository repository){
        this.repository = repository;
    }

    public List<Pedido> listarTodos(){
        return repository.obtenerTodos();
    }

    public Optional<Pedido> obtenerPorId(Long id){
        return repository.buscarPorId(id);
    }

/**El pedido crear como dice el nombre crea un nuevo pedido que setea el Id, lo agrega
 * setea la fecha, y los atributos que se otorgan tambien al poner el metodo del repositorio
 * que es guardar pedido
    */
    public Pedido crear(Pedido pedido){
        pedido.setId(nextId++);
        pedido.setFechaPedido(LocalDate.now());
        return repository.guardarPedido(pedido);
    }


    /**este metodo lo que permite es editar un pedido existente, para ello busca el id
     * del objeto guardado en el repositorio y reemplaza los datos relacionados al id
     * con los datos nuevos como se ve en la linea 66
     */
    public boolean editar(Long id, Pedido datosNuevos){
        Optional<Pedido> existente = repository.buscarPorId(id);
        if(existente.isPresent()){
            datosNuevos.setId(id);
            datosNuevos.setFechaPedido(existente.get().getFechaPedido());
            repository.actualizarPedido(id, datosNuevos);
            return true;
        }
        return false;
    }

    /**metodo que permite borrar un objeto por id
     * como se ve el Service llama el metodo para borrar pedidos desde el service
    */
    public boolean borrar(Long id){
        return repository.eliminarPedido(id);
    }

    /**Al igual que el metodo anterior, llama el metodo de obtener todos del repositorio
     * permitiendo poder ir recolectando los objetos que cumplan con los filtros establecidos
     * en este caso puntual permite filtrar por estado ya que asi esta hecho en los parametros
     * :v
    */
    public List<Pedido> filtrarPorEstado(Estado estado){
        return repository.obtenerTodos().stream()
                .filter(P -> P.getEstado()==estado)
                .collect(Collectors.toList());
    }

}