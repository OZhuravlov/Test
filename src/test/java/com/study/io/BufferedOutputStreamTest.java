package com.study.io;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class BufferedOutputStreamTest {

    byte[] array = new byte[10_000];
    ByteArrayOutputStream outputStream;
    BufferedOutputStream bos;

    @Before
    public void before() {
        new Random().nextBytes(array);
        outputStream = new ByteArrayOutputStream();
        bos = new BufferedOutputStream(outputStream);
    }

    @Test
    public void testWrite() throws IOException {
        byte[] data;
        for (byte b : array) {
            bos.write(b);
        }
        bos.flush();
        data = outputStream.toByteArray();
        for (int i = 0; i < array.length; i++) {
            assertEquals(array[i], data[i]);
        }

    }

    @Test
    public void testWriteFromArray() throws IOException {
        byte[] data;
        byte[] dataToWrite = new byte[2_000];
        System.arraycopy(array, 0, dataToWrite, 0, 2_000);
        bos.write(dataToWrite);
        bos.flush();
        data = outputStream.toByteArray();
        for (int i = 0; i < dataToWrite.length; i++) {
            assertEquals(dataToWrite[i], data[i]);
        }
    }

    @Test
    public void testReadWihOffset() throws IOException {
        byte[] data;
        byte[] dataToWrite = new byte[2_000];
        System.arraycopy(array, 0, dataToWrite, 0, 2_000);
        int off = 1000;
        int len = 512;
        bos.write(dataToWrite, off, len);
        bos.flush();
        data = outputStream.toByteArray();
        for (int i = 0; i < len; i++) {
            assertEquals(dataToWrite[off + i], data[i]);
        }
    }

    @After
    public void after() throws IOException {
        outputStream.close();
        bos.close();
    }
}
