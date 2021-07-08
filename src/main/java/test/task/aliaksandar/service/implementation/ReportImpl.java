package test.task.aliaksandar.service.implementation;

import org.springframework.stereotype.Service;
import test.task.aliaksandar.enums.DocType;
import test.task.aliaksandar.service.DocumentService;

@Service("reportService")
public class ReportImpl implements DocumentService {
    private final DocType TYPE = DocType.REPORT;

    @Override
    public DocType getDocType() {
        return DocType.REPORT;
    }
}
