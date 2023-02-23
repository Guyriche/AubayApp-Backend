package com.App.QCM.Wrapper;


import com.App.QCM.Model.Difficulty;
import lombok.NoArgsConstructor;

//@Data
@NoArgsConstructor
public class QuestionWrapper {

    private Integer id;

    private Integer num_question;

    private String content_question;

    private Difficulty difficulty;

    public QuestionWrapper(Integer id, Integer num_question, String content_question, Difficulty difficulty) {
        this.id = id;
        this.num_question = num_question;
        this.content_question = content_question;
        this.difficulty = difficulty;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNum_question() {
        return num_question;
    }

    public void setNum_question(Integer num_question) {
        this.num_question = num_question;
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
}
