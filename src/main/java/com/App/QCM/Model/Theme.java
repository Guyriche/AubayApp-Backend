package com.App.QCM.Model;


import jakarta.persistence.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

@NamedQuery(name = "Theme.getAllTheme", query = "select t from Theme t")

//@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "theme")
public class Theme implements Serializable {

    private static final long serialVersionID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "wording")
    private String wording;

    @Column(name = "description")
    private String description;

    public Theme() {

    }

    public Theme(Integer _id) {
        this.id = _id;
    }

    public Theme(Integer _id, String _wording, String _description) {
        this.id = _id;
        this.wording = _wording;
        this.description = _description;
    }

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
