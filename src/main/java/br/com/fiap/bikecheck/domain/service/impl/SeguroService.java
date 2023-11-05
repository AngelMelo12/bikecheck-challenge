package br.com.fiap.bikecheck.domain.service.impl;

import br.com.fiap.bikecheck.domain.entity.Seguro;
import br.com.fiap.bikecheck.domain.repository.impl.SeguroRepository;
import br.com.fiap.bikecheck.domain.service.Service;

import java.util.List;

public class SeguroService implements Service<Seguro, Long> {

    private SeguroRepository repository;

    public SeguroService() {
        this.repository = SeguroRepository.build();
    }

    @Override
    public List<Seguro> findAll() {
        return repository.findAll();
    }

    @Override
    public Seguro findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Seguro persist(Seguro seguro) {
        return repository.persist(seguro);
    }
}
