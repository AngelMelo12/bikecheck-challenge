package br.com.fiap.bikecheck.domain.dto;

import br.com.fiap.bikecheck.domain.entity.Seguro;
import br.com.fiap.bikecheck.domain.service.impl.BicicletaService;
import br.com.fiap.bikecheck.domain.service.impl.ClienteService;
import br.com.fiap.bikecheck.domain.service.impl.SeguroService;

import java.time.LocalDate;
import java.util.Objects;

public record SeguroDTO(
        Long id,
        LocalDate dataContratacao,
        String nomeEmpresa,
        ClienteDTO cliente,
        BicicletaDTO bicicleta
) {

    static BicicletaService bicicletaService = new BicicletaService();
    static ClienteService clienteService = new ClienteService();
    static SeguroService seguroService = new SeguroService();

    public static Seguro of(SeguroDTO dto) {

        if (Objects.isNull( dto )) return null;

        if (Objects.nonNull( dto.id )) return seguroService.findById(dto.id);

        Seguro seguro = new Seguro();
        seguro.setId(null);
        seguro.setDataContratacao(dto.dataContratacao);
        seguro.setNomeEmpresa(dto.nomeEmpresa);

        var cliente = Objects.nonNull(dto.cliente) && (Objects.nonNull(dto.cliente.id())) ?
                clienteService.findById(dto.cliente.id()) : null;

        seguro.setCliente(cliente);

        var bicicleta = Objects.nonNull(dto.bicicleta) && (Objects.nonNull(dto.bicicleta.id())) ?
                bicicletaService.findById(dto.bicicleta.id()) : null;

        seguro.setBicicleta(bicicleta);

        return seguro;
    }

    public static SeguroDTO of(Seguro entity) {
        return new SeguroDTO(entity.getId(), entity.getDataContratacao(), entity.getNomeEmpresa(), ClienteDTO.of(entity.getCliente()), BicicletaDTO.of(entity.getBicicleta()));
    }
}
