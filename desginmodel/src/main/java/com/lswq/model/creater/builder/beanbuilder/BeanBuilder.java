package com.lswq.model.creater.builder.beanbuilder;

/**
 * 构建复杂对象的方法
 * <p>
 * Builder模式变换之省略抽象Builder角色
 * <p>
 * 如果Builder模式中的ConcreteBuilder只有一个，那么抽象的Builder可以省略
 * <p>
 * 省略指挥者角色，省略抽象Builder角色，整个Builder模式只剩下两个角色
 * <p>
 * 外部类和内部类的构造函数，是否有种对称美？正是这种美，巧妙地完成了从Product重回Builder的逆向过程。
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

        //  逆向过程
        public Builder(BeanBuilder builder) {
            this.a = builder.a;
            this.b = builder.b;
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

    public Builder newBuilder() {
        return new Builder(this);
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
        BeanBuilder builder = new BeanBuilder.Builder(1, 2).builder();
        System.err.println("bean builder is " + builder);

        BeanBuilder copy = builder.newBuilder().c(3).d(4).builder();
        System.err.println("bean back builder copy is " + copy);
    }
}
