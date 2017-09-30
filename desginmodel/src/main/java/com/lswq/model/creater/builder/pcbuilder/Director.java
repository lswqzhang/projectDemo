package com.lswq.model.creater.builder.pcbuilder;

public class Director {
    private ComputerProduct.Builder mBuilder;

    public Director(ComputerProduct.Builder builder) {
        this.mBuilder = builder;
    }

    public ComputerProduct buildPC(String board, String display) {
        return mBuilder.buildBoard(board)
                .buildDisplay(display)
                .buildOS()
                .build();
    }
}
