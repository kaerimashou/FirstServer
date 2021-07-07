package test.task.aliaksandar.service.implementation;

import org.apache.commons.io.IOUtils;
import org.apache.poi.util.CloseIgnoringInputStream;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import test.task.aliaksandar.enums.DocTypes;
import test.task.aliaksandar.models.report.DocumentReportList;
import test.task.aliaksandar.service.DocumentService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service("reportService")
public class ReportImpl implements DocumentService {
    private final DocTypes TYPE = DocTypes.REPORT;

    @Override
    public Object parseXML(MultipartFile file) {
        DocumentReportList doc = new DocumentReportList();
        try (ZipInputStream zin = new ZipInputStream(new CloseIgnoringInputStream(file.getInputStream()))) {
            ZipEntry entry;
            while ((entry = zin.getNextEntry()) != null) {
                String str = IOUtils.toString(zin, StandardCharsets.UTF_8);
                String tag = str.split("\n")[1];
                if (tag.equals(TYPE.getTag())) {
                    StringReader reader = new StringReader(str);
                    JAXBContext context = JAXBContext.newInstance(DocumentReportList.class);
                    Unmarshaller unmarshaller = context.createUnmarshaller();
                    doc = (DocumentReportList) unmarshaller.unmarshal(reader);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }

    @Override
    public DocTypes getDocType() {
        return DocTypes.REPORT;
    }
}
