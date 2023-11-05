package br.com.fiap.bikecheck.domain.entity;

import java.time.LocalDate;

public class Seguro {

    private Long id;
    private LocalDate dataContratacao;
    private String nomeEmpresa;
    private Cliente cliente;
    private Bicicleta bicicleta;

    public Seguro() {
    }

    public Seguro(Long id, LocalDate dataContratacao, String nomeEmpresa, Cliente cliente, Bicicleta bicicleta) {
        this.id = id;
        this.dataContratacao = dataContratacao;
        this.nomeEmpresa = nomeEmpresa;
        this.cliente = cliente;
        this.bicicleta = bicicleta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(LocalDate dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Bicicleta getBicicleta() {
        return bicicleta;
    }

    public void setBicicleta(Bicicleta bicicleta) {
        this.bicicleta = bicicleta;
    }

    @Override
    public String toString() {
        return "Seguro{" +
                "id=" + id +
                ", dataContratacao=" + dataContratacao +
                ", nomeEmpresa='" + nomeEmpresa + '\'' +
                ", cliente=" + cliente.getNome() +
                ", bicicleta=" + bicicleta.getMarca() +
                '}';
    }
}
