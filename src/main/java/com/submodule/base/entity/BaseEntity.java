package com.submodule.base.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * BaseEntity
 *
 * Clase base para todas las entidades del sistema.
 * El objetivo es evitar código repetitivo en cada entidad y estandarizar
 * el comportamiento común de persistencia.
 *
 */

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    // createdAt: Se setea automáticamente al crear la entidad
    // updatedAt: Se setea automáticamente al crear la entidad
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // updatedAt: Se setea automáticamente al actualizar la entidad
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // softDelete(): marca la entidad como eliminada
    public void softDelete() {
        this.deletedAt = LocalDateTime.now();
    }

    // isDeleted(): indica si la entidad está marcada como eliminada
    public boolean isDeleted() {
        return deletedAt != null;
    }
}
