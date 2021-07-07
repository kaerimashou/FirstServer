package test.task.aliaksandar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import test.task.aliaksandar.enums.DocTypes;
import test.task.aliaksandar.service.implementation.AppServiceImpl;

public interface DocumentService {
    Object parseXML(MultipartFile file);

    DocTypes getDocType();

    @Autowired
    default void register(AppServiceImpl appService) {
        appService.register(getDocType(), this);
    }
}
