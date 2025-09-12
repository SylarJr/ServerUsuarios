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
    escritor.println("Ingrese su nombre de usuario:");
    String userLogin = lectorSocket.readLine();

    escritor.println("Ingrese su contraseña:");
    String passLogin = lectorSocket.readLine();

    boolean encontrado = false;
    File archivoLogin = new File("usuarios.txt");
    if (!archivoLogin.exists()) {
        archivoLogin.createNewFile();
    }

    BufferedReader brLogin = new BufferedReader(new FileReader(archivoLogin));
    String lineaLogin;
    while ((lineaLogin = brLogin.readLine()) != null) {
        String[] partes = lineaLogin.split(",");
        if (partes[0].equals(userLogin) && partes[1].equals(passLogin)) {
            encontrado = true;
            break;
        }
    }
    brLogin.close();

    if (encontrado) {
        escritor.println("Pudo iniciar sesion " + userLogin + ". Ahora puedes chatear con el servidor, para salir escribe fin");

        while ((entrada = lectorSocket.readLine()) != null) {
            if (entrada.equalsIgnoreCase("fin")) {
                escritor.println("Saliendo de la sesion...");
                break;
            }

           
            File archivoChat = new File("chat_" + userLogin + ".txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivoChat, true));
            bw.write("Usuario: " + entrada);
            bw.newLine();

            System.out.println("Cliente (" + userLogin + "): " + entrada);

           
            Mimensaje = teclado.readLine();
            escritor.println(Mimensaje);

            
            bw.write("Servidor: " + Mimensaje);
            bw.newLine();
            bw.close();
        }
    } else {
        escritor.println("Usuario o contraseña incorrectos.");
    }
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
