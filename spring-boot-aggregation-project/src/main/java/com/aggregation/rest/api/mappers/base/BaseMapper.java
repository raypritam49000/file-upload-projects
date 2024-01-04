package com.aggregation.rest.api.mappers.base;

import org.bson.types.ObjectId;

import java.util.List;

public interface BaseMapper<T, E> {
    T toDto(E entity);

    E toEntity(T dto);

    List<T> toDtoList(List<E> entities);

    List<E> toEntityList(List<T> entities);

    default String objectIdToString(ObjectId objectId) {
        return objectId != null ? objectId.toString() : null;
    }

    default ObjectId stringToObjectId(String objectIdString) {
        return objectIdString != null ? new ObjectId(objectIdString) : null;
    }
}