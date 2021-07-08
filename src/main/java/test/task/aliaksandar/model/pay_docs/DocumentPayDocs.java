package test.task.aliaksandar.model.pay_docs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "Doc")
@XmlAccessorType(XmlAccessType.FIELD)
public class DocumentPayDocs {
    @XmlElement(name = "GUID")
    private String guid;

    @XmlElement(name = "Inf_PAY")
    private ParticipantInfo payerInfo;

    @XmlElement(name = "Bank_PAY")
    private BankInfo payerBankInfo;

    @XmlElement(name = "Inf_RCP")
    private ParticipantInfo receiverInfo;

    @XmlElement(name = "Bank_RCP")
    private BankInfo receiverBankInfo;

    @XmlElement(name = "Purpose")
    private String purpose;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public BankInfo getPayerBankInfo() {
        return payerBankInfo;
    }

    public void setPayerBankInfo(BankInfo payerBankInfo) {
        this.payerBankInfo = payerBankInfo;
    }

    public BankInfo getReceiverBankInfo() {
        return receiverBankInfo;
    }

    public void setReceiverBankInfo(BankInfo receiverBankInfo) {
        this.receiverBankInfo = receiverBankInfo;
    }

    public ParticipantInfo getPayerInfo() {
        return payerInfo;
    }

    public void setPayerInfo(ParticipantInfo payerInfo) {
        this.payerInfo = payerInfo;
    }

    public ParticipantInfo getReceiverInfo() {
        return receiverInfo;
    }

    public void setReceiverInfo(ParticipantInfo receiverInfo) {
        this.receiverInfo = receiverInfo;
    }
}