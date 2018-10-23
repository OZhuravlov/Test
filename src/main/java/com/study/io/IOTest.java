package com.study.io;

import java.io.*;

public class IOTest {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = new BufferedInputStream(new FileInputStream("test.log"));
        int value;
        while ((value = inputStream.read()) != -1) {
            System.out.print((char) value);
        }

        inputStream.close();


//        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream("test2.log"));
//        try {
//            outputStream.write("Hello world".getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new NullPointerException();
//        } finally {
//            outputStream.close();
//        }

    }
}
// Hello


// int read() [0... 255] -> if byte was read, or -1 if end of stream
