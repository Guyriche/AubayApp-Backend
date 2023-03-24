package com.App.QCM.Service;

import com.App.QCM.Model.Proposition;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface PropositionService {

    ResponseEntity<String> addNewProposition(Integer questionId, Map<String, String> propositionRequestMAp);

    ResponseEntity<List<Proposition>> getAllProposition(String filterValue);

    ResponseEntity<String> updateProposition(Map<String, String> requestMap);

    ResponseEntity<String> deleteProposition(Integer id);

    ResponseEntity<Proposition> getPropositionById(Integer propositionId);

    ResponseEntity<List<Proposition>> getAllPropositionByQuestionsId(Integer questionId);

    ResponseEntity<String> deletePropositionFromQuestion(Integer questionId, Integer propositionId);

    ResponseEntity<String> addProposition(Map<String, String> propositionRequest);
}
