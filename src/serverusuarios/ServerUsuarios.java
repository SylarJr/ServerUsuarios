package serverusuarios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerUsuarios {


    public static void main(String[] args) throws IOException {
          Socket salida = new Socket("localhost", 8080);
        PrintWriter escritor = new PrintWriter(salida.getOutputStream(), true);
        BufferedReader lector = new BufferedReader(new InputStreamReader(salida.getInputStream()));
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
        String cadena = teclado.readLine();
        String mensaje;
        
        while (!cadena.equalsIgnoreCase("fin")) {
            escritor.println(cadena);
            mensaje = lector.readLine();
            System.out.println(mensaje);
            cadena = teclado.readLine();
        }
        salida.close();

        
    }
    
}
