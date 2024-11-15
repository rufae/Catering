package org.example.encargos.Controller;

import org.example.encargos.Model.Menu;
import org.example.encargos.Service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/menus")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping
    public List<Menu> listarMenus() {
        return menuService.listarMenus();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Menu> obtenerMenuPorId(@PathVariable Long id) {
        Optional<Menu> menu = menuService.obtenerMenuPorId(id);
        return menu.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Menu> crearMenu(@RequestBody Menu menu) {
        Menu nuevoMenu = menuService.crearMenu(menu);
        return new ResponseEntity<>(nuevoMenu, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Menu> actualizarMenu(@PathVariable Long id, @RequestBody Menu menuDetalles) {
        Optional<Menu> menuActualizado = menuService.actualizarMenu(id, menuDetalles);
        return menuActualizado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMenu(@PathVariable Long id) {
        if (menuService.eliminarMenu(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
