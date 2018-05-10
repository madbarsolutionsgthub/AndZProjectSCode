package com.csi.institutionsprofile.model;

/**
 * Created by Jahid on 11/6/17.
 */

public class Notice {
    private int id;
    private  String title;
    private  String details;
    private  String date;
    private  String pdf;

    public Notice(int id, String title, String details, String pdf,String date){
        this.id = id;
        this.title = title;
        this.details = details;
        this.date = date;
        this.pdf = pdf;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

}
