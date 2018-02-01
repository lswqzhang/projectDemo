package com.lswq.nio.file;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

public class FileCopy {
    public static void main(String[] args) throws IOException {
        nioReadFile();
        nioWriteFile();
        System.err.println();
        System.err.println("buffer analysis");
        bufferAnalysis();
    }


    /**
     * @throws IOException
     */
    private static void nioWriteFile() throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("/Users/zhangsw/Desktop/nioWrite.rtf", "rw");
        FileChannel channel = aFile.getChannel();
        String newData = "New String to write to file..." + System.currentTimeMillis();
        ByteBuffer buf = ByteBuffer.allocate(4096);
        buf.clear();
        buf.put(newData.getBytes());
        buf.flip();
        while (buf.hasRemaining()) {
            channel.write(buf);
        }
        channel.close();
    }


    /**
     * nio 文件读取
     *
     * @throws IOException
     */
    private static void nioReadFile() throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("/Users/zhangsw/Desktop/test.rtf", "rw");
        FileChannel inChannel = aFile.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(4096);
        int read = inChannel.read(buffer);
        while (read != -1) {
            buffer.flip();
            while (buffer.hasRemaining()) {
                System.out.print((char) buffer.get());
            }
            buffer.clear();
            read = inChannel.read(buffer);
        }
        aFile.close();
    }

    private static void bufferAnalysis() {
        IntBuffer intBuffer = IntBuffer.allocate(10);
        intBuffer.put(10);
        intBuffer.put(101);
        System.err.println("Write mode: ");
        System.err.println("\tCapacity: " + intBuffer.capacity());
        System.err.println("\tPosition: " + intBuffer.position());
        System.err.println("\tLimit: " + intBuffer.limit());

        intBuffer.flip();
        System.err.println("Read mode: ");
        System.err.println("\tCapacity: " + intBuffer.capacity());
        System.err.println("\tPosition: " + intBuffer.position());
        System.err.println("\tLimit: " + intBuffer.limit());
    }
}
