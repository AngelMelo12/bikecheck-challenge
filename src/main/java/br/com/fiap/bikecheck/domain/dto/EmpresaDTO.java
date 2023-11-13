package br.com.fiap.bikecheck.domain.dto;

import br.com.fiap.bikecheck.domain.entity.Empresa;
import br.com.fiap.bikecheck.domain.service.impl.EmpresaService;

import java.util.Objects;

public record EmpresaDTO(
        Long id,
        Long cnpj,
        String nome,
        String endereco,
        String segmentos,
        Long telefone,
        String email
) {

    static EmpresaService empresaService = new EmpresaService();

    public static Empresa of(EmpresaDTO dto) {

        if (Objects.isNull( dto )) return null;

        if (Objects.nonNull( dto.id )) return empresaService.findById( dto.id );

        Empresa empresa = new Empresa();
        empresa.setId(null);
        empresa.setCnpj(dto.cnpj);
        empresa.setNome(dto.nome);
        empresa.setEndereco(dto.endereco);
        empresa.setSegmentos(dto.segmentos);
        empresa.setTelefone(dto.telefone);
        empresa.setEmail(dto.email);

        return empresa;
    }

    public static EmpresaDTO of(Empresa entity) {
        return new EmpresaDTO(entity.getId(), entity.getCnpj(), entity.getNome(), entity.getEndereco(), entity.getSegmentos(), entity.getTelefone(), entity.getEmail());
    }
}
