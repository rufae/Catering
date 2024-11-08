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

    // POST @/encargos: crea un nuevo encargo
    @PostMapping
    public ResponseEntity<Encargo> crearEncargo(@RequestBody Encargo encargo) {
        Encargo nuevoEncargo = encargoService.crearEncargo(encargo);
        return new ResponseEntity<>(nuevoEncargo, HttpStatus.CREATED);
    }

    // GET @/encargos: lista todos los encargos
    @GetMapping
    public List<Encargo> listarEncargos() {
        return encargoService.listarEncargos();
    }

    // GET @/encargos/cliente/{clienteId}: lista los encargos de un cliente específico
    @GetMapping("/cliente/{clienteId}")
    public List<Encargo> listarEncargosPorCliente(@PathVariable Long clienteId) {
        return encargoService.listarEncargosPorCliente(clienteId);
    }

    // PUT @/encargos/{idencargo}: actualiza un encargo
    @PutMapping("/{idencargo}")
    public ResponseEntity<Encargo> actualizarEncargo(@PathVariable Long idencargo, @RequestBody Encargo encargoDetalles) {
        Optional<Encargo> encargoActualizado = encargoService.actualizarEncargo(idencargo, encargoDetalles);
        return encargoActualizado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE @/encargos/{idencargo}: borra un encargo de la base de datos
    @DeleteMapping("/{idencargo}")
    public ResponseEntity<Void> eliminarEncargo(@PathVariable Long idencargo) {
        if (encargoService.eliminarEncargo(idencargo)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
