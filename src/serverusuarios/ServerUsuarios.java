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
import java.util.ArrayList;
import java.util.List;

public class ServerUsuarios {


    public static void main(String[] args) throws IOException {
          Socket salida = new Socket("localhost", 8080);
        PrintWriter escritor = new PrintWriter(salida.getOutputStream(), true);
        BufferedReader lector = new BufferedReader(new InputStreamReader(salida.getInputStream()));
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
        String cadena = teclado.readLine();
        String mensaje;
            String [] usario = new String [100];
            String [] contraseña = new String [100];
            List <String> listaUsuarios = new ArrayList<>();
            
    
        
        while (!cadena.equalsIgnoreCase("fin")) {
           
            try{
                int op = Integer.parseInt(teclado.readLine());

                switch (op) {
                    case 1:       //caso para ingresar a la cuenta
                        
                        break;
                
                    case 2:       // caso para registrar se

                    escritor.println("ingrese su nombre de usuario");
                   


                    escritor.println("ingrese su contraseña");
                    



                        break;

                    case 3:         // Salir

                        break;
                }


            }catch (NumberFormatException e){

            }
        }
        salida.close();

        
    }
    
}
