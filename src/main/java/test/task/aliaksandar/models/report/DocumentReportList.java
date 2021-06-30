package test.task.aliaksandar.models.report;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name="SKP_REPORT_KS")
@XmlAccessorType(XmlAccessType.FIELD)
public class DocumentReportList {
    @XmlElementWrapper(name = "Docs")
    @XmlElement(name="Doc")
    private List<DocumentReport> documentReportList;

    public List<DocumentReport> getAll() {
        return documentReportList;
    }

    public DocumentReport getByGuid(String guid) throws Exception {
        return documentReportList.stream().filter(i->i.getGuid().equals(guid)).findFirst().orElseThrow(Exception::new);
    }
}
