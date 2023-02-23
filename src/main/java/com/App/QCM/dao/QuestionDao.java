package com.App.QCM.dao;

import com.App.QCM.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionDao extends JpaRepository<Question, Integer> {

    List<Question> getAllQuestion();

    List<Question> findQuestionByPropositionsId(long propositionId);
}
