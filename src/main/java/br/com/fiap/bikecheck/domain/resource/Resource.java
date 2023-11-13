package br.com.fiap.bikecheck.domain.resource;

import jakarta.ws.rs.core.Response;

public interface Resource<T, U> {
    Response findAll();

    Response findById(U id);

    Response persist(T t);
}
