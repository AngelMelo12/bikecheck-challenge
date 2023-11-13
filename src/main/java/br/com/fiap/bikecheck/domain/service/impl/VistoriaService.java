package br.com.fiap.bikecheck.domain.service.impl;

import br.com.fiap.bikecheck.domain.entity.Vistoria;
import br.com.fiap.bikecheck.domain.repository.impl.VistoriaRepository;
import br.com.fiap.bikecheck.domain.service.Service;

import java.util.List;

public class VistoriaService implements Service<Vistoria, Long> {

    private final VistoriaRepository repository = VistoriaRepository.build();

    @Override
    public List<Vistoria> findAll() {
        return repository.findAll();
    }

    @Override
    public Vistoria findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Vistoria persist(Vistoria vistoria) {
        return repository.persist(vistoria);
    }

    public List<Vistoria> findBySeguro(Long idSeguro) {
        return repository.findBySeguro(idSeguro);
    }
}
