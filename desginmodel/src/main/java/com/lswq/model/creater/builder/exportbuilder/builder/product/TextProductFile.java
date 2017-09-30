package com.lswq.model.creater.builder.exportbuilder.builder.product;

import com.lswq.model.creater.builder.exportbuilder.builder.ProductFile;
import com.lswq.model.creater.builder.exportbuilder.builder.model.ExportDataModel;
import com.lswq.model.creater.builder.exportbuilder.builder.model.ExportFooterModel;
import com.lswq.model.creater.builder.exportbuilder.builder.model.ExportHeaderModel;

import java.util.Collection;
import java.util.Map;

public class TextProductFile implements ProductFile {

    private StringBuffer buffer;

    public TextProductFile(StringBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void data(Map<String, Collection<ExportDataModel>> mapData) {
        for (String tblName : mapData.keySet()) {
            //先拼接表名称
            buffer.append(tblName + "\n");
            //然后循环拼接具体数据
            for (ExportDataModel edm : mapData.get(tblName)) {
                buffer.append(edm.getProductId() + "," + edm.getPrice() + "," + edm.getAmount() + "\n");
            }
        }
    }

    @Override
    public void footer(ExportFooterModel efm) {
        buffer.append(efm.getExportUser());
    }

    @Override
    public void header(ExportHeaderModel ehm) {
        buffer.append(ehm.getDepId() + "," + ehm.getExportDate() + "\n");
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TextProductFile{");
        sb.append("\n").append("buffer=").append(buffer).append("\n");
        sb.append('}');
        return sb.toString();
    }
}
