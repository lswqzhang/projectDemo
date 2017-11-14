package com.lswq.model.structural.decorator.io.secound;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Client {


    public static void main(String[] args) throws IOException {
        bufferOutStream();
    }

    private static void bufferOutStream() throws IOException {
        //流式输出文件
        DataOutputStream dout = new DataOutputStream(new EncryptOutputStream(new BufferedOutputStream(new FileOutputStream("MyEncrypt.txt"))));
        dout.write("abcdxyz".getBytes());
        dout.close();

    }
}
