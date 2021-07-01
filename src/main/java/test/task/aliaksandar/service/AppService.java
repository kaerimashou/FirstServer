package test.task.aliaksandar.service;

import org.springframework.web.multipart.MultipartFile;
import test.task.aliaksandar.models.document.Document;

import java.io.File;
import java.util.List;

public interface AppService {
    void post(MultipartFile file) throws Exception;
    String extractFromXML(MultipartFile file, String filename) throws Exception;
    <T> T getListFromXML(MultipartFile file,Class<T> requiredClass) throws Exception;
    List<Document> documentList(MultipartFile file) throws Exception;
}
