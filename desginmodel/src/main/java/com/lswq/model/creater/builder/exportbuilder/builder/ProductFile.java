package com.lswq.model.creater.builder.exportbuilder.builder;

import com.lswq.model.creater.builder.exportbuilder.builder.model.ExportDataModel;
import com.lswq.model.creater.builder.exportbuilder.builder.model.ExportFooterModel;
import com.lswq.model.creater.builder.exportbuilder.builder.model.ExportHeaderModel;

import java.util.Collection;
import java.util.Map;

public interface ProductFile {
    void data(Map<String, Collection<ExportDataModel>> mapData);

    void footer(ExportFooterModel efm);

    void header(ExportHeaderModel ehm);
}
