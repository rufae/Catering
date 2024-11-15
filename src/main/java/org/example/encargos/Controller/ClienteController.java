package org.example.encargos.Controller;

import org.example.encargos.Model.Cliente;
import org.example.encargos.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }

    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        Cliente nuevoCliente = clienteService.crearCliente(cliente);
        return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
    }

    @PutMapping("/{clienteid}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long clienteid, @RequestBody Cliente clienteDetalles) {
        Optional<Cliente> clienteActualizado = clienteService.actualizarCliente(clienteid, clienteDetalles);
        return clienteActualizado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{clienteid}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Long clienteid) {
        Optional<Cliente> cliente = clienteService.obtenerClientePorId(clienteid);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{clienteid}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long clienteid) {
        if (clienteService.eliminarCliente(clienteid)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
