package com.csi.institutionsprofile.model;

/**
 * Created by Jahid on 11/8/17.
 */

public class Stakeholder {
    private int id;
    private String name;
    private String designation;
    private int division;
    private String district;
    private String mobile;
    private String email;

    public Stakeholder(int id, String name, String designation, int division, String district, String mobile, String email){
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.division = division;
        this.district = district;
        this.mobile = mobile;
        this.email = email;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getDivision() {
        return division;
    }

    public void setDivision(int division) {
        this.division = division;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        return email;
    }

}
