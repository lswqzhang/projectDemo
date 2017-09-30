package com.lswq.model.creater.builder.exportbuilder.builder.xmlbuilder;

import com.lswq.model.creater.builder.exportbuilder.builder.Builder;
import com.lswq.model.creater.builder.exportbuilder.builder.ProductFile;
import com.lswq.model.creater.builder.exportbuilder.builder.model.ExportDataModel;
import com.lswq.model.creater.builder.exportbuilder.builder.model.ExportFooterModel;
import com.lswq.model.creater.builder.exportbuilder.builder.model.ExportHeaderModel;
import com.lswq.model.creater.builder.exportbuilder.builder.product.XMLProductFile;

import java.util.Collection;
import java.util.Map;

/**
 * 实现导出数据到XML文件的的生成器对象
 */
public class XmlBuilder implements Builder {
    /**
     * 用来记录构建的文件的内容，相当于产品
     */
    private ProductFile product = new XMLProductFile(new StringBuffer());

    @Override
    public void buildBody(Map<String, Collection<ExportDataModel>> mapData) {
        product.data(mapData);
    }

    @Override
    public void buildFooter(ExportFooterModel efm) {
        product.footer(efm);
    }

    @Override
    public void buildHeader(ExportHeaderModel ehm) {
        product.header(ehm);
        
        
    }

    public ProductFile getResult() {
        return product;
    }
}