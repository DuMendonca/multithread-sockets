package com.multithreads.sockets.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {

    public static void writeConnectionFromServer(String path, Socket socket) {
        try (FileWriter fw = new FileWriter(path, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw)) {
            pw.println("Host: " + socket.getInetAddress().getHostAddress() + " - Data e Hora da Conexão: " + dateTimeFormatted());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static String dateTimeFormatted(){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        return now.format(fmt);
    }
}
