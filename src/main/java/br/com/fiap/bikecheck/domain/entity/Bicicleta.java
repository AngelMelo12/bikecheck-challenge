package br.com.fiap.bikecheck.domain.entity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Bicicleta {

    private Long id;
    private String marca;
    private LocalDate dataModelo;
    private Double valor;
    private String cor;
    private String descricaoPecas;
    private byte[] fotos;
    private List<Vistoria> vistorias;
    private Cliente cliente;

    public Bicicleta() {
    }

    public Bicicleta(Long id, String marca, LocalDate dataModelo, Double valor, String cor, String descricaoPecas, byte[] fotos, List<Vistoria> vistorias, Cliente cliente) {
        this.id = id;
        this.marca = marca;
        this.dataModelo = dataModelo;
        this.valor = valor;
        this.cor = cor;
        this.descricaoPecas = descricaoPecas;
        this.fotos = fotos;
        this.vistorias = vistorias;
        this.cliente = cliente;
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

    public List<Vistoria> getVistorias() {
        return vistorias;
    }

    public void setVistorias(List<Vistoria> vistorias) {
        this.vistorias = vistorias;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
                ", vistorias=" + vistorias.size() +
                ", cliente=" + cliente.getNome() +
                '}';
    }

    public void adicionaVistoria(Vistoria vistoria) {
        this.vistorias.add(vistoria);
    }

    public boolean possuiVistoria(Vistoria vistoria) {
        return vistorias.contains(vistoria);
    }

    public boolean possuiFotosDaVistoria(Vistoria vistoria) {
        return vistorias.stream()
                .map(Vistoria::getFoto)
                .anyMatch(v -> v == vistoria.getFoto());
    }
}
