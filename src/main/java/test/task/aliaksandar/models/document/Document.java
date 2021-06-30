package test.task.aliaksandar.models.document;

import test.task.aliaksandar.models.pay_docs.BankInfo;
import test.task.aliaksandar.models.pay_docs.DocumentPayDocs;
import test.task.aliaksandar.models.pay_docs.ParticipantInfo;
import test.task.aliaksandar.models.report.DocumentReport;

import java.util.Date;

public class Document {

    private final Long docNum;

    private final Date docDate;

    private final String docGUID;

    private final Integer operType;

    private final Double amountOut;

    private final ParticipantInfo payerInfo;

    private final BankInfo bankPayerInfo;

    private final ParticipantInfo receiverInfo;

    private final BankInfo bankReceiverInfo;

    private final String purpose;

    public Document(DocumentReport report, DocumentPayDocs payDocs){
        docNum = report.getDocNum();
        docDate = report.getDate();
        docGUID = report.getGuid();
        operType = report.getOperType();
        amountOut = report.getAmountOut();
        payerInfo = payDocs.getPayerInfo();
        bankPayerInfo = payDocs.getPayerBankInfo();
        receiverInfo = payDocs.getReceiverInfo();
        bankReceiverInfo=payDocs.getReceiverBankInfo();
        purpose=payDocs.getPurpose();
    }

    public Document(DocumentPayDocs payDocs, DocumentReport report) {
        docNum = report.getDocNum();
        docDate = report.getDate();
        docGUID = report.getGuid();
        operType = report.getOperType();
        amountOut = report.getAmountOut();
        payerInfo = payDocs.getPayerInfo();
        bankPayerInfo = payDocs.getPayerBankInfo();
        receiverInfo = payDocs.getReceiverInfo();
        bankReceiverInfo=payDocs.getReceiverBankInfo();
        purpose=payDocs.getPurpose();
    }

    public String getPurpose() {
        return purpose;
    }

    public Long getDocNum() {
        return docNum;
    }

    public Date getDocDate() {
        return docDate;
    }

    public String getDocGUID() {
        return docGUID;
    }

    public Integer getOperType() {
        return operType;
    }

    public Double getAmountOut() {
        return amountOut;
    }

    public ParticipantInfo getPayerInfo() {
        return payerInfo;
    }

    public BankInfo getBankPayerInfo() {
        return bankPayerInfo;
    }

    public ParticipantInfo getReceiverInfo() {
        return receiverInfo;
    }

    public BankInfo getBankReceiverInfo() {
        return bankReceiverInfo;
    }
}
