package com.multithreads.sockets.client;

import com.multithreads.sockets.connection.PrintDataSocket;
import com.multithreads.sockets.connection.WriteDataSocket;

import java.io.IOException;
import java.net.Socket;

public class ClientSockets extends Thread {

    public ClientSockets() {
    }

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 12345);
        System.out.println("Connected at server");

        Thread write = new Thread(new WriteDataSocket(socket));
        Thread print = new Thread(new PrintDataSocket(socket));
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
