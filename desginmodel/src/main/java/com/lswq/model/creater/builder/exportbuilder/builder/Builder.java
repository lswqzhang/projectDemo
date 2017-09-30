package com.lswq.model.creater.builder.exportbuilder.builder;

import com.lswq.model.creater.builder.exportbuilder.builder.model.ExportDataModel;
import com.lswq.model.creater.builder.exportbuilder.builder.model.ExportFooterModel;
import com.lswq.model.creater.builder.exportbuilder.builder.model.ExportHeaderModel;

import java.util.Collection;
import java.util.Map;

/**
 * 生成器接口，定义创建一个输出文件对象所需的各个部件的操作
 * <p>
 * 作者：陶邦仁
 * 链接：http://www.jianshu.com/p/5d34a496e517
 * 來源：简书
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * <p>
 * 修改：张韶伟
 */
public interface Builder {
    /**
     * 构建输出文件的Header部分
     *
     * @param ehm 文件头的内容
     */
    void buildHeader(ExportHeaderModel ehm);

    /**
     * 构建输出文件的Body部分
     *
     * @param mapData 要输出的数据的内容
     */
    void buildBody(Map<String, Collection<ExportDataModel>> mapData);

    /**
     * 构建输出文件的Footer部分
     *
     * @param efm 文件尾的内容
     */
    void buildFooter(ExportFooterModel efm);

    /**
     * @return
     */
    ProductFile getResult();
}


