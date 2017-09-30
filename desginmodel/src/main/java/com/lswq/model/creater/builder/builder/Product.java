package com.lswq.model.creater.builder.builder;

/**
 * 复杂的产品
 */
public class Product {

    private String partA;
    private String partB;
    private String partC;


    public String getPartA() {
        return partA;
    }

    public void setPartA(String partA) {
        this.partA = partA;
    }

    public String getPartB() {
        return partB;
    }

    public void setPartB(String partB) {
        this.partB = partB;
    }

    public String getPartC() {
        return partC;
    }

    public void setPartC(String partC) {
        this.partC = partC;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Product{");
        sb.append("partA='").append(partA).append('\'');
        sb.append(", partB='").append(partB).append('\'');
        sb.append(", partC='").append(partC).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
