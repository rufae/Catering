package org.example.encargos.Service;

import org.example.encargos.Model.Menu;
import org.example.encargos.Repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public List<Menu> listarMenus() {
        return menuRepository.findAll();
    }

    public Optional<Menu> obtenerMenuPorId(Long id) {
        return menuRepository.findById(id);
    }

    public Menu crearMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    public Optional<Menu> actualizarMenu(Long id, Menu menuDetalles) {
        return menuRepository.findById(id).map(menu -> {
            menu.setNombre(menuDetalles.getNombre());
            menu.setDescripcion(menuDetalles.getDescripcion());
            menu.setPrecio(menuDetalles.getPrecio());
            return menuRepository.save(menu);
        });
    }

    public boolean eliminarMenu(Long id) {
        if (menuRepository.existsById(id)) {
            menuRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
