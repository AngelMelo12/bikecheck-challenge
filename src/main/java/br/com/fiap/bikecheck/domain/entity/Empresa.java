package br.com.fiap.bikecheck.domain.entity;

public class Empresa {

    private Long id;
    private Long cnpj;
    private String nome;
    private String endereco;
    private String segmentos;
    private Long telefone;
    private String email;

    public Empresa() {
    }

    public Empresa(Long id, Long cnpj, String nome, String endereco, String segmentos, Long telefone, String email) {
        this.id = id;
        this.cnpj = cnpj;
        this.nome = nome;
        this.endereco = endereco;
        this.segmentos = segmentos;
        this.telefone = telefone;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCnpj() {
        return cnpj;
    }

    public void setCnpj(Long cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getSegmentos() {
        return segmentos;
    }

    public void setSegmentos(String segmentos) {
        this.segmentos = segmentos;
    }

    public Long getTelefone() {
        return telefone;
    }

    public void setTelefone(Long telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "id=" + id +
                ", cnpj=" + cnpj +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", segmentos='" + segmentos + '\'' +
                ", telefone=" + telefone +
                ", email='" + email + '\'' +
                '}';
    }
}
