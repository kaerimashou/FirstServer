package test.task.aliaksandar.service;

import org.springframework.web.multipart.MultipartFile;
import test.task.aliaksandar.enums.DocType;
import test.task.aliaksandar.model.document.Document;

import java.util.List;

public interface AppService {
    void post(MultipartFile file) throws Exception;

    void register(DocType type, DocumentService service);

    List<Document> documentList(MultipartFile file) throws Exception;
}
