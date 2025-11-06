import java.io.*;
//gerenciamento de input/output
import java.net.*;
//rede
import java.util.*;
//utilitarios

public class Servidor {
    public static void main(String[] args) {
        int porta = 12345;
        //porta TCP que o server irá escutar

        try (ServerSocket servidor = new ServerSocket(porta)) {
            //cria um Server-Socket atrelado á porta
            //por ser aberto como parametro no try-catch, vai ser fechado sozinho
            System.out.println("Servidor iniciado na porta " + porta);
            System.out.println("Aguardando conexão...");

            Socket cliente = servidor.accept();
            //interrompe a execução do código até que um cliente se conecte
            System.out.println("Cliente conectado: " + cliente.getInetAddress());

            BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            //objeto para leitura de input
            PrintWriter saida = new PrintWriter(cliente.getOutputStream(), true);
            //objeto para envio de output

            String numerosRecebidos = entrada.readLine();
            System.out.println("Recebendo: " + numerosRecebidos);

            String[] partes = numerosRecebidos.split(" ");
            //separa o input em um array de String
            int[] numeros = new int[partes.length];
            //cria array de numeros de mesmo tamanho
            for (int i = 0; i < partes.length; i++) {
                numeros[i] = Integer.parseInt(partes[i]);
                //converte String para int e registra no array de int
            }

            Arrays.sort(numeros);
            //realiza a ordenação
            System.out.println("Ordenando...");

            StringBuilder resposta = new StringBuilder();
            //objeto para formatar a repsosta
            for (int n : numeros) {
                resposta.append(n).append(" ");
            }

            saida.println(resposta.toString().trim());
            //envio de input para o cliente
            System.out.println("Enviando números ordenados: " + resposta.toString().trim());

            cliente.close();
            //fecha o socket unico do cliente
            System.out.println("Conexão encerrada.");

        } catch (IOException e) {
            //catch generico
            e.printStackTrace();
        }
    }
}
