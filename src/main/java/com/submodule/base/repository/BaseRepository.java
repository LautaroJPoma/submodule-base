package com.submodule.base.repository;

import com.submodule.base.entity.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

/**
 * BaseRepository
 *
 * Repositorio genérico para todas las entidades del sistema.
 * Extiende CrudRepository y PagingAndSortingRepository para
 * proporcionar operaciones CRUD y soporte de paginación sin
 * necesidad de crear lógica repetitiva en cada repositorio específico.
 *
 */

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, ID>
        extends CrudRepository<T, ID>,
        PagingAndSortingRepository<T, ID> {

    // findActiveById: Obtiene solo entidades activas (no eliminadas).
    @Query("SELECT e FROM #{#entityName} e WHERE e.id = ?1 AND e.deletedAt IS NULL")
    Optional<T> findActiveById(ID id);

    // findAllActive: Obtiene todas las entidades activas.
    @Query("SELECT e FROM #{#entityName} e WHERE e.deletedAt IS NULL")
    Iterable<T> findAllActive();

    // findAllActive(Pageable): Obtiene entidades activas con paginación.
    @Query("SELECT e FROM #{#entityName} e WHERE e.deletedAt IS NULL")
    Page<T> findAllActive(Pageable pageable);
}

