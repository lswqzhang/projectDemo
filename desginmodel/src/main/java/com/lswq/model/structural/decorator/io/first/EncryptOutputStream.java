package com.lswq.model.structural.decorator.io.first;

import java.io.IOException;
import java.io.OutputStream;

public class EncryptOutputStream extends OutputStream {

    private OutputStream os;

    public EncryptOutputStream(OutputStream os) {
        this.os = os;
    }

    @Override
    public void write(int b) throws IOException {
        //先统一向后移动两位
        b = b + 2;
        //97是小写的a的码值
        if (b >= (97 + 26)) {
            //如果大于，表示已经是y或者z了，减去26就回到a或者b了
            b = b - 26;
        }
        this.os.write(b);
    }
}
