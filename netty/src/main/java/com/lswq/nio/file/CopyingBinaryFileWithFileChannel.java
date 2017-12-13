package com.lswq.nio.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class CopyingBinaryFileWithFileChannel {

    public static void main(String[] args) {

        try {

            // source file channel
            // return the unique FileChannel object associated with this file input stream.
            FileChannel srcChannel = new FileInputStream("src.dat").getChannel();

            // destination file channel
            // return the unique FileChannel object associated with this file output stream.
            FileChannel dstChannel = new FileOutputStream("dst.dat").getChannel();

            // transfer bytes into this channel's file from the given readable byte channel
            dstChannel.transferFrom(srcChannel, 0, srcChannel.size());

            // close channels
            srcChannel.close();
            dstChannel.close();

        } catch (IOException e) {
            System.out.println("I/O Error: " + e.getMessage());
        }

    }

}
