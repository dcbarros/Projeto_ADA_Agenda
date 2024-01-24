package models.DTO;

import java.util.List;

import models.Telefone;

public class ContatosDTO {

    private String nome;
    private String sobrenome;
    private List<Telefone> telefone;
    
    public ContatosDTO(String nome, String sobrenome, List<Telefone> telefone) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public List<Telefone> getTelefone() {
        return telefone;
    }

    public void setTelefone(List<Telefone> telefone) {
        this.telefone = telefone;
    }

}
