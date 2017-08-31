package com.lswq.model.creater.builder.pcbuilder;

/**
 * 作者：脐橙熟了
 * 链接：http://www.jianshu.com/p/c295e304508b
 * 來源：简书
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * <p>
 * 做了一些改进，把
 *
 * @see Builder#build 更改非抽象的方法，可以直接在此方法中实例类
 * 为了使用回炉方法，做的妥协，更改实抽象方法
 * <p>
 * {@link Builder#build()} 和 {@link ComputerProduct#ComputerProduct(Builder)}
 */
public abstract class ComputerProduct {

    protected String mBoard;
    protected String mDisplay;
    protected String mOS;


    protected ComputerProduct(Builder builder) {
        this.mBoard = builder.board;
        this.mDisplay = builder.display;
        this.mOS = builder.os;
    }


    public static abstract class Builder {

        protected String board;
        protected String display;
        protected String os;

        public Builder() {
        }

        public Builder(ComputerProduct product) {
            this.board = product.mBoard;
            this.display = product.mDisplay;
            this.os = product.mOS;
        }

        public abstract ComputerProduct.Builder buildBoard(String board);

        public abstract ComputerProduct.Builder buildDisplay(String display);

        public abstract ComputerProduct.Builder buildOS();

        /**
         * 在子类中实现，方便使用回炉方法进行处理
         *
         * @return
         */
        public abstract ComputerProduct build();
    }

    /**
     * 如果想要使用builder的回炉方法，必须有子类实现此方法，也可以不实现
     *
     * @return
     */
    public abstract Builder newBuilder();

    @Override
    public String toString() {
        return "ComputerProduct [mBoard=" + mBoard + ", mDisplay=" + mDisplay + ", mOS=" + mOS + "]";
    }
}
