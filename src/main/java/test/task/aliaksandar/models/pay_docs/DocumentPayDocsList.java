package test.task.aliaksandar.models.pay_docs;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name="Inf_Pay_Doc")
@XmlAccessorType(XmlAccessType.FIELD)
public class DocumentPayDocsList {
    @XmlElement(name="Doc")
    @XmlElementWrapper(name="Docs")
    private List<DocumentPayDocs> documentPayDocsList;

    public List<DocumentPayDocs> getAll(){
        return documentPayDocsList;
    }

    public DocumentPayDocs getByGuid(String guid) throws Exception {
        return documentPayDocsList.stream().filter(i-> i.getGuid().equals(guid)).findFirst().orElseThrow(Exception::new);
    }
}