package br.com.fiap.bikecheck.domain.dto;

import br.com.fiap.bikecheck.domain.entity.Bicicleta;
import br.com.fiap.bikecheck.domain.service.impl.BicicletaService;

import java.time.LocalDate;
import java.util.Objects;

public record BicicletaDTO(
        Long id,
        String marca,
        LocalDate dataModelo,
        Double valor,
        String cor,
        String descricaoPecas,
        byte[] fotos
) {

    static BicicletaService bicicletaService = new BicicletaService();

    public static Bicicleta of(BicicletaDTO dto) {

        if (Objects.isNull( dto )) return null;

        if (Objects.nonNull( dto.id )) return bicicletaService.findById( dto.id );

        Bicicleta bicicleta = new Bicicleta();
        bicicleta.setId( null );
        bicicleta.setMarca(dto.marca);
        bicicleta.setDataModelo(dto.dataModelo);
        bicicleta.setValor(dto.valor);
        bicicleta.setCor(dto.cor);
        bicicleta.setDescricaoPecas(dto.descricaoPecas);
        bicicleta.setFotos(dto.fotos);

        return bicicleta;
    }

    public static BicicletaDTO of(Bicicleta entity) {
        return new BicicletaDTO(entity.getId(), entity.getMarca(), entity.getDataModelo(), entity.getValor(), entity.getCor(), entity.getDescricaoPecas(), entity.getFotos());
    }
}
