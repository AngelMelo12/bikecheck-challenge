package br.com.fiap.bikecheck.domain.entity;

import java.time.LocalDate;

public class Cliente {

    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private String endereco;
    private Long cpf;
    private Long telefone;
    private Long telefoneResidencial;
    private String email;

    public Cliente() {
    }

    public Cliente(Long id, String nome, LocalDate dataNascimento, String endereco, Long cpf, Long telefone, Long telefoneResidencial, String email) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.cpf = cpf;
        this.telefone = telefone;
        this.telefoneResidencial = telefoneResidencial;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public Long getTelefone() {
        return telefone;
    }

    public void setTelefone(Long telefone) {
        this.telefone = telefone;
    }

    public Long getTelefoneResidencial() {
        return telefoneResidencial;
    }

    public void setTelefoneResidencial(Long telefoneResidencial) {
        this.telefoneResidencial = telefoneResidencial;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", endereco='" + endereco + '\'' +
                ", cpf=" + cpf +
                ", telefone=" + telefone +
                ", telefoneResidencial=" + telefoneResidencial +
                ", email='" + email + '\'' +
                '}';
    }
}
