package org.example.server;

import org.example.client.AdminClient;
import org.example.constants.ServerConstants;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class AdminSocketHandler implements Runnable{
    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(ServerConstants.adminPortNumber)) {
            System.out.println("Admin handler is running...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket);

                AdminClient adminClient = new AdminClient(clientSocket);
                new Thread(adminClient).start();

//                AuctionServer.clients.forEach(e -> System.out.println(e.getClientSocket().getPort()));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
