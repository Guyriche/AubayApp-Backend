package com.App.QCM.Wrapper;


public class ThemeWrapper {

    private Integer id;

    private String wording;

    private String description;

    /*Constructors*/
    public ThemeWrapper() {

    }

    public ThemeWrapper(Integer id, String wording, String description) {
        this.id = id;
        this.wording = wording;
        this.description = description;
    }

    /*Getters and Setters*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWording() {
        return wording;
    }

    public void setWording(String wording) {
        this.wording = wording;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
