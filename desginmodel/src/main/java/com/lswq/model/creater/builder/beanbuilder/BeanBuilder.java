package com.lswq.model.creater.builder.beanbuilder;

/**
 * 构建复杂对象的方法
 */
public class BeanBuilder {
    private final int a;
    private final int b;
    private final int c;
    private final int d;

    public static class Builder {
        //  required parameters
        private final int a;
        private final int b;

        //  optional parameters
        public Builder(int a, int b) {
            this.a = a;
            this.b = b;
        }

        private int c;
        private int d;

        public Builder c(int c) {
            this.c = c;
            return this;
        }

        public Builder d(int d) {
            this.d = d;
            return this;
        }

        public BeanBuilder builder() {
            return new BeanBuilder(this);
        }
    }


    public BeanBuilder(Builder builder) {
        this.a = builder.a;
        this.b = builder.b;
        this.c = builder.c;
        this.d = builder.d;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BeanBuilder{");
        sb.append("a=").append(a);
        sb.append(", b=").append(b);
        sb.append(", c=").append(c);
        sb.append(", d=").append(d);
        sb.append('}');
        return sb.toString();
    }

    public static void main(String[] args) {
        BeanBuilder builder = new BeanBuilder.Builder(1, 2).c(3).d(4).builder();
        System.err.println("bean builder is " + builder);
    }
}
