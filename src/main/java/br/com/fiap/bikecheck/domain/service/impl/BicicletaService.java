package br.com.fiap.bikecheck.domain.service.impl;

import br.com.fiap.bikecheck.domain.entity.Bicicleta;
import br.com.fiap.bikecheck.domain.repository.impl.BicicletaRepository;
import br.com.fiap.bikecheck.domain.service.Service;

import java.util.List;

public class BicicletaService implements Service<Bicicleta, Long> {

    private final BicicletaRepository repository = BicicletaRepository.build();

    @Override
    public List<Bicicleta> findAll() {
        return repository.findAll();
    }

    @Override
    public Bicicleta findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Bicicleta persist(Bicicleta bicicleta) {
        return repository.persist(bicicleta);
    }

    private boolean bicicletaValida(Bicicleta bicicleta) {
        if (bicicleta.getValor() < 30000.0)
            return false;
        else if (bicicleta.getMarca() == null || bicicleta.getMarca().isEmpty())
            return false;
        else return bicicleta.getFotos() != null;
    }
}
