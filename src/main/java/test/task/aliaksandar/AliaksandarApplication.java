package test.task.aliaksandar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
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

@SpringBootApplication
public class AliaksandarApplication {

    private static File file;

    static {
        try {
            file = File.createTempFile("test",".zip");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(AliaksandarApplication.class, args);
    }

    public static AliaksandarApplication getInstance(){
        return new AliaksandarApplication();
    }

    private String payDocs() {
        StringBuilder payDocs = new StringBuilder("");
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(file))) {
            ZipEntry entry;
            while ((entry = zin.getNextEntry()) != null) {
                if(entry.getName().equals("PayDocs.xml")){
                    int c;

                    while ((c = zin.read()) != -1) {
                        payDocs.append((char) c);
                    }
                    zin.closeEntry();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payDocs.toString();
    }

    private String report() {
        StringBuilder report = new StringBuilder("");
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(file))) {
            ZipEntry entry;
            while ((entry = zin.getNextEntry()) != null) {
                if(entry.getName().equals("Report.xml")){
                    int c;
                    while ((c = zin.read()) != -1) {
                        report.append((char) c);
                    }
                    zin.closeEntry();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return report.toString();
    }

    private DocumentPayDocsList documentPayDocsList() {
        String content=payDocs();
        StringReader reader=new StringReader(content);
        DocumentPayDocsList doc=new DocumentPayDocsList();
        try{
            JAXBContext context = JAXBContext.newInstance(DocumentPayDocsList.class);
            Unmarshaller unmarshaller=context.createUnmarshaller();
            doc=(DocumentPayDocsList)unmarshaller.unmarshal(reader);
        }catch(JAXBException e){
            e.printStackTrace();
        }
        return doc;
    }

    private DocumentReportList documentReportList() {
        String content=report();
        StringReader reader=new StringReader(content);
        DocumentReportList doc=new DocumentReportList();
        try {
            JAXBContext context = JAXBContext.newInstance(DocumentReportList.class);
            Unmarshaller unmarshaller=context.createUnmarshaller();
            doc=(DocumentReportList)unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return doc;
    }

    public List<Document> documentList(){
        DocumentReportList docReportList=documentReportList();
        DocumentPayDocsList docPayDocsList=documentPayDocsList();
        List<Document> docList=new ArrayList<Document>();
        for (DocumentPayDocs doc:
             docPayDocsList.getAll()) {
            try {
                docList.add(new Document(docReportList.getByGuid(doc.getGuid()),doc));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return docList;
    }


    public static File getFile() {
        return file;
    }

    public static void setFile(MultipartFile file) throws IOException {
        file.transferTo(AliaksandarApplication.file);
    }

}
