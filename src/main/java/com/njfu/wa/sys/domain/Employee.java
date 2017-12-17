package com.njfu.wa.sys.domain;

/**
 * 员工
 */
public class Employee {

    private String empId;

    private String empName;

    private String empTel;

    private String empPosition;

    private Integer empAge;

    private String empSex;

    private String empMail;

    private Short mailStatus;

    private String empPs;

    public Employee() {
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpTel() {
        return empTel;
    }

    public void setEmpTel(String empTel) {
        this.empTel = empTel;
    }

    public String getEmpPosition() {
        return empPosition;
    }

    public void setEmpPosition(String empPosition) {
        this.empPosition = empPosition;
    }

    public Integer getEmpAge() {
        return empAge;
    }

    public void setEmpAge(Integer empAge) {
        this.empAge = empAge;
    }

    public String getEmpSex() {
        return empSex;
    }

    public void setEmpSex(String empSex) {
        this.empSex = empSex;
    }

    public String getEmpMail() {
        return empMail;
    }

    public void setEmpMail(String empMail) {
        this.empMail = empMail;
    }

    public Short getMailStatus() {
        return mailStatus;
    }

    public void setMailStatus(Short mailStatus) {
        this.mailStatus = mailStatus;
    }

    public String getEmpPs() {
        return empPs;
    }

    public void setEmpPs(String empPs) {
        this.empPs = empPs;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId='" + empId + '\'' +
                ", empName='" + empName + '\'' +
                ", empTel='" + empTel + '\'' +
                ", empPosition='" + empPosition + '\'' +
                ", empAge=" + empAge +
                ", empSex='" + empSex + '\'' +
                ", empMail='" + empMail + '\'' +
                ", mailStatus=" + mailStatus +
                ", empPs='" + empPs + '\'' +
                '}';
    }
}
