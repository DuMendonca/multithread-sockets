package com.multithreads.sockets.server;

import com.multithreads.sockets.connection.PrintDataSocket;
import com.multithreads.sockets.connection.WriteDataSocket;
import com.multithreads.sockets.utils.FileWrite;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSockets {
    private static Socket clientSocket;

    public ServerSockets() {
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("Connected at server");
        File file = new File("fileName.txt");
        file.createNewFile();


        while (true) {
            clientSocket = serverSocket.accept();
            System.out.println("Client " + clientSocket.getInetAddress().getHostAddress() + " connected");

            FileWrite.writeConnectionFromServer(file.getName(), clientSocket);

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
