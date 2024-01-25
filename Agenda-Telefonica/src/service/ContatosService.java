package service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import models.Contatos;
import models.Telefone;
import models.DTO.ContatosDTO;

public class ContatosService {

    String diretorio = "Agenda-Telefonica\\src\\db";
    String arquivoContato = "contatos.txt";

    public void criarContato(ContatosDTO request) throws Exception{
        
        Contatos novoContato = new Contatos(request.getNome(), request.getSobrenome(),request.getTelefone());
        addId(novoContato);

        if(contatoEValido(novoContato)){
            try{
                File arquivo = new File(diretorio,arquivoContato);
                try(FileWriter fw = new FileWriter(arquivo,true)) {
                    String linha = novoContato.getId()+"|"+novoContato.getNome()+"|"+novoContato.getSobrenome()+"|"+listarTelefones(novoContato)+"\n";
                    fw.write(linha);
                    fw.flush();
                } catch (IOException e) {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("Ocorreu um problema: " + e.getMessage());
                }

            }catch(Exception e){
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("Ocorreu um erro: " + e.getMessage());
            }
        }

    }

    public void listarContatos(){

        File arquivo = new File(diretorio,arquivoContato);
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            
            String linha;

            while ((linha = br.readLine()) != null) {
            String[] partes = linha.split("\\|");

            String id = partes[0];
            String nomeCompleto = partes[1]+" "+partes[2];
            String listaTelefonica = partes[3];

            String contatos = String.format("Id: %-10s Nome: %-30s Telefone(s): %s",id,nomeCompleto,listaTelefonica);
            System.out.println(contatos);
            
        }
        } catch (IOException e) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Ocorreu um erro: " + e.getMessage());
        }
    }

    public void atualizarContato(Long id){
        Scanner scanner = new Scanner(System.in);
        System.out.print("1 - Nome\n2 - Sobrenome\nNúmero de Telefone\nO que deseja atualizar? ");
        String selecao = scanner.nextLine();
        try{
            switch (selecao) {
                case "1":
                    
                    break;
                case "2":
                    
                    break;
                case "3":
                    
                    break;
                case "4":
                    
                    break;           
                default:

                    break;
            }
        }catch(Exception e){

        }
    }

    //private void atualizaLinha(String )
    private boolean contatoEValido(Contatos input){
        if(input.getNome().length() > 15 || input.getSobrenome().length() > 15){
            System.out.println("O nome ou sobrenome tem mais que 15 caracteres");
            return false;
        }
        return true;
    }

    private void addId(Contatos input){

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

    private String listarTelefones(Contatos contatos){
        String linha = " ";
        try{
            for (Telefone tel : contatos.getTelefone()) {
                linha += "(" + tel.getDdd() + ") " + tel.getNumero() + " ";
            }
        }catch(Exception e){
            System.out.println(e.getCause());
        }
        return linha;
    }

}
