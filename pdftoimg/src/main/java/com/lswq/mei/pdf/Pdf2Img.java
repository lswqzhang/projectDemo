package com.lswq.mei.pdf;


import org.icepdf.core.pobjects.Document;
import org.icepdf.core.pobjects.Page;
import org.icepdf.core.util.GraphicsRenderingHints;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * PDF转图片
 */
public class Pdf2Img {


    private static final String PATH = "/Users/zhangshaowei/Desktop/";
    private static final File file = new File(PATH + "test.pdf");


    /**
     * 转换指定pdf文件为图片到指定的文件夹目录下
     *
     * @param pdfFilePath 需要转换的pdf文件路径
     * @param imgPushPath 需要存放转换后的图片文件目录路径
     * @param toFormat    需要转换的图片格式(如:jpg/png等)
     * @param imgScaling  图片缩放的比例
     * @return 转换后图片的文件名集合
     */

    public static List<String> converPdfToImg(String pdfFilePath, String imgPushPath, String toFormat, float imgScaling) {

        //定义Document,用于转换图片

        Document document = new Document();

        List<String> filePathList = new ArrayList<String>();

        try {

            document.setFile(pdfFilePath);

        } catch (Exception ex) {

            ex.printStackTrace();

        }

        // save page caputres to file.

        float rotation = 0f;

        // 循环把每页的数据转换成对应的图片

        for (int i = 0; i < document.getNumberOfPages(); i++) {

            BufferedImage image = (BufferedImage) document.getPageImage(i, GraphicsRenderingHints.SCREEN, Page.BOUNDARY_CROPBOX, rotation, imgScaling);

            RenderedImage rendImage = image;

            try {

                System.out.println("/t capturing page " + i);

                File file = new File(imgPushPath + i + "." + toFormat);

                ImageIO.write(rendImage, toFormat, file);

                filePathList.add(i + "." + toFormat);

            } catch (IOException e) {

                e.printStackTrace();

            }

            image.flush();

        }

        // 清理document资源

        document.dispose();


        return filePathList;

    }

    public static void main(final String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Pdf2Img.converPdfToImg(PATH + "test.pdf", PATH, "jpg", 1);
            }
        });
    }
}
