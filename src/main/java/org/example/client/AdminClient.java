package org.example.client;

import java.io.*;
import java.net.Socket;

public class AdminClient extends Client {
    public AdminClient(Socket clientSocket) {
        super(clientSocket);
    }

    @Override
    public void run() {
        try {
            InputStream input = getSocket().getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            OutputStream output = getSocket().getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            String inputLine;
            while (!(inputLine = reader.readLine()).equals("bye")) {
                System.out.println("Received from client: " + inputLine);
//                writer.println("Server received: " + inputLine);
            }
            System.out.println("Received from client OUT: " + inputLine);
            getSocket().close();
            // Döngüden çıkıldığında istemci bağlantısı kapatılır
            System.out.println("Client disconnected.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
