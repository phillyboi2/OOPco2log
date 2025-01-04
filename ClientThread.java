package co2log;

import java.io.*;
import java.net.*;

// ClientThread implements Runnable to handle client-server communication in separate threads
public class ClientThread implements Runnable {
    private Socket clientSocket; // socket for communication with the client
    private Server server; // refernece to the server for logging data

    // initialise the clientSocket and server 
    public ClientThread(Socket clientSocket, Server server) {
        this.clientSocket = clientSocket;
        this.server = server;
    }

    
// The outputs and prompts for the user to enter data
    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // to read data from the client
             PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)) { // to send data to the client

            writer.println("Welcome to the server!");
            
            writer.println("Please enter your User ID:");
            String userID = reader.readLine();

            writer.println("Please enter your postcode:");
            String postcode = reader.readLine();

            writer.println("Please enter CO2 concentration:");
            float co2Concentration = Float.parseFloat(reader.readLine());

            ClientData data = new ClientData(userID, postcode, co2Concentration);
            server.logData(data);
            
            writer.println("Thank you! Your data has been logged.");
        } catch (IOException e) { // print stack trace for any errors
            e.printStackTrace();
        } finally {
        	// close client socket to release resources
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
