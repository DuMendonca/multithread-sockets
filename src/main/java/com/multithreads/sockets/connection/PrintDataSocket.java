package com.multithreads.sockets.connection;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class PrintDataSocket implements Runnable{

    private Socket socket;

    private static final String EXIT = "!sair";

    public PrintDataSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        Scanner scanner;
        try {
            scanner = new Scanner(socket.getInputStream());
            while (scanner.hasNextLine()) {
                String message = scanner.nextLine();
                System.out.println(socket.getInetAddress().getHostAddress() + " said: " + message);

                if (EXIT.equals(message))
                {
                    socket.close();
                    System.out.println(socket.getInetAddress().getHostAddress() + " desconnected");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
