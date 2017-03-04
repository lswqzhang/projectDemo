package com.lswq.zhang.datastruct.stack;

/**
 * Created by zhangsw on 2016/12/9.
 */
public interface StackI<E> {
    int length();//返回栈的长度

    E pop();//出栈

    void push(E element);//进栈

    E peek();//访问栈顶元素

    boolean empty();//判断栈是否为空

    void clear();//清空栈
}
