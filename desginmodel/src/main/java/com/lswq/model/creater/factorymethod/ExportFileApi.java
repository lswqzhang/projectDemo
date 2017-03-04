package com.lswq.model.creater.factorymethod;

/**
 * 导出的文件对象的接口
 * <p>
 * Created by zhangsw on 2017/2/26.
 */
public interface ExportFileApi {
    /**
     * 导出内容成为文件
     *
     * @param data 示意：需要保存的数据
     * @return 是否导出成功
     */
    boolean export(String data);
}
