package com.App.QCM.Wrapper;
import com.App.QCM.Model.Difficulty;


public class QuestionWrapper {

    private Integer id;

    private Integer numQuestion;

    private String content_question;

    private Difficulty difficulty;

    private Integer themeId;

    private String theme_Wording;

    private Integer qcmId;

    private String title_qcm;


    /*Constructors*/

    public QuestionWrapper() {

    }

    public QuestionWrapper(Integer id, Integer num_question, String content_question, Difficulty difficulty,
                           Integer themeId, String theme_Wording/*, Integer qcmId, String description_qcm*/) {
        this.id = id;
        this.numQuestion = num_question;
        this.content_question = content_question;
        this.difficulty = difficulty;
        this.themeId = themeId;
        this.theme_Wording = theme_Wording;
        /*this.qcmId = qcmId;
        this.description_qcm = description_qcm;*/
    }

    public QuestionWrapper(Integer id, Integer num_question, String content_question, Difficulty difficulty,
                           Integer themeId, String theme_Wording, Integer qcmId, String title_qcm) {
        this.id = id;
        this.numQuestion = num_question;
        this.content_question = content_question;
        this.difficulty = difficulty;
        this.themeId = themeId;
        this.theme_Wording = theme_Wording;
        this.qcmId = qcmId;
        this.title_qcm = title_qcm;
    }

    /* Getters and Setters*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNum_question() {
        return numQuestion;
    }

    public void setNum_question(Integer num_question) {
        this.numQuestion = num_question;
    }

    public String getContent_question() {
        return content_question;
    }

    public void setContent_question(String content_question) {
        this.content_question = content_question;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getThemeId() {
        return themeId;
    }

    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
    }

    public String getTheme_Wording() {
        return theme_Wording;
    }

    public void setTheme_Wording(String theme_Wording) {
        this.theme_Wording = theme_Wording;
    }

    public Integer getQcmId() {
        return qcmId;
    }

    public void setQcmId(Integer qcmId) {
        this.qcmId = qcmId;
    }

    public String getDescription_qcm() {
        return title_qcm;
    }

    public void setDescription_qcm(String title_qcm) {
        this.title_qcm = title_qcm;
    }
}
