package com.App.QCM.Rest;

import com.App.QCM.Model.Passage;
import com.App.QCM.Model.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/passage")
public interface PassageRest {

    @PostMapping(path = "/add")
    public ResponseEntity<String> add(@RequestBody(required = true)
                                              Map<String, String> requestMap);

    @PostMapping(path = "/test/{id}/add")
    public ResponseEntity<String> addTestToPassage(@PathVariable(value = "id") Integer passageId,
                                                   @RequestBody(required = true) Test testRequest);

    @GetMapping(path = "/get")
    ResponseEntity<List<Passage>> getAllPassage();

    @GetMapping(path = "/get/{id}")
    ResponseEntity<Passage> getPassageById(@PathVariable(value = "id") Integer passageId);

    @PostMapping(path = "/update")
    ResponseEntity<String> updatePassage(@RequestBody(required = true)
                                                 Map<String, String> requestMap);

    @PostMapping(path = "/updateStatus")
    ResponseEntity<String> updateStatus(@RequestBody(required = true) Map<String, String> requestMap);

    @PostMapping(path = "/delete/{id}")
    ResponseEntity<String> deletePassage(@PathVariable(value = "id") Integer id);
}
