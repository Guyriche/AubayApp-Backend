package com.App.QCM.Rest;


import com.App.QCM.Model.Qcm;
import com.App.QCM.Model.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/test")
public interface TestRest {

    @PostMapping(path = "/add")
    public ResponseEntity<String> add(@RequestBody(required = true)
                                              Map<String, String> requestMap);

    @PostMapping(path = "/qcm/{id}/add")
    public ResponseEntity<String> addQcmToTest(@PathVariable(value = "id") Integer testId,
                                               @RequestBody(required = true) Qcm qcmRequest);

    @GetMapping(path = "/get")
    ResponseEntity<List<Test>> getAllTest();

    @GetMapping(path = "/get/{id}")
    ResponseEntity<Test> getTestById(@PathVariable(value = "id") Integer testId);

    @PostMapping(path = "/update")
    ResponseEntity<String> updateTest(@RequestBody(required = true)
                                              Map<String, String> requestMap);

    @PostMapping(path = "/delete/{id}")
    ResponseEntity<String> deleteTest(@PathVariable(value = "id") Integer id);

    @PostMapping(path = "/delete/{testId}/qcm/{id}")
    ResponseEntity<String> deleteQcmInTest(@PathVariable(value = "testId") Integer testId,
                                           @PathVariable(value = "id") Integer id);
}
