package service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import models.Contatos;
import models.DTO.ContatosDTO;

public class ContatosService {


    

    public void criarContato(ContatosDTO request){
        
        Contatos novoContato = new Contatos(request.getNome(), request.getSobrenome());
        // criar o id
        if(contatoEValido(novoContato)){
            try{

                String diretorio = "Agenda-Telefonica\\src\\db";
                String arquivoContato = "contatos.txt";
                File arquivo = new File(diretorio,arquivoContato);

                try(FileWriter fw = new FileWriter(arquivo,true)) {
                String linha = addId(novoContato)+"|"+novoContato.getNome()+"|"+novoContato.getSobrenome()+"|"+novoContato.getTelefone()+"\n";
                fw.write(linha);
                fw.flush();
                } catch (IOException e) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("Ocorreu um problema: ");
                System.out.println(e.getMessage());
                }
            }catch(Exception e){
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("Ocorreu um erro: " + e.getMessage());
            }
        }

    }

    private boolean contatoEValido(Contatos input){
        if(input.getNome().length() > 15 || input.getSobrenome().length() > 15){
            System.out.println("O nome ou sobrenome tem mais que 15 caracteres");
            return false;
        }
        return true;
    }

    private Long addId(Contatos input){
        
        return 1L;
    }
}
