package com.lswq.model.creater.builder.exportbuilder.builder.product;

import com.lswq.model.creater.builder.exportbuilder.builder.ProductFile;
import com.lswq.model.creater.builder.exportbuilder.builder.model.ExportDataModel;
import com.lswq.model.creater.builder.exportbuilder.builder.model.ExportFooterModel;
import com.lswq.model.creater.builder.exportbuilder.builder.model.ExportHeaderModel;

import java.util.Collection;
import java.util.Map;

public class XMLProductFile implements ProductFile {


    private StringBuffer buffer;


    public XMLProductFile(StringBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void data(Map<String, Collection<ExportDataModel>> mapData) {
        buffer.append("  <Body>\n");
        for (String tblName : mapData.keySet()) {
            //先拼接表名称
            buffer.append("    <Datas TableName=\"" + tblName + "\">\n");
            //然后循环拼接具体数据
            for (ExportDataModel edm : mapData.get(tblName)) {
                buffer.append("      <Data>\n");
                buffer.append("        <ProductId>" + edm.getProductId() + "</ProductId>\n");
                buffer.append("        <Price>" + edm.getPrice() + "</Price>\n");
                buffer.append("        <Amount>" + edm.getAmount() + "</Amount>\n");
                buffer.append("      </Data>\n");
            }
            buffer.append("    </Datas>\n");
        }
        buffer.append("  </Body>\n");
    }

    @Override
    public void footer(ExportFooterModel efm) {
        buffer.append("  <Footer>\n");
        buffer.append("    <ExportUser>" + efm.getExportUser() + "</ExportUser>\n");
        buffer.append("  </Footer>\n");
        buffer.append("</Report>\n");
    }

    @Override
    public void header(ExportHeaderModel ehm) {
        buffer.append("<?xml version='1.0' encoding='gb2312'?>\n");
        buffer.append("<Report>\n");
        buffer.append("  <Header>\n");
        buffer.append("    <DepId>" + ehm.getDepId() + "</DepId>\n");
        buffer.append("    <ExportDate>" + ehm.getExportDate() + "</ExportDate>\n");
        buffer.append("  </Header>\n");
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("XMLProductFile{");
        sb.append("\n").append("buffer=").append(buffer).append("\n");
        sb.append('}');
        return sb.toString();
    }
}
