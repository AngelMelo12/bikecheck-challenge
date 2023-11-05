package br.com.fiap.bikecheck.domain.service.impl;

import br.com.fiap.bikecheck.domain.entity.Empresa;
import br.com.fiap.bikecheck.domain.repository.impl.EmpresaRepository;
import br.com.fiap.bikecheck.domain.service.Service;

import java.util.List;

public class EmpresaService implements Service<Empresa, Long> {

    private EmpresaRepository repository;

    public EmpresaService() {
        this.repository = EmpresaRepository.build();
    }

    @Override
    public List<Empresa> findAll() {
        return repository.findAll();
    }

    @Override
    public Empresa findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Empresa persist(Empresa empresa) {
        return repository.persist(empresa);
    }
}
