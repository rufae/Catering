package org.example.encargos.Service;

import org.example.encargos.Model.Cliente;
import org.example.encargos.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> obtenerClientePorId(Long clienteid) {
        return clienteRepository.findById(clienteid);
    }

    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> actualizarCliente(Long clienteid, Cliente clienteDetalles) {
        return clienteRepository.findById(clienteid).map(cliente -> {
            cliente.setNombre(clienteDetalles.getNombre());
            cliente.setApellido(clienteDetalles.getApellido());
            cliente.setDireccion(clienteDetalles.getDireccion());
            cliente.setTelefono(clienteDetalles.getTelefono());
            return clienteRepository.save(cliente);
        });
    }

    public boolean eliminarCliente(Long clienteid) {
        if (clienteRepository.existsById(clienteid)) {
            clienteRepository.deleteById(clienteid);
            return true;
        }
        return false;
    }
}
