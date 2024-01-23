package view;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Console {
    
    private static String saudacao(){
        LocalDateTime data = LocalDateTime.now();
        String saudacao = data.getHour() >= 12 && data.getHour() < 18 ? " Boa Tarde!" :
                         data.getHour() >= 18 && data.getHour() < 23 ? " Boa Noite!" :
                         " Bom Dia!"; 
        return saudacao;
    }

    public static void consoleApp(){
        System.out.println("##################\n##### AGENDA #####\n##################\n");
        Scanner scanner = new Scanner(System.in);
        boolean isOpen = true;
        while(isOpen){
            System.out.println("1 - Listar Contatos\n2 - Adicionar Contato\n3 - Remover Contato\n4 - Editar Contato\n5 - Sair");
            System.out.println("\n--------------------");
            System.out.print("Digite sua Opção: ");
            int selecao = scanner.nextInt();
            System.out.println("\n--------------------");

            switch (selecao) {
                case 1:
                    break;
                case 2:
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
                    System.out.println("Seleção Inválida!");
                    break;
            }
        }
        scanner.close();
    }

}
