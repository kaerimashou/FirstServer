package test.task.aliaksandar.enums;

public enum DocTypes {
    PAYDOCS("<Inf_Pay_Doc>\r"),
    REPORT("<SKP_REPORT_KS>\r");

    private String tag;
    DocTypes(String tag){
        this.tag=tag;
    }

    public String getTag() {
        return tag;
    }
}
