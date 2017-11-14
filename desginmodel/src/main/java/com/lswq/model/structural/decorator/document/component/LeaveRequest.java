package com.lswq.model.structural.decorator.document.component;

public class LeaveRequest implements Document {
    @Override
    public void display() {
        System.err.println("显示请假单！"); 
    }
}
