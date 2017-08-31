package com.lswq.model.creater.builder.pcbuilder.concreteproduct;

import com.lswq.model.creater.builder.pcbuilder.ComputerProduct;

public class MacBook extends ComputerProduct {
    
    public MacBook(Builder builder) {
        super(builder);
    }

    public static class Builder extends ComputerProduct.Builder {

        @Override
        public ComputerProduct.Builder buildBoard(String board) {
            this.board = board;
            return this;
        }

        @Override
        public ComputerProduct.Builder buildDisplay(String display) {
            this.display = display;
            return this;
        }

        @Override
        public ComputerProduct.Builder buildOS() {
            this.os = "Mac OS X 10.10";
            return this;
        }
    }
}


