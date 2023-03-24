package com.App.QCM.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.util.List;

@NamedQuery(name = "Proposition.getAllProposition", query = "select p from Proposition p")
//@NamedQuery(name = "Proposition.getAllQuestionsByPropositionId", query = "select new com.App.QCM.Wrapper.PropositionWrapper() from Proposition p where p.id =: id")

//@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "propositions")
public class Proposition implements Serializable {

    private static final long serialVersionUid = 123456L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "num_proposition")
    private Integer num_proposition;

    @Column(name = "content_proposition")
    private String content_proposition;

    @Column(name = "status_proposition")
    private Boolean status_proposition;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "propositions"
            , cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JsonIgnore
    private List<Question> questions;
    //private Question question;

    public Proposition() {
        super();
    }

    public Proposition(Integer id) {
        this.id = id;
    }

    public Proposition(Integer id, Integer num_proposition, String content_proposition, Boolean status_proposition) {
        this.id = id;
        this.num_proposition = num_proposition;
        this.content_proposition = content_proposition;
        this.status_proposition = status_proposition;
    }

    public Proposition(Integer num_proposition, String content_proposition, Boolean status_proposition) {
        this.num_proposition = num_proposition;
        this.content_proposition = content_proposition;
        this.status_proposition = status_proposition;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNum_proposition() {
        return num_proposition;
    }

    public void setNum_proposition(Integer num_proposition) {
        this.num_proposition = num_proposition;
    }

    public String getContent_proposition() {
        return content_proposition;
    }

    public void setContent_proposition(String content_proposition) {
        this.content_proposition = content_proposition;
    }

    public Boolean getStatus_proposition() {
        return status_proposition;
    }

    public void setStatus_proposition(Boolean status_proposition) {
        this.status_proposition = status_proposition;
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
        question.getPropositions().add(this);
    }

    public void removeQuestion(Integer questionId) {

        Question question = this.questions.stream().filter(t -> t.getId() == questionId).findFirst().orElse(null);
        if (question != null) {
            this.questions.remove(question);
            question.getPropositions().remove(this);
        }
    }
}
