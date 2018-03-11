package com.manuvastra;

/**
 * Created by sriram on 19/06/17.
 */

/**
 * Department class denotes a department in EasyShopping app data
 */
public class Department {

    private String id;
    private String title;

    public Department(){

    }

    public Department(String id, String title){
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
