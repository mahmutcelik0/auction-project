package org.example;

import org.example.constants.ServerConstants;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
//        Socket socket = new Socket("localhost", ServerConstants.clientPortNumber);
//        Socket socket2 = new Socket("localhost", ServerConstants.adminPortNumber);

        try (Socket socket = new Socket("localhost", ServerConstants.adminPortNumber)) {

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            Scanner scanner = new Scanner(System.in);
            String text;

            do {
                text = scanner.nextLine();
                System.out.println("TEXT:"+text);
                writer.println("123");

                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                String time = reader.readLine();

                System.out.println(time);

            } while (!text.equals("bye"));

            socket.close();

        } catch (UnknownHostException ex) {

            System.out.println("Server not found: " + ex.getMessage());

        } catch (IOException ex) {

            System.out.println("I/O error: " + ex.getMessage());
        }
    }
    }
