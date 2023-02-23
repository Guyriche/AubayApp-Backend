package com.App.QCM.Rest;


import com.App.QCM.Model.Question;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/question")
public interface QuestionRest {

    @PostMapping(path = "/add")
    ResponseEntity<String> addNewQuestion(@RequestBody(required = true)
                                                  Map<String, String> requestMap);

    @GetMapping(path = "/get")
    ResponseEntity<List<Question>> getAllQuestion(@RequestParam(required = false)
                                                          String filterValue);

    @PostMapping(path = "/update")
    ResponseEntity<String> updateQuestion(@RequestBody(required = true)
                                                  Map<String, String> requestMap);

    @PostMapping(path = "/delete")
    ResponseEntity<String> deleteQuestion(@PathVariable Integer id);

    @GetMapping(path = "/get/{id}")
    ResponseEntity<Question> getQuestionById(@PathVariable(value = "id") Integer questionId);

}
