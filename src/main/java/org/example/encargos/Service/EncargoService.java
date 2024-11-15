package org.example.encargos.Service;

import org.example.encargos.Model.Cliente;
import org.example.encargos.Model.Encargo;
import org.example.encargos.Model.Menu;
import org.example.encargos.Repository.ClienteRepository;
import org.example.encargos.Repository.EncargoRepository;
import org.example.encargos.Repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EncargoService {

    @Autowired
    private EncargoRepository encargoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private MenuRepository menuRepository;

    public Encargo crearEncargo(Encargo encargo) {

        if (encargo.getCliente().getId() == null || encargo.getCliente().getId() <= 0) {
            throw new IllegalArgumentException("ID de cliente no válido: " + encargo.getCliente().getId());
        }
        // Buscar cliente por ID
        Cliente cliente = clienteRepository.findById(encargo.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + encargo.getCliente().getId()));

        // Buscar menú por ID
        Menu menu = menuRepository.findById(encargo.getMenu().getIdMenu())
                .orElseThrow(() -> new RuntimeException("Menú no encontrado con ID: " + encargo.getMenu().getIdMenu()));

        // Asociar cliente y menú al encargo
        encargo.setCliente(cliente);
        encargo.setMenu(menu);

        // Guardar y devolver el encargo
        return encargoRepository.save(encargo);
    }

    public List<Encargo> listarEncargos() {
        return encargoRepository.findAll();
    }

    public List<Encargo> listarEncargosPorCliente(Long clienteId) {
        return encargoRepository.findByClienteId(clienteId);
    }

    public Optional<Encargo> actualizarEncargo(Long idEncargo, Encargo encargoDetalles) {
        return encargoRepository.findById(idEncargo).map(encargo -> {
            encargo.setMenu(encargoDetalles.getMenu());
            encargo.setFechaEncargo(encargoDetalles.getFechaEncargo());
            encargo.setFechaEntrega(encargoDetalles.getFechaEntrega());
            return encargoRepository.save(encargo);
        });
    }

    public boolean eliminarEncargo(Long idEncargo) {
        if (encargoRepository.existsById(idEncargo)) {
            encargoRepository.deleteById(idEncargo);
            return true;
        }
        return false;
    }
}
