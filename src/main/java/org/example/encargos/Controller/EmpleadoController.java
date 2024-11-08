package org.example.encargos.Controller;

import org.example.encargos.Model.Empleado;
import org.example.encargos.Service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    // GET @/empleados/{id}: obtiene el empleado según el id
    @GetMapping("/{id}")
    public ResponseEntity<Empleado> obtenerEmpleadoPorId(@PathVariable Long id) {
        Optional<Empleado> empleado = empleadoService.obtenerEmpleadoPorId(id);
        return empleado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE @/empleados/{idemp}: elimina un empleado específico
    @DeleteMapping("/{idemp}")
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable Long idemp) {
        if (empleadoService.eliminarEmpleado(idemp)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // POST @/empleados: crea un nuevo empleado
    @PostMapping
    public ResponseEntity<Empleado> crearEmpleado(@RequestBody Empleado empleado) {
        Empleado nuevoEmpleado = empleadoService.crearEmpleado(empleado);
        return new ResponseEntity<>(nuevoEmpleado, HttpStatus.CREATED);
    }

    // GET @/empleados: lista todos los empleados que hay en la empresa
    @GetMapping
    public List<Empleado> listarEmpleados() {
        return empleadoService.listarEmpleados();
    }

    // PUT @/empleados/{idemp}: actualiza los datos de un empleado
    @PutMapping("/{idemp}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Long idemp, @RequestBody Empleado empleadoDetalles) {
        Optional<Empleado> empleadoActualizado = empleadoService.actualizarEmpleado(idemp, empleadoDetalles);
        return empleadoActualizado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
