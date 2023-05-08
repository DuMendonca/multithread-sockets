package com.multithreads.sockets.connection;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class WriteDataSocket implements Runnable{

    private Socket socket;

    public WriteDataSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        try {
            PrintStream printStream = new PrintStream(socket.getOutputStream());
            while (scanner.hasNextLine()) {
                printStream.println(scanner.nextLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
