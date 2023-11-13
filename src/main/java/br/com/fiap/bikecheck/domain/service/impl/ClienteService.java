package br.com.fiap.bikecheck.domain.service.impl;

import br.com.fiap.bikecheck.domain.entity.Cliente;
import br.com.fiap.bikecheck.domain.repository.impl.ClienteRepository;
import br.com.fiap.bikecheck.domain.service.Service;

import java.util.List;

public class ClienteService implements Service<Cliente, Long> {

    private final ClienteRepository repository = ClienteRepository.build();

    @Override
    public List<Cliente> findAll() {
        return repository.findAll();
    }

    @Override
    public Cliente findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Cliente persist(Cliente cliente) {
        return repository.persist(cliente);
    }
}
