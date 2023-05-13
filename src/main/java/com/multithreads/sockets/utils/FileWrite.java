package com.multithreads.sockets.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;

public class FileWrite {

    public static void writeConnectionFromServer(String fileName, Socket socket) {
        GregorianCalendar calendar = new GregorianCalendar();
        try {
            FileWriter writer = new FileWriter(fileName);
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime now = LocalDateTime.now();
            writer.write("Host: " + socket.getInetAddress().getHostAddress() + " - Data e Hora da Conexão: " + now.format(fmt));
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
