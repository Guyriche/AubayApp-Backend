package com.App.QCM.Wrapper;

import com.App.QCM.Model.Difficulty;

public class QcmWrapper {

    private Integer id;

    private String title_qcm;

    private String description_qcm;

    private Difficulty difficulty_qcm;

    private Integer num_qcm;

    private Integer duration_qcm;

    private Integer testId;

    private String title_test;

    /*Constructors*/

    public QcmWrapper() {

    }

    public QcmWrapper(Integer id, String title_qcm, String description_qcm, Difficulty difficulty_qcm,
                      Integer num_qcm, Integer duration_qcm, Integer testId, String title_test) {
        this.id = id;
        this.title_qcm = title_qcm;
        this.description_qcm = description_qcm;
        this.difficulty_qcm = difficulty_qcm;
        this.num_qcm = num_qcm;
        this.duration_qcm = duration_qcm;
        this.testId = testId;
        this.title_test = title_test;
    }

    /*Getters and Setters*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle_qcm() {
        return title_qcm;
    }

    public void setTitle_qcm(String title_qcm) {
        this.title_qcm = title_qcm;
    }

    public String getDescription_qcm() {
        return description_qcm;
    }

    public void setDescription_qcm(String description_qcm) {
        this.description_qcm = description_qcm;
    }

    public Difficulty getDifficulty_qcm() {
        return difficulty_qcm;
    }

    public void setDifficulty_qcm(Difficulty difficulty_qcm) {
        this.difficulty_qcm = difficulty_qcm;
    }

    public Integer getNum_qcm() {
        return num_qcm;
    }

    public void setNum_qcm(Integer num_qcm) {
        this.num_qcm = num_qcm;
    }

    public Integer getDuration_qcm() {
        return duration_qcm;
    }

    public void setDuration_qcm(Integer duration_qcm) {
        this.duration_qcm = duration_qcm;
    }
}
