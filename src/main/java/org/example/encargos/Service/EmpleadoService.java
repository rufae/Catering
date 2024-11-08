package org.example.encargos.Service;

import org.example.encargos.Model.Empleado;
import org.example.encargos.Repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public Optional<Empleado> obtenerEmpleadoPorId(Long id) {
        return empleadoRepository.findById(id);
    }

    public boolean eliminarEmpleado(Long idEmp) {
        if (empleadoRepository.existsById(idEmp)) {
            empleadoRepository.deleteById(idEmp);
            return true;
        }
        return false;
    }

    public Empleado crearEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    public List<Empleado> listarEmpleados() {
        return empleadoRepository.findAll();
    }

    public Optional<Empleado> actualizarEmpleado(Long idEmp, Empleado empleadoDetalles) {
        return empleadoRepository.findById(idEmp).map(empleado -> {
            empleado.setNombre(empleadoDetalles.getNombre());
            empleado.setApellido(empleadoDetalles.getApellido());
            empleado.setDireccion(empleadoDetalles.getDireccion());
            empleado.setTelefono(empleadoDetalles.getTelefono());
            return empleadoRepository.save(empleado);
        });
    }
}
