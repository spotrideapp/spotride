package com.spotride.spotride.model.common;

import java.util.List;

/**
 * A generic facade interface for performing CRUD operations on models.
 *
 * @param <T> the type of the response DTO
 * @param <C> the type of the create request DTO
 * @param <U> the type of the update request DTO
 */
public interface ModelFacade<T, C, U> {

    /**
     * Retrieve all entities.
     *
     * @return a list of all response DTOs
     */
    List<T> getAll();

    /**
     * Retrieve an entity by its ID.
     *
     * @param id the ID of the entity
     * @return the response DTO of the entity, or null if not found
     */
    T getById(Long id);

    /**
     * Create a new entity.
     *
     * @param createRequest the create request DTO
     * @return the created entity's response DTO
     */
    T create(C createRequest);

    /**
     * Update an existing entity.
     *
     * @param id the ID of the entity to update
     * @param updateRequest the update request DTO
     * @return the updated entity's response DTO
     */
    T update(Long id, U updateRequest);

    /**
     * Delete an entity by its ID.
     *
     * @param id the ID of the entity to delete
     */
    void delete(Long id);
}
