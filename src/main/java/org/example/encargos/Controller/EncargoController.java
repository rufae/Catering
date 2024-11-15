package org.example.encargos.Controller;

import org.example.encargos.Model.Encargo;
import org.example.encargos.Service.EncargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/encargos")
public class EncargoController {

    @Autowired
    private EncargoService encargoService;

    @PostMapping
    public ResponseEntity<Encargo> crearEncargo(@RequestBody Encargo encargo) {
        Encargo nuevoEncargo = encargoService.crearEncargo(encargo);
        return new ResponseEntity<>(nuevoEncargo, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Encargo> listarEncargos() {
        return encargoService.listarEncargos();
    }

    @GetMapping("/cliente/{clienteId}")
    public List<Encargo> listarEncargosPorCliente(@PathVariable Long clienteId) {
        return encargoService.listarEncargosPorCliente(clienteId);
    }

    @PutMapping("/{idencargo}")
    public ResponseEntity<Encargo> actualizarEncargo(@PathVariable Long idencargo, @RequestBody Encargo encargoDetalles) {
        Optional<Encargo> encargoActualizado = encargoService.actualizarEncargo(idencargo, encargoDetalles);
        return encargoActualizado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{idencargo}")
    public ResponseEntity<Void> eliminarEncargo(@PathVariable Long idencargo) {
        if (encargoService.eliminarEncargo(idencargo)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
