package com.App.QCM.Model;


import jakarta.persistence.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NamedQuery(name = "Question.getAllQuestion", query = "select q from Question q")

//@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "questions")
public class Question implements Serializable {

    private static final long serialVersionID = 1L;

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

    public Question() {
        super();
    }

    public Question(Integer id, Integer numQuestion, String content_question, Difficulty difficulty) {
        this.id = id;
        this.num_question = numQuestion;
        this.content_question = content_question;
        this.difficulty = difficulty;
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
