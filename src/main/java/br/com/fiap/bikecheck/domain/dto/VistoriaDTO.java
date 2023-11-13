package br.com.fiap.bikecheck.domain.dto;

import br.com.fiap.bikecheck.domain.entity.Bicicleta;
import br.com.fiap.bikecheck.domain.entity.Seguro;
import br.com.fiap.bikecheck.domain.entity.Vistoria;
import br.com.fiap.bikecheck.domain.service.impl.BicicletaService;
import br.com.fiap.bikecheck.domain.service.impl.ClienteService;
import br.com.fiap.bikecheck.domain.service.impl.SeguroService;
import br.com.fiap.bikecheck.domain.service.impl.VistoriaService;

import java.time.LocalDate;
import java.util.Objects;

public record VistoriaDTO(
        Long id,
        byte[] foto,
        BicicletaDTO bicicleta,
        SeguroDTO seguro
) {

    static BicicletaService bicicletaService = new BicicletaService();
    static SeguroService seguroService = new SeguroService();

    static VistoriaService vistoriaService = new VistoriaService();

    public static Vistoria of(VistoriaDTO dto) {

        if (Objects.isNull( dto )) return null;

        if (Objects.nonNull( dto.id )) return vistoriaService.findById(dto.id);

        Vistoria vistoria = new Vistoria();
        vistoria.setId(null);
        vistoria.setFoto(dto.foto);

        var bicicleta = Objects.nonNull(dto.bicicleta) && (Objects.nonNull(dto.bicicleta.id())) ?
                bicicletaService.findById(dto.bicicleta.id()) : null;

        vistoria.setBicicleta(bicicleta);

        var seguro = Objects.nonNull(dto.seguro) && (Objects.nonNull(dto.seguro.id())) ?
                seguroService.findById(dto.seguro.id()) : null;

        vistoria.setSeguro(seguro);

        return vistoria;
    }

    public static VistoriaDTO of(Vistoria entity) {
        return new VistoriaDTO(entity.getId(), entity.getFoto(), BicicletaDTO.of(entity.getBicicleta()), SeguroDTO.of(entity.getSeguro()));
    }
}
