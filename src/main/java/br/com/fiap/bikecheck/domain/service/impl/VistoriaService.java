package br.com.fiap.bikecheck.domain.service.impl;

import br.com.fiap.bikecheck.domain.entity.Bicicleta;
import br.com.fiap.bikecheck.domain.entity.Vistoria;
import br.com.fiap.bikecheck.domain.repository.impl.VistoriaRepository;
import br.com.fiap.bikecheck.domain.service.Service;

import java.util.List;

public class VistoriaService implements Service<Vistoria, Long> {

    private VistoriaRepository repository;

    public VistoriaService() {
        this.repository = VistoriaRepository.build();
    }

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
        if (vistoriaValidaParaBicicleta(vistoria)) {
            Bicicleta bicicleta = vistoria.getBicicleta();
            bicicleta.adicionaVistoria(vistoria);
            vistoria.setBicicleta(bicicleta);
        }

        return repository.persist(vistoria);
    }

    public List<Vistoria> findByBicicleta(Long bicicletaId) {
        return repository.findByBicicleta(bicicletaId);
    }

    private boolean vistoriaValidaParaBicicleta(Vistoria vistoria) {
        Bicicleta bicicleta = vistoria.getBicicleta();
        return !bicicleta.possuiVistoria(vistoria) &&
                !bicicleta.possuiFotosDaVistoria(vistoria);
    }

}
