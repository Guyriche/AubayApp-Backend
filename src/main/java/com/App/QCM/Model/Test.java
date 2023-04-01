package com.App.QCM.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.util.List;

@NamedQuery(name = "Test.getAllTest", query = "select t from Test t")

@NamedQuery(name = "Test.getTestByThemeId", query = "select new com.App.QCM.Wrapper.TestWrapper(t.id, t.title_test, t.description_test, t.level, t.user.id, t.user.email, t.theme.id, t.theme.wording) from Test t where t.theme.id=:id")

@NamedQuery(name = "Test.getAllTestByPassageId", query = "select new com.App.QCM.Wrapper.TestWrapper(t.id, t.title_test, t.description_test, t.level, t.user.id, t.user.email, t.theme.id, t.theme.wording, t.passage.id, t.passage.ref) from Test t where t.passage.id=:id")

@NamedQuery(name = "Test.findThemeByTestId", query = "select t.theme.id from Test t where t.id=:id")
//@NamedQuery(name = "Test.getAllTest", query = "select new com.App.QCM.Wrapper.TestWrapper(t.id, t.title_test, t.description_test, t.level, t.theme.id, t.theme.wording) from Test t")

//@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "test")
public class Test implements Serializable {

    private static final long serialVersionID = 123456L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title_test")
    private String title_test;

    @Column(name = "description_test")
    private String description_test;

    @Enumerated(EnumType.STRING)
    private AllLevel level;

    @OneToMany(mappedBy = "test", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Qcm> qcms;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theme_fk")
    @JsonIgnore
    private Theme theme;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_fk")
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passage_fk")
    @JsonIgnore
    private Passage passage;

    /*Constructors*/

    public Test() {

    }

    public Test(Integer id, String title_test, String description_test, AllLevel level, List<Qcm> qcms,
                Theme theme) {
        this.id = id;
        this.title_test = title_test;
        this.description_test = description_test;
        this.level = level;
        this.qcms = qcms;
        this.theme = theme;
    }

    public Test(Integer id, String title_test, String description_test, AllLevel level, List<Qcm> qcms,
                Theme theme, User user) {
        this.id = id;
        this.title_test = title_test;
        this.description_test = description_test;
        this.level = level;
        this.qcms = qcms;
        this.theme = theme;
        this.user = user;
    }

    public Test(Integer id, String title_test, String description_test, AllLevel level, List<Qcm> qcms,
                Theme theme, User user, Passage passage) {
        this.id = id;
        this.title_test = title_test;
        this.description_test = description_test;
        this.level = level;
        this.qcms = qcms;
        this.theme = theme;
        this.user = user;
        this.passage = passage;
    }

    public Test(String title_test, String description_test, AllLevel level, Theme theme, User user, Passage passage) {
        this.title_test = title_test;
        this.description_test = description_test;
        this.level = level;
        this.theme = theme;
        this.user = user;
        this.passage = passage;
    }

    /*Getters and Setters*/

    public Passage getPassage() {
        return passage;
    }

    public void setPassage(Passage passage) {
        this.passage = passage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle_test() {
        return title_test;
    }

    public void setTitle_test(String title_test) {
        this.title_test = title_test;
    }

    public String getDescription_test() {
        return description_test;
    }

    public void setDescription_test(String description_test) {
        this.description_test = description_test;
    }

    public AllLevel getLevel() {
        return level;
    }

    public void setLevel(AllLevel level) {
        this.level = level;
    }

    public List<Qcm> getQcms() {
        return qcms;
    }

    public void setQcms(List<Qcm> qcms) {
        this.qcms = qcms;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
