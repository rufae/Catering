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

    // GET @/menus: lista todos los menús
    @GetMapping
    public List<Menu> listarMenus() {
        return menuService.listarMenus();
    }

    // GET @/menus/{id}: muestra el menú correspondiente a ese id
    @GetMapping("/{id}")
    public ResponseEntity<Menu> obtenerMenuPorId(@PathVariable Long id) {
        Optional<Menu> menu = menuService.obtenerMenuPorId(id);
        return menu.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST @/menus: añade un nuevo menú
    @PostMapping
    public ResponseEntity<Menu> crearMenu(@RequestBody Menu menu) {
        Menu nuevoMenu = menuService.crearMenu(menu);
        return new ResponseEntity<>(nuevoMenu, HttpStatus.CREATED);
    }

    // PUT @/menus/{id}: edita un menú específico
    @PutMapping("/{id}")
    public ResponseEntity<Menu> actualizarMenu(@PathVariable Long id, @RequestBody Menu menuDetalles) {
        Optional<Menu> menuActualizado = menuService.actualizarMenu(id, menuDetalles);
        return menuActualizado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE @/menus/{id}: elimina un menú concreto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMenu(@PathVariable Long id) {
        if (menuService.eliminarMenu(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
