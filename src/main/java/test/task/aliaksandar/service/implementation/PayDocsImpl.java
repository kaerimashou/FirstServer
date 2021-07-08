package test.task.aliaksandar.service.implementation;

import org.springframework.stereotype.Service;
import test.task.aliaksandar.enums.DocType;
import test.task.aliaksandar.service.DocumentService;

@Service("payDocsService")
public class PayDocsImpl implements DocumentService {
    private final DocType TYPE = DocType.PAYDOCS;

    @Override
    public DocType getDocType() {
        return TYPE;
    }
}
