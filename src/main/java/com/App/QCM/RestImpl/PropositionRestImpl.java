package com.App.QCM.RestImpl;

import com.App.QCM.Model.Proposition;
import com.App.QCM.Rest.PropositionRest;
import com.App.QCM.Service.PropositionService;
import com.App.QCM.Utils.QcmUtils;
import com.App.QCM.constents.QcmConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class PropositionRestImpl implements PropositionRest {

    @Autowired
    PropositionService propositionService;

    @Override
    public ResponseEntity<String> addProposition(Map<String, String> propositionRequest) {
        try {
            return propositionService.addProposition(propositionRequest);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return QcmUtils.getResponseEntity(QcmConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> addNewProposition(Integer questionId, Map<String, String> propositionRequestMap) {
        try {
            return propositionService.addNewProposition(questionId, propositionRequestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return QcmUtils.getResponseEntity(QcmConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Proposition>> getAllProposition(String filterValue) {
        try {
            return propositionService.getAllProposition(filterValue);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<List<Proposition>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Proposition> getPropositionById(Integer propositionId) {
        try {
            return propositionService.getPropositionById(propositionId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Proposition>> getAllPropositionByQuestionsId(Integer questionId) {
        try {
            return propositionService.getAllPropositionByQuestionsId(questionId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateProposition(Map<String, String> requestMap) {
        try {
            return propositionService.updateProposition(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return QcmUtils.getResponseEntity(QcmConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteProposition(Integer id) {
        try {
            return propositionService.deleteProposition(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return QcmUtils.getResponseEntity(QcmConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deletePropositionFromQuestion(Integer questionId, Integer propositionId) {
        try {
            return propositionService.deletePropositionFromQuestion(questionId, propositionId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return QcmUtils.getResponseEntity(QcmConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
