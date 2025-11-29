package com.submodule.base.controller;

import com.submodule.base.entity.BaseEntity;
import com.submodule.base.service.BaseService;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
/**
 * BaseController
 *
 * Controlador REST genérico que expone endpoints CRUD reutilizables
 * para cualquier entidad del sistema.
 *
 * Esta clase elimina la necesidad de repetir lógica básica en cada
 * controlador concreto, asegurando una API uniforme entre módulos.
 *
 * El comportamiento de negocio específico debe implementarse en
 * controladores concretos que extiendan esta clase.
 *
 */

public class BaseController<T extends BaseEntity, D, ID> {

    private final BaseService<T, D, ID> service;

    public BaseController(BaseService<T, D, ID> service) {
        this.service = service;
    }

    // Obtiene una entidad activa por ID.
     @GetMapping("/{id}")
    public ResponseEntity<D> getById(@PathVariable ID id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    // Lista todas las entidades activas.
   @GetMapping
    public Iterable<D> getAll() {
        return service.findAll();
    }

    // Lista entidades activas con paginación.
   @GetMapping("/paged")
    public Page<D> getAllPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return service.findAllPaged(page, size);
    }

    // Crea una nueva entidad.
     @PostMapping
    public D create(@RequestBody D dto) {
        return service.save(dto);
    }

    // Actualiza una entidad existente.
    @PutMapping("/{id}")
    public D update(@PathVariable ID id, @RequestBody D dto) {
        return service.update(id, dto);
    }

    // Soft-delete: marca una entidad como eliminada.
    @DeleteMapping("/{id}")
    public void delete(@PathVariable ID id) {
        service.deleteById(id);
    }
}


