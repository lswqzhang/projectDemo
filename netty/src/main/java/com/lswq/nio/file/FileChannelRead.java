package com.lswq.nio.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileChannelRead {


    public static void main(String[] args) throws Exception {
        new FileChannelRead().readFile();
    }

    private void readFile() throws IOException {

        String filePath = "/Users/zhangsw/Desktop/资产盘点系统上线.rtf";
        printFileContents(filePath);
        Path path = Paths.get(filePath);

        FileChannel fileChannel = FileChannel.open(path);
        ByteBuffer buffer = ByteBuffer.allocate(6);
        int noOfBytesRead = fileChannel.read(buffer);

        while (noOfBytesRead != -1) {
            System.out.println("Number of bytes read: " + noOfBytesRead);
            buffer.flip();
            System.out.print("Buffer contents: ");

            while (buffer.hasRemaining()) {
                System.out.print((char) buffer.get());
            }

            System.out.println(" ");
            buffer.clear();
            noOfBytesRead = fileChannel.read(buffer);
        }
        fileChannel.close();
    }

    private void printFileContents(String path)
            throws IOException {

        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);
        String textRead = br.readLine();
        System.out.println("File contents: ");

        while (textRead != null) {
            System.out.println("     " + textRead);
            textRead = br.readLine();
        }
        fr.close();
        br.close();
    }

}
