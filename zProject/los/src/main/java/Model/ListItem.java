package Model;

/**
 * Created by Rayhan on 12/24/2017.
 */

public class ListItem {
    String caseNo;
    String name;
    String loanType;
    String status;
    String loanAmount;

    public ListItem(String caseNo, String name, String loanType, String status, String loanAmount) {
        this.caseNo = caseNo;
        this.name = name;
        this.loanType = loanType;
        this.status = status;
        this.loanAmount = loanAmount;
    }
    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }
}

