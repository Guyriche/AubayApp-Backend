package com.App.QCM.dao;

import com.App.QCM.Model.Question;
import com.App.QCM.Model.Theme;
import com.App.QCM.Wrapper.QuestionWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionDao extends JpaRepository<Question, Integer> {

    List<Question> getAllQuestion();

    List<Question> findQuestionByPropositionsId(Integer propositionId);

    List<QuestionWrapper> getQuestionByTheme(@Param("id") Integer themeId);

    List<QuestionWrapper> getAllQuestionByQcmId(@Param("id") Integer qcmId);

    List<QuestionWrapper> getAllQuestionWrapper();

    Theme findThemeByQuestionId(@Param("id") Integer questionId);
}
