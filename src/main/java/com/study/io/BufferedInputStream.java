package com.study.io;

import java.io.IOException;
import java.io.InputStream;

public class BufferedInputStream extends InputStream {
    private InputStream inputStream;
    private byte[] buffer = new byte[1024];
    private int index;
    private int count;

    public BufferedInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public int read() throws IOException {
        if (checkBuffer() == -1) {
            return -1;
        }
        int value = buffer[index];
        index++;

        return value;
    }

    private int checkBuffer() throws IOException {
        if (index == count) {
            count = inputStream.read(buffer);
            index = 0;
        }
        return count;
    }
    @Override
    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int destIndex = off;
        while(destIndex < off + len){
            if (checkBuffer() == -1) {
                return -1;
            }
            int bufferRest = count - index;
            int destRest = off + len - destIndex;
            int bytesAmount = Math.min(bufferRest, destRest);
            System.arraycopy(buffer, index, b, destIndex, bytesAmount);
            index += bytesAmount;
            destIndex += bytesAmount;
            if(destIndex == off + len){
                return destIndex - off;
            }
        }
        return destIndex - off;
    }

    @Override
    public void close() throws IOException {
        inputStream.close();
    }
}
