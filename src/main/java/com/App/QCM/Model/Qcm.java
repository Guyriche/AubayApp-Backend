package com.App.QCM.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.util.List;

@NamedQuery(name = "Qcm.getAllQcm", query = "select q from Qcm q")

@NamedQuery(name = "Qcm.getAllQcmByTestId", query = "select new com.App.QCM.Wrapper.QcmWrapper(q.id, q.title_qcm, q.description_qcm, q.difficulty_qcm, q.num_qcm,q.duration_qcm, q.test.id, q.test.title_test) from Qcm q where q.test.id=:id")


//@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "qcm")
public class Qcm implements Serializable {

    private static final long serialVersionID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title_qcm")
    private String title_qcm;

    @Column(name = "description_qcm")
    private String description_qcm;

    @Column(name = "difficulty_qcm")
    private Difficulty difficulty_qcm;

    @Column(name = "num_qcm")
    private Integer num_qcm;

    @Column(name = "duration_qcm")
    private Integer duration_qcm;

    @OneToMany(mappedBy = "qcm", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Question> questions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_fk")
    @JsonIgnore
    private Test test;

    public Qcm(Integer id, String title_qcm, String description_qcm, Difficulty difficulty_qcm,
               Test _test, Integer num_qcm, Integer duration_qcm, List<Question> questions) {
        this.id = id;
        this.title_qcm = title_qcm;
        this.description_qcm = description_qcm;
        this.difficulty_qcm = difficulty_qcm;
        this.test = _test;
        this.num_qcm = num_qcm;
        this.duration_qcm = duration_qcm;
        this.questions = questions;
    }

    public Qcm(String title_qcm, String description_qcm, Difficulty difficulty_qcm, Integer num_qcm, Integer duration_qcm, Test test) {
        this.title_qcm = title_qcm;
        this.description_qcm = description_qcm;
        this.difficulty_qcm = difficulty_qcm;
        this.num_qcm = num_qcm;
        this.duration_qcm = duration_qcm;
        this.test = test;
    }

    public Qcm() {
    }

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

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
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

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
