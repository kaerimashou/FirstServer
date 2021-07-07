package test.task.aliaksandar.service;

import org.springframework.web.multipart.MultipartFile;
import test.task.aliaksandar.enums.DocTypes;
import test.task.aliaksandar.models.document.Document;

import java.util.List;

public interface AppService {
    void post(MultipartFile file) throws Exception;

    void register(DocTypes type, DocumentService service);

    List<Document> documentList(MultipartFile file) throws Exception;
}
