package br.com.fiap.bikecheck.domain.dto;

import br.com.fiap.bikecheck.domain.entity.Cliente;
import br.com.fiap.bikecheck.domain.service.impl.ClienteService;

import java.time.LocalDate;
import java.util.Objects;

public record ClienteDTO(
        Long id,
        String nome,
        LocalDate dataNascimento,
        String endereco,
        Long cpf,
        Long telefone,
        Long telefoneResidencial,
        String email
) {

    static ClienteService clienteService = new ClienteService();

    public static Cliente of(ClienteDTO dto) {

        if (Objects.isNull( dto )) return null;

        if (Objects.nonNull( dto.id )) return clienteService.findById( dto.id );

        Cliente cliente = new Cliente();
        cliente.setId(null);
        cliente.setNome(dto.nome);
        cliente.setDataNascimento(dto.dataNascimento);
        cliente.setEndereco(dto.endereco);
        cliente.setCpf(dto.cpf);
        cliente.setTelefone(dto.telefone);
        cliente.setTelefoneResidencial(dto.telefoneResidencial);
        cliente.setEmail(dto.email);

        return cliente;
    }

    public static ClienteDTO of(Cliente entity) {
        return new ClienteDTO(entity.getId(), entity.getNome(), entity.getDataNascimento(), entity.getEndereco(), entity.getCpf(), entity.getTelefone(), entity.getTelefoneResidencial(), entity.getEmail());
    }
}
