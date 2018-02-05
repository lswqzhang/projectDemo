package com.lswq.nio.file;

import java.nio.ByteBuffer;

public class ByteBufferTest {

    public static void main(String[] args) {
        ByteBuffer buf = ByteBuffer.allocate(4096);
        buf.put("hello".getBytes());
        buf.flip();
        while (buf.hasRemaining()) {
            System.err.println(String.valueOf((char) buf.get()));
        }
    }
}
