package com.lswq.app.util.report;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

public class ExportUtils {

    private ExportUtils (){
        
    }
    
    
    /**
     * 导出数据的controller层
     * @param response          HttpServletResponse返回对象
     * @param is                生成报表的输入流
     * @param fileName          生成报表的名称
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    public static void exportExcelFile(HttpServletResponse response, InputStream is, String fileName) throws IOException {
        // 设置下载头
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName + ".xlsx").getBytes(), "iso-8859-1"));
        // 获取servlet输出流
        ServletOutputStream out = response.getOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(is);
            // 输入流转换为BufferedOutputStream
            bos = new BufferedOutputStream(out);
            // 设置缓冲区大小
            byte[] buff = new byte[4096];
            // 读取数据的大小
            int bytesRead;
            // 从inputstream中获取流并写入到输入流到下载
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (final IOException e) {
            throw e;
        } finally {
            if (is != null) {
                is.close();
            }
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
    }
    
    
    
    /**
     * 导出数据的controller层
     * @param response          HttpServletResponse返回对象
     * @param is                生成报表的输入流
     * @param fileName          生成报表的名称
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    public static void exportExcelFile(HttpServletResponse response, OutputStream out, String fileName) throws IOException {
        //  输出流转换为输入流
        ByteArrayOutputStream baos= new ByteArrayOutputStream();
        baos = (ByteArrayOutputStream) out;
        InputStream is = new ByteArrayInputStream(baos.toByteArray());
        baos.close();
        out.close();
        
        // 设置下载头
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName + ".xlsx").getBytes(), "iso-8859-1"));
        // 获取servlet输出流
        ServletOutputStream serverOut = response.getOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(is);
            // 输入流转换为BufferedOutputStream
            bos = new BufferedOutputStream(serverOut);
            // 设置缓冲区大小
            byte[] buff = new byte[4096];
            // 读取数据的大小
            int bytesRead;
            // 从inputstream中获取流并写入到输入流到下载
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (final IOException e) {
            throw e;
        } finally {
            is.close();
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
    }
}
