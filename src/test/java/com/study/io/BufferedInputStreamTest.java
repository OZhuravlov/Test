package com.study.io;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import static org.junit.Assert.*;

public class BufferedInputStreamTest {

    byte[] array = new byte[10_000];
    InputStream inputStream;
    BufferedInputStream bis;

    @Before
    public void before() {
        new Random().nextBytes(array);
        inputStream = new ByteArrayInputStream(array);
        bis = new BufferedInputStream(inputStream);
    }

    @Test
    public void testRead() throws IOException {
        for (byte b : array) {
            byte value = (byte)bis.read();
            assertEquals(b, value);
        }
    }

    @Test
    public void testReadToArray() throws IOException {
        byte[] data = new byte[512];
        int index = 0;
        bis.read(data);
        for (byte value : data) {
            byte expect = array[index];
            assertEquals(expect, value);
            index++;
        }
    }

    @Test
    public void testReadWihOffset() throws IOException {
        byte[] data = new byte[512];
        int off = 128;
        int len = 100;
        bis.read(data, off, len);
        for (int i = 0; i < len; i++) {
            assertEquals(array[i], data[i + off]);
        }
    }

    @After
    public void after() throws IOException {
        inputStream.close();
        bis.close();
    }
}