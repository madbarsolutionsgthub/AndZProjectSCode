package com.csi.institutionsprofile.model;

/**
 * Created by Jahid on 11/13/17.
 */

public class SugestGetSet {
    String id;


    String name;

    public SugestGetSet(String id, String name){
        this.id = id;
        this.name = name;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
