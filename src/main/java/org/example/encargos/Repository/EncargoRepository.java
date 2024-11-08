package org.example.encargos.Repository;

import org.example.encargos.Model.Encargo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EncargoRepository extends JpaRepository<Encargo, Long> {
    List<Encargo> findByClienteId(Long clienteId);
}
