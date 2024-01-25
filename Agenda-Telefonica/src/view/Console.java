package view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import models.Telefone;
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

        File tabelaContatos = new File(diretorio, arquivoContato);
        if(!tabelaContatos.exists()){
            try{
                FileWriter fwContatos = new FileWriter(tabelaContatos);
                fwContatos.close();
            }catch(IOException e){
                System.out.println("Ocorreu um problema: ");
                System.out.println(e.getMessage());
            }
        }
    }

    private static void limparTela(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void consoleApp(){
        criaBanco();
        ContatosController _contatosController = new ContatosController();

        Scanner scanner = new Scanner(System.in);
        boolean isOpen = true;

        limparTela();

        while(isOpen){

            System.out.println("##################\n##### AGENDA #####\n##################\n");
            System.out.println("1 - Listar Contatos\n2 - Adicionar Contato\n3 - Remover Contato\n4 - Editar Contato\n5 - Sair");
            System.out.println("\n--------------------");
            System.out.print("Digite sua Opção: ");
            String selecaoSTR = scanner.nextLine();
            
            try {

                int selecao = Integer.parseInt(selecaoSTR);
                switch (selecao) {
                    case 1:

                        limparTela();
                        System.out.printf("%-30sLista de Contatos%-30s\n\n","","");
                        _contatosController.listarContatos();
                        System.out.println("\nSair da seleção? (Aperte qualquer tecla)");
                        scanner.nextLine();
                        limparTela();
                        break;

                    case 2:

                        limparTela();
                        System.out.print("Digite o nome do contato: ");
                        String nome = scanner.next();
                        System.out.print("Digite o sobrenome do contato: ");
                        String sobrenome = scanner.next();
                        List<Telefone> listaTelefones = new ArrayList<>();
                        ContatosDTO contato = new ContatosDTO(nome, sobrenome, listaTelefones);

                        scanner.nextLine();

                        System.out.printf("Quantos números serão adicionados ao contato %s %s: ", nome,sobrenome);
                        int totalTelefones = scanner.nextInt();

                        for (int i = 0; i < totalTelefones; i++) {
                            System.out.printf("(%d) Qual o ddd do contato: ", i + 1);
                            String ddd = scanner.next();
                        
                            System.out.printf("(%d) Qual o número do contato: ", i + 1);
                            String numeroTelefone = scanner.next();
                        
                            Telefone telefone = new Telefone(ddd, numeroTelefone);
                            listaTelefones.add(telefone);
                        }

                        contato.setTelefone(listaTelefones);
                        scanner.nextLine();
                        limparTela();
                        _contatosController.criarContato(contato);
                        break;

                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:

                        limparTela();
                        String saudacao = saudacao();
                        String contracao = saudacao == " Bom dia" ? " um" : " uma";
                        System.out.println("Tenha" + contracao + saudacao + "\n");
                        isOpen = false;
                        break;

                    default:
                        limparTela();
                        System.out.println("Erro: Seleção Inválida!\n");
                        break;
                }
            } catch (NumberFormatException e) {
                limparTela();
                System.out.println("Erro: Por favor digite um número válido!\n");
            } 
        }

        scanner.close();
    }

}
