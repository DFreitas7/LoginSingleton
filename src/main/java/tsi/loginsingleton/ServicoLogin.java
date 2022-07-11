
package tsi.loginsingleton;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class ServicoLogin {
    private static ServicoLogin instancia = null;
    private HashMap<String, Usuario> usuarios;
    
    private ServicoLogin()
    {
        usuarios = new HashMap();
    }
    
    public static ServicoLogin Instancia()
    {
        if (instancia == null)
        {
            instancia = new ServicoLogin();
        }
        
        return instancia;
    }
    
    public Usuario cadastrar(String nome, String senha, String confirmacaoSenha)  throws Exception {
        verificarBot();
        if (usuarios.containsKey(nome))
            throw new Exception("Nome de usuário já está sendo utilizado.");
        
        if (!senha.equals(confirmacaoSenha))
            throw new Exception("Confirmação de senha não bate com a senha informada.");
                            
        
        Usuario novoUsuario = new Usuario(nome, senha);
        
        usuarios.put(nome, novoUsuario);
        System.out.println("Usuário " + nome + " cadastrado com sucesso!");
        
        return novoUsuario;
    }
    
    public Usuario logar(String nome, String senha) throws Exception {
        verificarBot();
        Usuario usuarioPorNome = usuarios.get(nome);
        
        if (usuarioPorNome == null)
            throw new Exception("Usuário não encontrado no sistema.");
        
        if (!usuarioPorNome.getSenha().equals(senha))
            throw new Exception("Senha incorreta.");
        
        System.out.println("Usuário " + nome + " logado com sucesso!");
        return usuarioPorNome;
    }
    
    public void remover(String nome, String senha) throws Exception {
        verificarBot();
        Usuario usuarioPorNome = usuarios.get(nome);
        
        if (usuarioPorNome == null)
            throw new Exception("Usuário não encontrado no sistema.");
        
        if (!usuarioPorNome.getSenha().equals(senha))
            throw new Exception("Senha incorreta.");
        
        usuarios.remove(nome);
        System.out.println("Usuário " + nome + " removido com sucesso!");
    }
    
    private void verificarBot() throws Exception
    {
        int menor = 48; // numero '0'
        int maior = 122; // letra 'z'
        int targetStringLength = 6;
        Random random = new Random();

        String codigoAleatorio = random.ints(menor, maior + 1)
            .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
            .limit(targetStringLength)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();
        
        System.out.println("Digite o codigo abaixo para verificação:");
        System.out.println(codigoAleatorio);
        
        Scanner scanner = new Scanner(System.in);
        
        String codigoDigitado = scanner.nextLine();
        
        if (!codigoDigitado.equals(codigoAleatorio))
            throw new Exception("Código de verificação inválido.");
    }
}
