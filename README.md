# submodule-base
Módulo base reutilizable para proyectos Spring Boot

Este submódulo provee una arquitectura genérica para evitar duplicación de código en tus proyectos Java/Spring Boot.
Incluye:

- CRUD genérico con DTOs

- Soft delete automático

- Mapeo automático Entity ↔ DTO usando ModelMapper

- Fechas createdAt, updatedAt, deletedAt con JPA callbacks

- Paginación

- Excepciones estándar

- GlobalExceptionHandler listo para producción

- Controlador base para exponer endpoints REST sin repetir código


