package com.insearchofknowledge.bugTracker.generics;

public interface Mapper<E, D> {

    public D convertToDto(E entity);

    public E convertToEntity(D dto);
}
