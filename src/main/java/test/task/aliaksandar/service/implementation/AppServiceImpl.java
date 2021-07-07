package test.task.aliaksandar.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import test.task.aliaksandar.enums.DocTypes;
import test.task.aliaksandar.models.document.Document;
import test.task.aliaksandar.models.pay_docs.DocumentPayDocs;
import test.task.aliaksandar.models.pay_docs.DocumentPayDocsList;
import test.task.aliaksandar.models.report.DocumentReportList;
import test.task.aliaksandar.service.AppService;
import test.task.aliaksandar.service.DocumentService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("appService")
public class AppServiceImpl implements AppService {

    private final RestTemplate restTemplate;
    private Map<DocTypes, DocumentService> serviceMap = new HashMap<>();

    @Value("${second.server.url}")
    private String URL;

    @Autowired
    public AppServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void post(MultipartFile file) throws Exception {
        restTemplate.postForLocation(URL, documentList(file));
    }

    @Override
    public void register(DocTypes type, DocumentService service) {
        serviceMap.put(type, service);
    }

    @Override
    public List<Document> documentList(MultipartFile file) throws Exception {
        DocumentReportList docReportList = (DocumentReportList) serviceMap.get(DocTypes.REPORT).parseXML(file);
        DocumentPayDocsList docPayDocsList = (DocumentPayDocsList) serviceMap.get(DocTypes.PAYDOCS).parseXML(file);
        List<Document> docList = new ArrayList<>();
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
