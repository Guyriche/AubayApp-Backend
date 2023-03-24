package com.App.QCM.Service;

import com.App.QCM.Model.Proposition;
import com.App.QCM.Model.Question;
import com.App.QCM.Wrapper.QuestionWrapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface QuestionService {

    ResponseEntity<String> addNewQuestion(Map<String, String> requestMAp);

    ResponseEntity<List<Question>> getAllQuestion();

    ResponseEntity<String> updateQuestion(Map<String, String> requestMap);

    ResponseEntity<String> deleteQuestion(Integer id);

    ResponseEntity<Question> getQuestionById(Integer questionId);

    ResponseEntity<List<QuestionWrapper>> getQuestionByThemeId(Integer themeId);

    ResponseEntity<List<QuestionWrapper>> getAllQuestionByQcmId(Integer qcmId);

    ResponseEntity<List<QuestionWrapper>> getAllQuestionWrapper();

    ResponseEntity<String> addPropositionToQuestion(Integer questionId, Proposition propositionRequest);
}
