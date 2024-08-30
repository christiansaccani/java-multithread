/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cis_16_servermultithread;

/**
 *
 * @author prova
 */
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connesso al server sulla porta " + SERVER_PORT);

            Intestatario intestatario = new Intestatario();

            // Loop per leggere input dal terminale e inviarlo al server
            String userInput;
            while (true) {
                System.out.print("Inserisci il messaggio da inviare al server (o 'exit' per terminare): ");
                
                userInput = scanner.nextLine();

                if ("exit".equalsIgnoreCase(userInput)) {
                    System.out.println("Disconnessione dal server.");
                    break;
                }

                String[] parts = userInput.split(" ");
                if (parts.length == 2) {
                    intestatario.setNome(parts[0]);
                    intestatario.setCognome(parts[1]);
                    System.out.println("Nuovo Intestatario: " + intestatario);
                } else {
                    System.out.println("Formato non valido. Inserisci 'nome cognome'.");
                }

                out.println(intestatario);
                String response = in.readLine();
                System.out.println("Risposta dal server: " + response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}