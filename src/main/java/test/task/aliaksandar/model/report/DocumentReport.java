package test.task.aliaksandar.model.report;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

@XmlType(name = "Doc")
@XmlAccessorType(XmlAccessType.FIELD)
public class DocumentReport {

    @XmlElement(name = "DocNum")
    private Long docNum;

    @XmlElement(name = "DocDate")
    private Date date;

    @XmlElement(name = "DocGUID")
    private String guid;

    @XmlElement(name = "OperType")
    private Integer operType;

    @XmlElement(name = "AmountOut")
    private Double amountOut;

    public Long getDocNum() {
        return docNum;
    }

    public void setDocNum(Long docNum) {
        this.docNum = docNum;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Integer getOperType() {
        return operType;
    }

    public void setOperType(Integer operType) {
        this.operType = operType;
    }

    public Double getAmountOut() {
        return amountOut;
    }

    public void setAmountOut(Double amountOut) {
        this.amountOut = amountOut;
    }
}
