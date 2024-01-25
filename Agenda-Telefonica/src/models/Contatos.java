package models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Contatos {
    private Long id;
    private String nome;
    private String sobrenome;
    private List<Telefone> telefone;

    public Contatos(String nome, String sobrenome, List<Telefone> telefone) throws Exception {
        this.nome = nome;
        this.sobrenome = sobrenome;
        for (Telefone t : telefone) {
            if(existeTelefone(t)){
                throw new Exception("Telefone já existe: " + t.getDdd() + " " + t.getNumero());
            }
        }
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

    private boolean existeTelefone(Telefone input){

        String diretorio = "Agenda-Telefonica\\src\\db";
        String arquivoContato = "contatos.txt";
        
        try (BufferedReader br = new BufferedReader(new FileReader(new File(diretorio,arquivoContato)))) {
            String linha;

            while((linha = br.readLine()) != null){
                if(linha.contains("(" + input.getDdd() + ") " + input.getNumero() + " ")){
                    return true;
                }
            }

        }catch (IOException e){
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Ocorreu um erro: " + e.getMessage());
        }
        return false;
    }

}
