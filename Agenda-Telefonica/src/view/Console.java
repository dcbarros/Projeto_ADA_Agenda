package view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

import models.DTO.ContatosDTO;
import controller.ContatosController;
import controller.TelefoneController;

public class Console {
    
    private static String saudacao(){
        LocalDateTime data = LocalDateTime.now();
        String saudacao = data.getHour() >= 12 && data.getHour() < 18 ? " Boa Tarde!" :
                         data.getHour() >= 18 && data.getHour() < 23 ? " Boa Noite!" :
                         " Bom Dia!"; 
        return saudacao;
    }

    private static void criaBanco(){
        String diretorio = "Agenda-Telefonica\\src\\db";
        String arquivoContato = "contatos.txt";
        String aquivoTelefones = "telefones.txt";

        File tabelaContatos = new File(diretorio, arquivoContato);
        File tabelaTelefone = new File(diretorio, aquivoTelefones);
        if(!tabelaContatos.exists() || !tabelaTelefone.exists()){
            try{
                FileWriter fwContatos = new FileWriter(tabelaContatos);
                FileWriter fwTelefone = new FileWriter(tabelaTelefone);
                fwContatos.close();
                fwTelefone.close();
            }catch(IOException e){
                System.out.println("Ocorreu um problema: ");
                System.out.println(e.getMessage());
            }
        }
    }

    public static void consoleApp(){
        criaBanco();
        ContatosController _contatosController = new ContatosController();

        Scanner scanner = new Scanner(System.in);
        boolean isOpen = true;

        System.out.print("\033[H\033[2J");
        System.out.flush();

        while(isOpen){
            System.out.println("##################\n##### AGENDA #####\n##################\n");
            System.out.println("1 - Listar Contatos\n2 - Adicionar Contato\n3 - Remover Contato\n4 - Editar Contato\n5 - Sair");
            System.out.println("\n--------------------");
            System.out.print("Digite sua Opção: ");
            String selecaoSTR = scanner.nextLine();
            try {
                int selecao = Integer.parseInt(selecaoSTR);
                System.out.println("\n--------------------");

                switch (selecao) {
                    case 1:
                        break;
                    case 2:

                        System.out.print("Digite o nome do contato: ");
                        String nome = scanner.next();
                        System.out.print("Digite o sobrenome do contato: ");
                        String sobrenome = scanner.next();
                        scanner.nextLine();
                        ContatosDTO contato = new ContatosDTO(nome, sobrenome, null);

                        _contatosController.criarContato(contato);

                        break;

                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                    String saudacao = saudacao();
                    String contracao = saudacao == " Bom dia" ? " um" : " uma";
                        System.out.println("Tenha" + contracao + saudacao + "\n");
                        isOpen = false;
                        break;
                    default:
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("Erro: Seleção Inválida!\n");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("Erro: Por favor digite um número válido!\n");
            } 
        }
        scanner.close();
    }

}
