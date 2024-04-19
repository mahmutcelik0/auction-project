package org.example.client;

import java.io.IOException;
import java.net.Socket;

public class AuctionClient extends Client{
    public AuctionClient(Socket socket) {
        super(socket);
    }

    @Override
    public void run() {
        try {
            String inputLine;
            while ((inputLine = getIn().readLine()) != null) {
                System.out.println("Received message from client: " + inputLine);
//                Server.broadcastMessage(inputLine, this);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}