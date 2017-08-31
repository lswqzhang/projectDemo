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
 */
public class ComputerProduct {
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

        public abstract ComputerProduct.Builder buildBoard(String board);

        public abstract ComputerProduct.Builder buildDisplay(String display);

        public abstract ComputerProduct.Builder buildOS();

        public ComputerProduct build() {
            return new ComputerProduct(this);
        }
    }

    @Override
    public String toString() {
        return "ComputerProduct [mBoard=" + mBoard + ", mDisplay=" + mDisplay + ", mOS=" + mOS + "]";
    }
}
