package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
                    String linha = novoContato.getId()+"|"+novoContato.getNome()+"|"+novoContato.getSobrenome()+"|"+listaTelefones(novoContato)+"\n";
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

    public void atualizarContato(Long id, String update, int selecao, boolean append) throws Exception{
        
        if(append){
            existeTelefone(update);
            List<String> tabela = lerTabela();
            int index = localizarIndex(id);
            String[] particionar = tabela.get(index).split("\\|");
            particionar[selecao] += update + " ";
            tabela.set(index, reconstruirString(particionar));
            escreverArquivo(tabela);
        }else{
            List<String> tabela = lerTabela();
            int index = localizarIndex(id);
            String[] particionar = tabela.get(index).split("\\|");
            particionar[selecao] = update;
            tabela.set(index, reconstruirString(particionar));
            escreverArquivo(tabela);
        }
        
    }

    public void removerContato(Long id) throws Exception{
        try {
            List<String> tabela = lerTabela();
            final int index = localizarIndex(id);
            if(index == -1){throw new Exception("id do contato não existe");}
            tabela.remove(index);
            escreverArquivo(tabela);
        } catch (Exception e) {
            throw new Exception("Erro na execução da remoção do contato!\nErro: "+e.getMessage());
        }
    }

    private String reconstruirString(String[] input){
        return String.format("%s|%s|%s|%s", input[0],input[1],input[2],input[3]);
    }
    
    private List<String> lerTabela() throws FileNotFoundException, IOException{
        List<String> linhas = new ArrayList<>();
        File arquivo = new File(diretorio, arquivoContato);
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linhas.add(linha);
            }
        }

        return linhas;
    }

    private int localizarIndex(Long id) throws NumberFormatException, IOException{
        File arquivo = new File(diretorio, arquivoContato);
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            String[] partes;
            int contador = 0;
            while ((linha = br.readLine()) != null) {
                partes = linha.split("\\|");
                if(Long.parseLong(partes[0]) == id){
                    return contador;
                }else{
                    contador++;
                }

            }
            return -1;
        }
    }

    private void escreverArquivo(List<String> input) throws IOException{
        File arquivo = new File(diretorio, arquivoContato);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
            for (String linha : input) {
                bw.write(linha);
                bw.newLine();
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

    private String listaTelefones(Contatos contatos){
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

    private void existeTelefone(String telefone) throws Exception{

        String diretorio = "Agenda-Telefonica\\src\\db";
        String arquivoContato = "contatos.txt";
        
        try (BufferedReader br = new BufferedReader(new FileReader(new File(diretorio,arquivoContato)))) {
            String linha;

            while((linha = br.readLine()) != null){
                if(linha.contains(telefone)){
                    throw new Exception("Número já consta na lista de contatos!!\n");
                }
            }

        }catch (IOException e){
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Ocorreu um erro: " + e.getMessage());
        }
    }

}
