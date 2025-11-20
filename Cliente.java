/*
-Felipe Trevelin de Godoi, 211338
-Kaiky Guizelini Santos da Silva, 075802
-Gabriel Henrique Lima dos Santos Galis, 211341
 */

import java.io.*;
//input/output
import java.net.*;
//rede
import java.util.Scanner;
//leitura do teclado

public class Cliente {
    public static void main(String[] args) {
        String servidor = "localhost";
        //endereço do servidor
        int porta = 12345;
        //porta do servidor

        try (Socket socket = new Socket(servidor, porta)) {
            //tenta conexão com servidor, cria socket TCP 
            System.out.println("Conectado ao servidor.");

            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //objeto para input
            PrintWriter saida = new PrintWriter(socket.getOutputStream(), true);
            //objeto para output

            Scanner sc = new Scanner(System.in);
            //objeto leitura do teclado

            System.out.print("Digite os números separados por espaço: ");
            String numeros = sc.nextLine();

            saida.println(numeros);

            String resposta = entrada.readLine();
            //recebe resposta do servidor
            System.out.println("Números ordenados recebidos do servidor: " + resposta);

            socket.close();
            //fecha socket TCP de conexão com servidor
            
            sc.close();
            //encerra o Scanner
            System.out.println("Conexão encerrada.");

        } catch (IOException e) {
            //catch generico
            e.printStackTrace();
        }
    }
}
