package com.lswq.jvm.linuxcommand;

/**
 * 虚拟机栈和本地方法栈溢出
 * <p>
 * VM Args: -Xssk
 *
 * @author zhangsw
 */
public class JavaVMStackSOF {

    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        System.err.println("stack length is " + stackLength);
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF oom = new JavaVMStackSOF();

        try {
            oom.stackLeak();
        } catch (Exception e) {
            System.err.println("stack length is " + oom.stackLength);
            throw e;
        }

    }

}
