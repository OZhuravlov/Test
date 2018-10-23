package com.study.io;

import java.io.IOException;
import java.io.OutputStream;

public class BufferedOutputStream extends OutputStream {

    private OutputStream outputStream;
    private byte[] buffer = new byte[1024];
    private int index;
    private int count;

    public BufferedOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void write(int b) throws IOException {
        if(index == buffer.length){
            flush();
        }
        buffer[index] = (byte)b;
        index++;
    }

    @Override
    public void write(byte[] b) throws IOException {
        write(b, 0, b.length);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        int srcIndex = off;
        while(srcIndex < off + len){
            if(index == buffer.length){
                flush();
            }
            int bufferRest = buffer.length - index;
            int destRest = off + len - srcIndex;
            int bytesAmount = Math.min(bufferRest, destRest);
            System.arraycopy(b, srcIndex, buffer, index, bytesAmount);
            index += bytesAmount;
            srcIndex += bytesAmount;
            if(srcIndex == off + len){
                return;
            }
        }
    }

    @Override
    public void flush() throws IOException {
        outputStream.write(buffer, 0, index);
        index = 0;
    }

    @Override
    public void close() throws IOException {
        flush();
        outputStream.close();
    }

}