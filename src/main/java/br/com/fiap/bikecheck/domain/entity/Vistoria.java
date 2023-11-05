package br.com.fiap.bikecheck.domain.entity;

import java.util.Arrays;

public class Vistoria {

    private Long id;
    private byte[] foto;
    private Bicicleta bicicleta;
    private Seguro seguro;
    private Cliente cliente;

    public Vistoria() {
    }

    public Vistoria(Long id, byte[] foto, Bicicleta bicicleta, Seguro seguro, Cliente cliente) {
        this.id = id;
        this.foto = foto;
        this.bicicleta = bicicleta;
        this.seguro = seguro;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public Bicicleta getBicicleta() {
        return bicicleta;
    }

    public void setBicicleta(Bicicleta bicicleta) {
        this.bicicleta = bicicleta;
    }

    public Seguro getSeguro() {
        return seguro;
    }

    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Vistoria{" +
                "id=" + id +
                ", foto=" + Arrays.toString(foto) +
                ", bicicleta=" + bicicleta.getMarca() +
                ", seguro=" + seguro.getId() +
                ", cliente=" + cliente.getNome() +
                '}';
    }
}
