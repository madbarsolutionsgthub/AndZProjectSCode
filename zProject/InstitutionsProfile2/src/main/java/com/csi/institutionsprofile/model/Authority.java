package com.csi.institutionsprofile.model;

/**
 * Created by Jahid on 10/29/17.
 */

public class Authority {
    private int id;
    private  String name;
    private  String designation;
    private  String mobile;
    private  String email;
    private  String image;

    public Authority(int id, String name, String designation, String mobile, String email, String image){
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.mobile = mobile;
        this.email = email;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
