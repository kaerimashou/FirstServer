package test.task.aliaksandar.service.implementation;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import test.task.aliaksandar.enums.DocTypes;
import test.task.aliaksandar.models.pay_docs.DocumentPayDocsList;
import test.task.aliaksandar.service.DocumentService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service("payDocsService")
public class PayDocsImpl implements DocumentService {
    private final DocTypes TYPE = DocTypes.PAYDOCS;

    @Override
    public Object parseXML(MultipartFile file) {
        DocumentPayDocsList doc = new DocumentPayDocsList();
        try (ZipInputStream zin = new ZipInputStream(file.getInputStream())) {
            ZipEntry entry;
            while ((entry = zin.getNextEntry()) != null) {
                String str = IOUtils.toString(zin, StandardCharsets.UTF_8);
                String tag = str.split("\n")[1];
                if (tag.equals(TYPE.getTag())) {
                    StringReader reader = new StringReader(str);
                    try {
                        JAXBContext context = JAXBContext.newInstance(DocumentPayDocsList.class);
                        Unmarshaller unmarshaller = context.createUnmarshaller();
                        doc = (DocumentPayDocsList) unmarshaller.unmarshal(reader);
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

    @Override
    public DocTypes getDocType() {
        return TYPE;
    }
}
