package com.lswq.model.creater.builder.pcbuilder;

public class Director {
    private ComputerProduct.Builder mBuilder;

    public Director(ComputerProduct.Builder builder) {
        this.mBuilder = builder;
    }

    public void buildPC(String board, String display) {
        mBuilder.buildBoard(board)
                .buildDisplay(display)
                .buildOS();
    }
}
