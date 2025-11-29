package com.submodule.base.mapper;

import org.modelmapper.ModelMapper;

/**
 * BaseMapper
 *
 * Mapper genérico basado en ModelMapper para convertir entre
 * entidades (E) y DTOs (D) sin necesidad de escribir código repetitivo.
 *
 * Este mapper funciona para la mayoría de los casos estándar. Para
 * mapeos complejos o reglas de negocio específicas, los servicios
 * concretos pueden extender esta clase o definir mappers propios.
 *
 */

public class BaseMapper<E, D> {

    private final ModelMapper modelMapper = new ModelMapper();

    // Convierte una entidad en un DTO.
    public D toDto(E entity, Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

    // Convierte un DTO en una entidad.
    public E toEntity(D dto, Class<E> entityClass) {
        return modelMapper.map(dto, entityClass);
    }

     public void updateEntity(D dto, E entity) {
        modelMapper.map(dto, entity);
    }
}
