package com.App.QCM.Rest;


import com.App.QCM.Model.Proposition;
import com.App.QCM.Model.Question;
import com.App.QCM.Wrapper.QuestionWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/question")
public interface QuestionRest {

    @PostMapping(path = "/add")
    ResponseEntity<String> addNewQuestion(@RequestBody(required = true)
                                                  Map<String, String> requestMap);

    @PostMapping(path = "/{id}/proposition/add")
    public ResponseEntity<String> addPropositionToQuestion(@PathVariable(value = "id") Integer questionId,
                                                           @RequestBody(required = true) Proposition propositionRequest);

    @GetMapping(path = "/get")
    ResponseEntity<List<Question>> getAllQuestion();

    @GetMapping(path = "/getQ")
    ResponseEntity<List<QuestionWrapper>> getAllQuestionWrapper();

    @GetMapping(path = "/get/{id}")
    ResponseEntity<Question> getQuestionById(@PathVariable(value = "id") Integer questionId);

    @GetMapping(path = "/getQuestionByThemeId/{id}")
    ResponseEntity<List<QuestionWrapper>> getQuestionByThemeId(@PathVariable(value = "id") Integer themeId);

    @GetMapping(path = "/getQuestionsByQcmId/{id}")
    ResponseEntity<List<QuestionWrapper>> getAllQuestionByQcmId(@PathVariable(value = "id") Integer qcmId);

    @PostMapping(path = "/update")
    ResponseEntity<String> updateQuestion(@RequestBody(required = true)
                                                  Map<String, String> requestMap);

    @PostMapping(path = "/delete/{id}")
    ResponseEntity<String> deleteQuestion(@PathVariable(value = "id") Integer id);


}
