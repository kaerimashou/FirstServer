package test.task.aliaksandar.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import test.task.aliaksandar.models.document.Document;
import test.task.aliaksandar.models.pay_docs.DocumentPayDocs;
import test.task.aliaksandar.models.pay_docs.DocumentPayDocsList;
import test.task.aliaksandar.models.report.DocumentReportList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service("appService")
public class AppService {

    private final File file = File.createTempFile("test", ".zip");

    public AppService() throws IOException {
    }

    public void setFile(MultipartFile file) throws IOException {
        file.transferTo(this.file);
    }

    public List<Document> getList() throws Exception {
        return documentList(file);
    }


    private String extractFromXML(File file, String fileName) {
        StringBuilder xml = new StringBuilder("");
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(file))) {
            ZipEntry entry;
            while ((entry = zin.getNextEntry()) != null) {
                if (entry.getName().equals(fileName)) {
                    int c;

                    while ((c = zin.read()) != -1) {
                        xml.append((char) c);
                    }
                    zin.closeEntry();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xml.toString();
    }

    private <T> T getListFromXML(File file, Class<T> requiredClass) throws Exception {
        String content;
        if (requiredClass == DocumentPayDocsList.class) {
            content = extractFromXML(file, "PayDocs.xml");
        } else if (requiredClass == DocumentReportList.class) {
            content = extractFromXML(file, "Report.xml");
        } else {
            return null;
        }
        StringReader reader = new StringReader(content);
        T doc = requiredClass.getDeclaredConstructor().newInstance();
        try {
            JAXBContext context = JAXBContext.newInstance(requiredClass);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            doc = requiredClass.cast(unmarshaller.unmarshal(reader));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return doc;
    }

    private List<Document> documentList(File file) throws Exception {
        DocumentReportList docReportList = getListFromXML(file, DocumentReportList.class);
        DocumentPayDocsList docPayDocsList = getListFromXML(file, DocumentPayDocsList.class);
        List<Document> docList = new ArrayList<Document>();
        if (docReportList == null || docPayDocsList == null) {
            return null;
        } else {
            for (DocumentPayDocs doc :
                    docPayDocsList.getAll()) {
                try {
                    docList.add(new Document(docReportList.getByGuid(doc.getGuid()), doc));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return docList;
    }


}