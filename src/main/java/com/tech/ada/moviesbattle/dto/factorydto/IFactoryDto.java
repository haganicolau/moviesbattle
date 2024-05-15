package com.tech.ada.moviesbattle.dto.factorydto;

/**
 * An interface that abstracts the methods for converting an entity to a DTO and a DTO to an entity.
 * @param <T> DTO to convert
 * @param <E> Entity to convert
 */
public interface IFactoryDto<T, E> {

    public T buildFromEntity(E entity);
    public E buildFromDto(T dto);
}
