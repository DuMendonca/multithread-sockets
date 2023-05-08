package com.multithreads.sockets.server;

import com.multithreads.sockets.connection.PrintDataSocket;
import com.multithreads.sockets.connection.WriteDataSocket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.GregorianCalendar;

public class ServerSockets {
    private static Socket clientSocket;

    public ServerSockets() {
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("Connected at server");

        while (true) {
            clientSocket = serverSocket.accept();
            System.out.println("Client " + clientSocket.getInetAddress().getHostAddress() + " connected");

            GregorianCalendar calendar = new GregorianCalendar();

            File file = new File("fileName");
            Writer writer2 = new FileWriter(file);
            writer2.write("Host: " + clientSocket.getInetAddress().getHostAddress() + "Data e Hora da Conexão: " + calendar.getTime());

            Thread write = new Thread(new WriteDataSocket(clientSocket));
            Thread print = new Thread(new PrintDataSocket(clientSocket));
            write.start();
            print.start();

            try {
                write.join();
                print.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
