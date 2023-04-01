package com.App.QCM.Wrapper;

import com.App.QCM.Model.AllLevel;

public class TestWrapper {

    private Integer id;

    private String title_test;

    private String description_test;

    private AllLevel level;

    private Integer themeId;

    private Integer userId;

    private String email;

    private String theme_wording;

    private Integer PassageId;

    private String ref;

    /* Constructors */
    public TestWrapper() {

    }

    public TestWrapper(Integer id, String title_test, String description_test, AllLevel level,
                       Integer userId, String email, Integer themeId, String theme_wording) {
        this.id = id;
        this.title_test = title_test;
        this.description_test = description_test;
        this.level = level;
        this.themeId = themeId;
        this.userId = userId;
        this.email = email;
        this.theme_wording = theme_wording;
    }

    public TestWrapper(Integer id, String title_test, String description_test,
                       AllLevel level, Integer themeId, String theme_wording) {
        this.id = id;
        this.title_test = title_test;
        this.description_test = description_test;
        this.level = level;
        this.themeId = themeId;
        this.theme_wording = theme_wording;
    }

    public TestWrapper(Integer id, String title_test, String description_test, AllLevel level,
                       Integer userId, String email, Integer themeId, String theme_wording, Integer passageId, String ref) {
        this.id = id;
        this.title_test = title_test;
        this.description_test = description_test;
        this.level = level;
        this.themeId = themeId;
        this.userId = userId;
        this.email = email;
        this.theme_wording = theme_wording;
        PassageId = passageId;
        this.ref = ref;
    }

    /* Getters and Setters */

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

    public Integer getThemeId() {
        return themeId;
    }

    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTheme_wording() {
        return theme_wording;
    }

    public void setTheme_wording(String theme_wording) {
        this.theme_wording = theme_wording;
    }

    public Integer getPassageId() {
        return PassageId;
    }

    public void setPassageId(Integer passageId) {
        PassageId = passageId;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }
}
