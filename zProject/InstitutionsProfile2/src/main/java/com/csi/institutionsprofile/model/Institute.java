package com.csi.institutionsprofile.model;

/**
 * Created by Jahid on 11/11/17.
 */

public class Institute {
    int id;
    String institute_name;
    String division;
    String district;
    String upazilla;
    String type;
    String category;
    String principle_name;
    String designition;
    String mobile;
    String email;

    public Institute(String mobile,String email) {
        this.mobile = mobile;
        this.email = email;
    }

    public Institute(int id, String institute_name, String division, String district, String upazilla, String type, String category, String principle_name, String mobile, String email){
        this.id = id;
        this.institute_name = institute_name;
        this.division = division;
        this.district = district;
        this.upazilla = upazilla;
        this.type = type;
        this.category = category;
        this.principle_name = principle_name;
        //this.designition = designition;
        this.mobile = mobile;
        this.email = email;

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInstitute_name() {
        return institute_name;
    }

    public void setInstitute_name(String institute_name) {
        this.institute_name = institute_name;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getUpazilla() {
        return upazilla;
    }

    public void setUpazilla(String upazilla) {
        this.upazilla = upazilla;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrinciple_name() {
        return principle_name;
    }

    public void setPrinciple_name(String principle_name) {
        this.principle_name = principle_name;
    }

    public String getDesignition() {
        return designition;
    }

    public void setDesignition(String designition) {
        this.designition = designition;
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

}
