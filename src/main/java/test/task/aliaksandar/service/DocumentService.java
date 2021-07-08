package test.task.aliaksandar.service;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import test.task.aliaksandar.enums.DocType;
import test.task.aliaksandar.model.pay_docs.DocumentPayDocsList;
import test.task.aliaksandar.service.implementation.AppServiceImpl;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public interface DocumentService {
    DocType getDocType();

    default Object parseXML(MultipartFile file, DocType type) throws Exception {
        Object doc = type.getRequiredClass().getDeclaredConstructor().newInstance();
        try (ZipInputStream zin = new ZipInputStream(file.getInputStream())) {
            ZipEntry entry;
            while ((entry = zin.getNextEntry()) != null) {
                String str = IOUtils.toString(zin, StandardCharsets.UTF_8);
                String tag = str.split("\n")[1];
                if (tag.equals(type.getTag())) {
                    StringReader reader = new StringReader(str);
                    try {
                        JAXBContext context = JAXBContext.newInstance(type.getRequiredClass());
                        Unmarshaller unmarshaller = context.createUnmarshaller();
                        doc = unmarshaller.unmarshal(reader);
                    } catch (JAXBException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }

    @Autowired
    default void register(AppServiceImpl appService) {
        appService.register(getDocType(), this);
    }
}
