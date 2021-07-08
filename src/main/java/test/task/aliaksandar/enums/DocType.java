package test.task.aliaksandar.enums;

import test.task.aliaksandar.model.pay_docs.DocumentPayDocsList;
import test.task.aliaksandar.model.report.DocumentReportList;

public enum DocType {
    PAYDOCS("<Inf_Pay_Doc>\r", DocumentPayDocsList.class),
    REPORT("<SKP_REPORT_KS>\r", DocumentReportList.class);

    private final String tag;
    private final Class requiredClass;

    DocType(String tag, Class requiredClass) {
        this.tag = tag;
        this.requiredClass = requiredClass;
    }

    public String getTag() {
        return tag;
    }

    public Class getRequiredClass() {
        return requiredClass;
    }
}
