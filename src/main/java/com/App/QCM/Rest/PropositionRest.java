package com.App.QCM.Rest;

import com.App.QCM.Model.Proposition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/proposition")
public interface PropositionRest {

    @PostMapping(path = "/question/{questionId}/add")
    public ResponseEntity<String> addNewProposition(@PathVariable(value = "questionId") Integer questionId,
                                                    @RequestBody(required = true) Map<String, String> propositionRequest);

    @GetMapping(path = "/get")
    public ResponseEntity<List<Proposition>> getAllProposition(@RequestParam(required = false)
                                                                       String filterValue);

    @GetMapping(path = "/get/{id}")
    public ResponseEntity<Proposition> getPropositionById(@PathVariable(value = "id")
                                                                  Integer propositionId);

    @GetMapping(path = "/question/{questionId}/get")
    public ResponseEntity<List<Proposition>> getAllPropositionByQuestionsId(@PathVariable(value = "questionId")
                                                                                    Integer questionId);

    @PostMapping(path = "/update")
    public ResponseEntity<String> updateProposition(@RequestBody(required = true)
                                                            Map<String, String> requestMap);

    @PostMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteProposition(@PathVariable(value = "id") Integer id);

    @DeleteMapping(path = "/question/{questionId}/delete/{propositionId}")
    public ResponseEntity<String> deletePropositionFromQuestion(@PathVariable(value = "questionId") Integer questionId,
                                                                @PathVariable(value = "propositionId")
                                                                        Integer propositionId);
}
