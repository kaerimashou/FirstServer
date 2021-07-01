package test.task.aliaksandar.service;

import test.task.aliaksandar.models.document.Document;

import java.io.File;
import java.util.List;

public interface IAppService {
    void post();
    String extractFromXML(File file,String filename);
    <T> T getListFromXML(File file,Class<T> requiredClass) throws Exception;
    List<Document> documentList(File file) throws Exception;
}
