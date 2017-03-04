package com.lswq.model.creater.factorymethod.specific;

import com.lswq.model.creater.factorymethod.ExportFileApi;
import com.lswq.model.creater.factorymethod.method.ExportOperateCreator;

/**
 * 具体的创建器实现对象，实现创建导出成文本文件格式的对象
 * <p>
 * Created by zhangsw on 2017/2/26.
 */
public class ExportTxtFileOperateCreator extends ExportOperateCreator {
    @Override
    protected ExportFileApi factoryMethod() {
        //创建导出成文本文件格式的对象
        return new ExportTxtFile();
    }
}
