package serverusuarios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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
        ServerSocket socketEspecial = new ServerSocket(8080);
        Socket cliente = socketEspecial.accept();

        PrintWriter escritor = new PrintWriter(cliente.getOutputStream(), true);
        BufferedReader lectorSocket = new BufferedReader(new InputStreamReader(
                cliente.getInputStream()));
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
       
        String entrada;
        String Mimensaje;
            String [] usario = new String [100];
            String [] contraseña = new String [100];
            List <String> listaUsuarios = new ArrayList<>();


        while ((entrada = lectorSocket.readLine()) != null) {
            int op= Integer.parseInt(entrada);
            
            try{
                
                switch (op) {
                    case 1:       //caso para ingresar a la cuenta
                        
                        break;
                
                    case 2:       // caso para registrar se

    escritor.println("Ingrese su nombre de usuario:");
    String nuevoUser = lectorSocket.readLine();

    escritor.println("ingrese su contraseña:");
    String nuevoPass = lectorSocket.readLine();

    boolean existe = false;
    File archivo = new File("usuarios.txt");
    if (!archivo.exists()) {
        archivo.createNewFile();
    }

   
    BufferedReader br = new BufferedReader(new FileReader(archivo));
    String linea;
    while ((linea = br.readLine()) != null) {
        String[] partes = linea.split(",");
        if (partes[0].equals(nuevoUser)) {
            existe = true;
            break;
        }
    }
    br.close();

    
    if (!existe) {
        BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true));
        bw.write(nuevoUser + "," + nuevoPass);
        bw.newLine();
        bw.close();
        escritor.println("Su usuario se registro exitosamente .");
    } else {
        escritor.println("No se puede poner ese nombre de usuario.");
    }


    break;

                    case 3:         // Salir

                         
                        cliente.close();
                        socketEspecial.close();

                        break;
                }


            }catch (NumberFormatException e){

            }
        }
        cliente.close();

        
    }
    
}
