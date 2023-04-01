package com.App.QCM.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@NamedQuery(name = "Passage.getAllPassage", query = "select p from Passage p")

@NamedQuery(name = "Passage.updateStatus", query = "update Passage p set p.status_passage=:status_passage where p.id=:id")

@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "passage")
public class Passage implements Serializable {

    private static final long serialVersionID = 123456L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "ref")
    private String ref;

    @Column(name = "date_creation")
    private Date date_creation;

    @Column(name = "date_expiration")
    private Date date_expiration;

    @Column(name = "score")
    private Integer score;

    @Column(name = "candidate")
    private String candidate;

    @Column(name = "status_passage")
    private String status_passage;

    @OneToMany(mappedBy = "passage", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Test> tests;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_fk")
    @JsonIgnore
    private User user;

    /* Constructors */

    public Passage() {
    }

    public Passage(Integer id, String ref, Date date_creation, Date date_expiration, Integer score,
                   String candidate, String status_passage, List<Test> tests, User user) {
        this.id = id;
        this.ref = ref;
        this.date_creation = date_creation;
        this.date_expiration = date_expiration;
        this.score = score;
        this.candidate = candidate;
        this.status_passage = status_passage;
        this.tests = tests;
        this.user = user;
    }

    /* Getters and Setters */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public Date getDate_expiration() {
        return date_expiration;
    }

    public void setDate_expiration(Date date_expiration) {
        this.date_expiration = date_expiration;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getCandidate() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }

    public String getStatus_passage() {
        return status_passage;
    }

    public void setStatus_passage(String status_passage) {
        this.status_passage = status_passage;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
