package com.App.QCM.RestImpl;

import com.App.QCM.Model.Proposition;
import com.App.QCM.Model.Question;
import com.App.QCM.Rest.QuestionRest;
import com.App.QCM.Service.QuestionService;
import com.App.QCM.Utils.QcmUtils;
import com.App.QCM.Wrapper.QuestionWrapper;
import com.App.QCM.constents.QcmConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class QuestionRestImpl implements QuestionRest {

    @Autowired
    QuestionService questionService;

    @Override
    public ResponseEntity<String> addNewQuestion(Map<String, String> requestMap) {
        try {
            return questionService.addNewQuestion(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return QcmUtils.getResponseEntity(QcmConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> addPropositionToQuestion(Integer questionId, Proposition propositionRequest) {
        try {
            return questionService.addPropositionToQuestion(questionId, propositionRequest);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return QcmUtils.getResponseEntity(QcmConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Question>> getAllQuestion() {
        try {
            return questionService.getAllQuestion();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<QuestionWrapper>> getAllQuestionWrapper() {
        try {
            return questionService.getAllQuestionWrapper();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateQuestion(Map<String, String> requestMap) {
        try {
            return questionService.updateQuestion(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return QcmUtils.getResponseEntity(QcmConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteQuestion(Integer id) {
        try {
            return questionService.deleteQuestion(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return QcmUtils.getResponseEntity(QcmConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Question> getQuestionById(Integer questionId) {
        try {
            return questionService.getQuestionById(questionId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<Question>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<QuestionWrapper>> getQuestionByThemeId(Integer themeId) {
        try {
            return questionService.getQuestionByThemeId(themeId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<QuestionWrapper>> getAllQuestionByQcmId(Integer qcmId) {
        try {
            return questionService.getAllQuestionByQcmId(qcmId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
