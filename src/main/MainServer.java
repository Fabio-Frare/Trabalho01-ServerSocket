package main;

import controller.Controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import org.json.simple.parser.ParseException;


/**
 *
 * @author Fabio e Lucas Nogueira
 */
public class MainServer {
    private static Socket s;
    private static ServerSocket ss;
    private static PrintWriter pr;
    private static Controller controller;

    public static void main(String[] args) throws IOException, ParseException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Indique uma porta para a aplicação servidor: ");
        int port = sc.nextInt();
        
        ss = new ServerSocket(port);
        ss.setReuseAddress(true); 
        controller = new Controller();
        System.out.println("Iniciado");

        while (true) {
            receberDados();
        }
        
    }
    
    public static void receberDados() throws IOException, ParseException {
        s = ss.accept();
        String clienteIP = s.getInetAddress().getHostAddress();
        System.out.println("Cliente IP " + clienteIP + " conectado." );

        InputStreamReader in = new InputStreamReader(s.getInputStream());
        BufferedReader bf = new BufferedReader(in);
        String msg = bf.readLine();
        
        String retorno = controller.trataDados(msg);  
        enviarDados(retorno);
    }
    
    public static void enviarDados(String msg) throws IOException {
        pr = new PrintWriter(s.getOutputStream());
        pr.println(msg);
        pr.flush();
    }
    
}
