package main;

import controller.Controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import org.json.simple.parser.ParseException;


/**
 *
 * @author fabio
 */
public class MainServer {
    private static Socket s;
    private static ServerSocket ss;
    private static PrintWriter pr;
    private static Controller controller;

    public static void main(String[] args) throws IOException, ParseException {
        
        ss = new ServerSocket(80);
        ss.setReuseAddress(true); 
        controller = new Controller();

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
        
        System.out.println("Dados recebidos do cliente:\n" + msg); 
           
        String retorno = controller.trataDados(msg);  
        enviarDados(retorno);
    }
    
    public static void enviarDados(String msg) throws IOException {
        pr = new PrintWriter(s.getOutputStream());
        pr.println(msg);
        pr.flush();
    }
    
    
}
