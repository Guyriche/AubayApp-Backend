package com.App.QCM.dao;

import com.App.QCM.Model.Test;
import com.App.QCM.Model.Theme;
import com.App.QCM.Wrapper.TestWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TestDao extends JpaRepository<Test, Integer> {

    List<Test> getAllTest();

    Theme findThemeByTestId(@Param("id") Integer TestId);

    List<TestWrapper> getTestByThemeId(@Param("id") Integer themeId);

    List<TestWrapper> getAllTestByPassageId(@Param("id") Integer passageId);
}
