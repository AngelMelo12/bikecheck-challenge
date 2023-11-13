package br.com.fiap.bikecheck.domain.entity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Bicicleta {

    private Long id;
    private String marca;
    private LocalDate dataModelo;
    private Double valor;
    private String cor;
    private String descricaoPecas;
    private byte[] fotos;

    public Bicicleta() {
    }

    public Bicicleta(Long id, String marca, LocalDate dataModelo, Double valor, String cor, String descricaoPecas, byte[] fotos) {
        this.id = id;
        this.marca = marca;
        this.dataModelo = dataModelo;
        this.valor = valor;
        this.cor = cor;
        this.descricaoPecas = descricaoPecas;
        this.fotos = fotos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public LocalDate getDataModelo() {
        return dataModelo;
    }

    public void setDataModelo(LocalDate dataModelo) {
        this.dataModelo = dataModelo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getDescricaoPecas() {
        return descricaoPecas;
    }

    public void setDescricaoPecas(String descricaoPecas) {
        this.descricaoPecas = descricaoPecas;
    }

    public byte[] getFotos() {
        return fotos;
    }

    public void setFotos(byte[] fotos) {
        this.fotos = fotos;
    }

    @Override
    public String toString() {
        return "Bicicleta{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", dataModelo=" + dataModelo +
                ", valor=" + valor +
                ", cor='" + cor + '\'' +
                ", descricaoPecas='" + descricaoPecas + '\'' +
                ", fotos=" + Arrays.toString(fotos) +
                '}';
    }
}
