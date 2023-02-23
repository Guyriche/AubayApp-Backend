package com.App.QCM.Service;

import com.App.QCM.Model.Question;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface QuestionService {

    ResponseEntity<String> addNewQuestion(Map<String, String> requestMAp);

    ResponseEntity<List<Question>> getAllQuestion(String filterValue);

    ResponseEntity<String> updateQuestion(Map<String, String> requestMap);

    ResponseEntity<String> deleteQuestion(Integer id);

    ResponseEntity<Question> getQuestionById(Integer questionId);
}
