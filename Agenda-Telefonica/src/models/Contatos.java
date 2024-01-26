package models;

import java.util.List;

public class Contatos {
    private Long id;
    private String nome;
    private String sobrenome;
    private List<Telefone> telefone;

    public Contatos(String nome, String sobrenome, List<Telefone> telefone) throws Exception {
        if(nome.length() > 15 || sobrenome.length() > 15){
            throw new Exception("O nome ou sobrenome exedem 15 caracteres");
        }
        if(nome.isEmpty() || sobrenome.isEmpty()){
            throw new Exception("O nome ou sobrenome não podem ser vazios");
        }
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
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

    public List<Telefone> getTelefone() {
        return telefone;
    }

    public void setTelefone(List<Telefone> telefone) {
        this.telefone = telefone;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
}
