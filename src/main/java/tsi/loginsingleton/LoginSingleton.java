package tsi.loginsingleton;

import java.util.Scanner;

public class LoginSingleton {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Integer comando;
            do {
                System.out.println("Bem-vindo ao sistema de Login!");
                System.out.println("1 - Cadastrar\t 2 - Logar\t 3 - Remover\t 0 - Sair");
                comando = Integer.parseInt(scanner.nextLine());
                
                String nome;
                String senha;
                String confirmacaoSenha;
                try
                {
                    switch(comando) {
                        case 0:
                            System.out.println("Saindo do Sistema de Login!");
                            break;
                        case 1:
                            System.out.println("Digite o nome de usuário para cadastrar:");
                            nome = scanner.nextLine();
                            System.out.println("Agora digite a senha:");
                            senha = scanner.nextLine();
                            System.out.println("Por favor, confirme a senha:");
                            confirmacaoSenha = scanner.nextLine();
                            
                            ServicoLogin.Instancia().cadastrar(nome, senha, confirmacaoSenha);
                            break;
                        case 2:
                            System.out.println("Digite o nome de usuário para logar:");
                            nome = scanner.nextLine();
                            System.out.println("Agora digite a senha:");
                            senha = scanner.nextLine();
                            
                            ServicoLogin.Instancia().logar(nome, senha);
                            break;
                        case 3:
                            System.out.println("Digite o nome de usuário para remover:");
                            nome = scanner.nextLine();
                            System.out.println("Agora digite a senha:");
                            senha = scanner.nextLine();
                            
                            ServicoLogin.Instancia().remover(nome, senha);
                            break;
                        default: throw new Exception("Comando inválido!");
                    }
                } catch(Exception e) {
                    System.out.println(e.getMessage());
                }
            } while(comando != 0);
        }
    }
}
