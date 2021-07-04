package test.task.aliaksandar.models.pay_docs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ParticipantInfo {

    @XmlElement(name = "INN_PAY")
    private Long inn;

    @XmlElement(name = "KPP_PAY")
    private Long kpp;

    @XmlElement(name = "CName_PAY")
    private String name;

    public Long getInn() {
        return inn;
    }

    public void setInn(Long inn) {
        this.inn = inn;
    }

    public Long getKpp() {
        return kpp;
    }

    public void setKpp(Long kpp) {
        this.kpp = kpp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ParticipantInfo{" +
                "inn=" + inn +
                ", kpp=" + kpp +
                ", name='" + name + '\'' +
                '}';
    }
}
