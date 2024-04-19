package org.example.server;

import org.example.client.AuctionClient;
import org.example.constants.ServerConstants;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientSocketHandler implements Runnable{
    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(ServerConstants.clientPortNumber)) {
            System.out.println("Client handler is running...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket);

                AuctionClient clientHandler = new AuctionClient(clientSocket);
                AuctionServer.clients.add(clientHandler);
                new Thread(clientHandler).start();
                AuctionServer.clients.forEach(e -> System.out.println(e.getSocket().getPort()));
                System.out.println("CLIENT CONNECTED");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
