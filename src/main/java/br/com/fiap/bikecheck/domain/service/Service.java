package br.com.fiap.bikecheck.domain.service;

import java.util.List;
import java.util.Objects;

public interface Service<T, U> {

    List<T> findAll();

    T findById(U id);

    T persist(T t);

    default boolean valido(String s) {
        return Objects.nonNull(s) && !s.trim().isEmpty();
    }

}