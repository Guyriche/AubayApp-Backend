package com.App.QCM.Wrapper;

import com.App.QCM.Model.Level;

public class TestWrapper {

    private Integer id;

    private String title_test;

    private String description_test;

    private Level level;

    private Integer themeId;

    private String theme_wording;

    /* Constructors */
    public TestWrapper() {

    }

    public TestWrapper(Integer id, String title_test, String description_test,
                       Level level, Integer themeId, String theme_wording) {
        this.id = id;
        this.title_test = title_test;
        this.description_test = description_test;
        this.level = level;
        this.themeId = themeId;
        this.theme_wording = theme_wording;
    }

    /* Getters and Setters */
}
