package com.App.QCM.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NamedQuery(name = "Question.getAllQuestion", query = "select q from Question q")

@NamedQuery(name = "Question.getQuestionByTheme", query = "select new com.App.QCM.Wrapper.QuestionWrapper(q.id, q.num_question, q.content_question, q.difficulty, q.theme.id, q.theme.wording) from Question q where q.theme.id=:id")

@NamedQuery(name = "Question.findThemeByQuestionId", query = "select q.theme.id from Question q where q.id=:id")

@NamedQuery(name = "Question.getAllQuestionByQcmId", query = "select new com.App.QCM.Wrapper.QuestionWrapper(q.id, q.num_question, q.content_question, q.difficulty, q.theme.id, q.theme.wording, q.qcm.id, q.qcm.title_qcm) from Question q where q.qcm.id=:id")

@NamedQuery(name = "Question.getAllQuestionWrapper", query = "select new com.App.QCM.Wrapper.QuestionWrapper(p.id, p.num_question, p.content_question, p.difficulty, p.theme.id, p.theme.wording) from Question p")

//@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "questions")
public class Question implements Serializable {

    private static final long serialVersionID = 123456L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "num_question")
    private Integer num_question;

    @Column(name = "content_question")
    private String content_question;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "question_proposition",
            joinColumns = {
                    @JoinColumn(name = "question_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "proposition_id", referencedColumnName = "id")
            }
    )
    private Set<Proposition> propositions = new HashSet<Proposition>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theme_fk", nullable = false)
    @JsonIgnore
    private Theme theme;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qcm_fk")
    @JsonIgnore
    private Qcm qcm;

    public Question() {
        super();
    }

    public Question(Integer id, Integer num_question, String content_question,
                    Difficulty difficulty, Set<Proposition> propositions, Theme theme, Qcm qcm) {
        this.id = id;
        this.num_question = num_question;
        this.content_question = content_question;
        this.difficulty = difficulty;
        this.propositions = propositions;
        this.theme = theme;
        this.qcm = qcm;
    }

    public Question(Integer num_question, String content_question, Difficulty difficulty, Set<Proposition> propositions, Theme theme, Qcm qcm) {
        this.num_question = num_question;
        this.content_question = content_question;
        this.difficulty = difficulty;
        this.propositions = propositions;
        this.theme = theme;
        this.qcm = qcm;
    }

    public Question(Integer id, Integer num_question, String content_question, Difficulty difficulty) {
        this.id = id;
        this.num_question = num_question;
        this.content_question = content_question;
        this.difficulty = difficulty;
    }

    public Qcm getQcm() {
        return qcm;
    }

    public void setQcm(Qcm qcm) {
        this.qcm = qcm;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public Question(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumQuestion() {
        return num_question;
    }

    public void setNumQuestion(Integer numQuestion) {
        this.num_question = numQuestion;
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

    public Set<Proposition> getPropositions() {
        return propositions;
    }

    public void setPropositions(Set<Proposition> propositions) {
        this.propositions = propositions;
    }

    public void addProposition(Proposition proposition) {
        this.propositions.add(proposition);
        proposition.getQuestions().add(this);
    }

    public void removeProposition(long propositionId) {
        Proposition proposition = this.propositions.stream().filter(t -> t.getId() == propositionId).findFirst().orElse(null);
        if (proposition != null) {
            this.propositions.remove(proposition);
            proposition.getQuestions().remove(this);
        }
    }
}
