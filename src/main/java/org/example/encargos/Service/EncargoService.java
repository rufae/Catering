package org.example.encargos.Service;

import org.example.encargos.Model.Encargo;
import org.example.encargos.Repository.EncargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EncargoService {

    @Autowired
    private EncargoRepository encargoRepository;

    public Encargo crearEncargo(Encargo encargo) {
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
