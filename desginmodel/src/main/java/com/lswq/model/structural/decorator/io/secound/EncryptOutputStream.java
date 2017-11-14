package com.lswq.model.structural.decorator.io.secound;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class EncryptOutputStream extends FilterOutputStream {
    private OutputStream os = null;

    public EncryptOutputStream(OutputStream os) {
        //调用父类的构造方法
        super(os);
    }

    public void write(int a) throws IOException {
        //先统一向后移动两位
        a = a + 2;
        //97是小写的a的码值
        if (a >= (97 + 26)) {
            //如果大于，表示已经是y或者z了，减去26就回到a或者b了
            a = a - 26;
        }
        //调用父类的方法
        super.write(a);
    }

}
