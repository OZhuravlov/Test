package com.study.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {


        try (Socket socket = new Socket("localhost", 3000);
             OutputStream outputStream = socket.getOutputStream();
             InputStream inputStream = socket.getInputStream()
        ) {
            Scanner scanner = new Scanner(System.in);
            byte[] buffer;
            while (true) {
                String message = scanner.nextLine();
                buffer = message.getBytes();
                outputStream.write(buffer);
                inputStream.read(buffer);
                String responseMessage = new String(buffer, 0, buffer.length);
                System.out.println("Echo from server: " + responseMessage);
            }
        }
    }
}