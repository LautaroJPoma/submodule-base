package com.submodule.base.service;

import com.submodule.base.entity.BaseEntity;
import com.submodule.base.exception.EntityNotFoundException;
import com.submodule.base.exception.InvalidRequestException;
import com.submodule.base.mapper.BaseMapper;
import com.submodule.base.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * BaseService
 *
 * Servicio genérico que implementa la lógica común de CRUD y paginación
 * para cualquier entidad que extienda BaseEntity.
 *
 * Este servicio:
 *   - Evita duplicar lógica básica en todos los servicios concretos.
 *   - Asegura consistencia en las operaciones (soft delete, update, find).
 *   - Expone métodos reutilizables para capas superiores (controllers).
 *
 */

public class BaseService<T extends BaseEntity, D, ID> {

    protected final BaseRepository<T, ID> repository;
    protected final BaseMapper<T, D> mapper;
    protected final Class<T> entityClass;
    protected final Class<D> dtoClass;

    public BaseService(BaseRepository<T, ID> repository,
                       BaseMapper<T, D> mapper,
                       Class<T> entityClass,
                       Class<D> dtoClass) {
        this.repository = repository;
        this.mapper = mapper;
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    // save(entity): Guarda una entidad en la base de datos.
      public D save(D dto) {

        T entity = mapper.toEntity(dto, entityClass);
        T saved = repository.save(entity);

        return mapper.toDto(saved, dtoClass);
    }
    // findById(id): Devuelve solo entidades activas (no soft-deleted).
    public Optional<D> findById(ID id) {

        if (id == null) {
            throw new InvalidRequestException("ID cannot be null");
        }

        return repository.findActiveById(id)
            .map(entity -> mapper.toDto(entity, dtoClass));
    }

    // findAll(): Lista todas las entidades activas.
     public List<D> findAll() {
        return StreamSupport.stream(repository.findAllActive().spliterator(), false)
            .map(entity -> mapper.toDto(entity, dtoClass))
            .toList();
    }

    //  findAllPaged(page, size): Devuelve una página de entidades activas.
    public Page<D> findAllPaged(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAllActive(pageable)
            .map(entity -> mapper.toDto(entity, dtoClass));
    }

    // update(id, updatedEntity): Actualiza una entidad preservando su ID y comportamiento de timestamps.
    public D update(ID id, D dto) {
    T existing = repository.findActiveById(id)
        .orElseThrow(() -> new EntityNotFoundException(entityClass.getSimpleName(), id));

    mapper.updateEntity(dto, existing);

    T saved = repository.save(existing);
    return mapper.toDto(saved, dtoClass);
}

    // deleteById(id): Soft-delete: marca la entidad como eliminada sin borrarla físicamente.
     public void deleteById(ID id) {
        T entity = repository.findActiveById(id)
            .orElseThrow(() -> new EntityNotFoundException(entityClass.getSimpleName(), id));

        entity.softDelete();

        repository.save(entity);
    }

}

