package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import models.Telefone;
import models.DTO.ContatosDTO;
import controller.ContatosController;

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

    private static boolean existeId(Long id) throws FileNotFoundException, IOException{
        String diretorio = "Agenda-Telefonica\\src\\db";
        String arquivoContato = "contatos.txt";

        File arquivo = new File(diretorio, arquivoContato);
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            String[] partes;
            while ((linha = br.readLine()) != null) {
                partes = linha.split("\\|");
                if(Long.parseLong(partes[0]) == id){
                    return true;
                }

            }
            return false;
        }
    }

    public static void consoleApp() throws Exception{
        criaBanco();
        ContatosController _contatosController = new ContatosController();

        boolean isOpen = true;
        
        limparTela();
        
        Scanner scanner = new Scanner(System.in);
        while(isOpen){

            System.out.println("##################\n##### AGENDA #####\n##################\n");
            System.out.println("1 - Listar Contatos\n2 - Adicionar Contato\n3 - Editar Contato\n4 - Remover Contato\n5 - Sair");
            System.out.println("\n--------------------");
            System.out.print("Digite sua Opção: ");
            String selecao = scanner.next();
            
            try {
                switch (selecao) {
                    case "1":

                        limparTela();
                        System.out.printf("%-30sLista de Contatos%-30s\n\n","","");
                        _contatosController.listarContatos();
                        System.out.println("\nSair da seleção? (Aperte qualquer tecla)");
                        scanner.nextLine();
                        scanner.nextLine();
                        limparTela();
                        break;

                    case "2":

                        limparTela();
                        System.out.println("Digite o nome do contato: ");
                        String nome = scanner.next();
                        System.out.println("Digite o sobrenome do contato: ");
                        String sobrenome = scanner.next();
                        List<Telefone> listaTelefones = new ArrayList<>();
                        ContatosDTO contato = new ContatosDTO(nome, sobrenome, listaTelefones);

                        //scanner.nextLine();

                        System.out.printf("Quantos números serão adicionados ao contato %s %s: ", nome,sobrenome);
                        int totalTelefones = scanner.nextInt();
                        //scanner.nextLine();


                        for (int i = 0; i < totalTelefones; i++) {
                            System.out.printf("(%d) Qual o ddd do contato, o DDD deve ter apenas 2 números: ", i + 1);
                            String ddd = scanner.next();
                        
                            System.out.printf("(%d) Qual o número do contato: ", i + 1);
                            String numeroTelefone = scanner.next();
                        
                            Telefone telefone = new Telefone(ddd, numeroTelefone);
                            listaTelefones.add(telefone);
                        }

                        contato.setTelefone(listaTelefones);
                        //scanner.nextLine();
                        limparTela();

                        _contatosController.criarContato(contato);
                        break;

                    case "3":
                        limparTela();
                        System.out.print("Digite o id do usuário que deseja editar: ");
                        Long idUpdate = scanner.nextLong();
                        if(!existeId(idUpdate)){
                            throw new Exception("O id digitado não existe dentro do banco de dados");
                        }
                        limparTela();
                        System.out.println("Menu de Atualização do contato");
                        System.out.print("\n1 - Nome\n2 - Sobrenome\n3 - Adicionar número ao contato\n\nO que deseja atualizar? ");
                        String selecaoAtualizar = scanner.next();
                        scanner.nextLine();
                        switch (selecaoAtualizar) {
                            case "1":
                                limparTela();
                                System.out.print("Digite o novo nome para o contato: ");
                                String novoNome = scanner.nextLine();
                                _contatosController.atualizarContato(idUpdate, novoNome,Integer.parseInt(selecaoAtualizar), false);
                            break;
            
                            case "2":
                                limparTela();
                                System.out.print("Digite o novo Sobrenome para o contato: ");
                                String novoSobrenome = scanner.nextLine();
                                _contatosController.atualizarContato(idUpdate, novoSobrenome,Integer.parseInt(selecaoAtualizar), false);
                                break;   
                            case "3":
                                limparTela();
                                System.out.print("Digite o ddd do seu número: ");
                                String novoDdd = scanner.nextLine();
                                System.out.print("Digite o novo telefone para o contato: ");
                                String novoNumero = scanner.nextLine();

                                try {
                                    Long.parseLong(novoNumero);
                                    if(novoDdd.length() != 2 || novoNumero.length() > 9 || novoNumero.length() < 8){
                                        throw new Exception("Número ou DDD não é válido");
                                    }
                                } catch (Exception e) {
                                    throw new Exception("Número não é válido");
                                }


                                String novoTelefone = String.format("(%s) %s", novoDdd, novoNumero);
                                _contatosController.atualizarContato(idUpdate, novoTelefone,Integer.parseInt(selecaoAtualizar), true);
                                break;       
                            default:
                                throw new Exception("Não existe essa opção no menu!");
                        }
                        System.out.println("Número atualizado com sucesso!");
                        //limparTela();
                        break;
                    case "4":
                        System.out.print("Digite o id do usuário que deseja apagar: ");
                        Long idRemove = scanner.nextLong();
                        limparTela();
                        _contatosController.removerContato(idRemove);
                        break;
                    case "5":

                        limparTela();
                        String saudacao = saudacao();
                        String contracao = saudacao == " Bom dia" ? " um" : " uma";
                        System.out.println("Tenha" + contracao + saudacao + "\n");
                        isOpen = false;
                        break;

                    default:
                        limparTela();
                        throw new Exception("Não existe essa opção no menu!");
                }
            } 
            catch (Exception e){
                limparTela();
                System.out.println("Erro: " + e.getMessage());
            }

        }
        scanner.close();

    }
}