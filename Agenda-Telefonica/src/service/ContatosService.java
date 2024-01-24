package service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import models.Contatos;
import models.DTO.ContatosDTO;

public class ContatosService {


    

    public void criarContato(ContatosDTO request){
        
        Contatos novoContato = new Contatos(request.getNome(), request.getSobrenome());
        addId(novoContato);

        if(contatoEValido(novoContato)){
            try{

                String diretorio = "Agenda-Telefonica\\src\\db";
                String arquivoContato = "contatos.txt";
                File arquivo = new File(diretorio,arquivoContato);

                try(FileWriter fw = new FileWriter(arquivo,true)) {
                    String linha = novoContato.getId()+"|"+novoContato.getNome()+"|"+novoContato.getSobrenome()+"\n";
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

    private void addId(Contatos input){
        String diretorio = "Agenda-Telefonica\\src\\db";
        String arquivoContato = "contatos.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(new File(diretorio,arquivoContato)))) {
            String linha;
            int contador = 0;
            String[] partes = new String[1];

            while((linha = br.readLine()) != null){
                partes = linha.split("\\|");
                contador++;
            }
            if(contador == 0){
                input.setId(1L);
            }else{
                input.setId(Long.parseLong(partes[0])+1);
            }

        }catch (IOException e){
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Ocorreu um erro: " + e.getMessage());
        }
    }
}
