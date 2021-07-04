package test.task.aliaksandar.models.pay_docs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigInteger;

@XmlAccessorType(XmlAccessType.FIELD)
public class BankInfo {

    @XmlElement(name = "BS_PAY")
    private BigInteger BAcc;

    @XmlElement(name = "BIC_PAY")
    private Long bic;

    @XmlElement(name = "BS_KS_PAY")
    private BigInteger TAcc;

    public BigInteger getBAcc() {
        return BAcc;
    }

    public void setBAcc(BigInteger BAcc) {
        this.BAcc = BAcc;
    }

    public Long getBic() {
        return bic;
    }

    public void setBic(Long bic) {
        this.bic = bic;
    }

    public BigInteger getTAcc() {
        return TAcc;
    }

    public void setTAcc(BigInteger TAcc) {
        this.TAcc = TAcc;
    }

    @Override
    public String toString() {
        return "BankInfo{" +
                "BAcc=" + BAcc +
                ", bic=" + bic +
                ", TAcc=" + TAcc +
                '}';
    }
}

