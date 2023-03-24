package com.App.QCM.Rest;

import com.App.QCM.Model.Qcm;
import com.App.QCM.Model.Question;
import com.App.QCM.Wrapper.QcmWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/qcm")
public interface QcmRest {

    @PostMapping(path = "/add")
    public ResponseEntity<String> addNewQcm(@RequestBody(required = true)
                                                    Map<String, String> requestMap);

    @PostMapping(path = "/question/{id}/add")
    public ResponseEntity<String> addQuestionIntoQcm(@PathVariable(value = "id") Integer qcmId,
                                                     @RequestBody(required = true) Question questionRequest);

    @GetMapping(path = "/get")
    ResponseEntity<List<Qcm>> getAllQcm();

    @GetMapping(path = "/get/{id}")
    ResponseEntity<Qcm> getQcmById(@PathVariable(value = "id") Integer qcmId);

    @GetMapping(path = "/getAllQcmByTestId/{id}")
    ResponseEntity<List<QcmWrapper>> getAllQcmByTestId(@PathVariable(value = "id") Integer testId);

    @PostMapping(path = "/update")
    ResponseEntity<String> updateQcm(@RequestBody(required = true)
                                             Map<String, String> requestMap);

    @PostMapping(path = "/delete/{id}")
    ResponseEntity<String> deleteQcm(@PathVariable(value = "id") Integer id);

    @PostMapping(path = "/delete/{qcmId}/question/{id}")
    ResponseEntity<String> deleteQuestionInQcm(@PathVariable(value = "qcmId") Integer qcmId,
                                               @PathVariable(value = "id") Integer id);

}
