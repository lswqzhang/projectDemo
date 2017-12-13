package com.lswq.model.structural.decorator.io.first;

import java.io.*;

/**
 * 作者：陶邦仁
 * 链接：http://www.jianshu.com/p/9a7f5d81cc51
 * 來源：简书
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class Client {

    public static void main(String[] args) throws IOException {
        fileOutStream();

    }

    private static void fileOutStream() throws IOException {
        //流式输出文件
        //这是我们加的装饰器
        DataOutputStream dout = new DataOutputStream(new BufferedOutputStream(new EncryptOutputStream(new FileOutputStream("MyEncrypt.txt"))));
        //然后就可以输出内容了
        dout.write("abcdxyz".getBytes());
        dout.close();
    }
}
