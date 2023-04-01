package com.App.QCM.RestImpl;

import com.App.QCM.Model.Qcm;
import com.App.QCM.Model.Test;
import com.App.QCM.Rest.TestRest;
import com.App.QCM.Service.TestService;
import com.App.QCM.Utils.QcmUtils;
import com.App.QCM.Wrapper.TestWrapper;
import com.App.QCM.constents.QcmConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class TestRestImpl implements TestRest {

    @Autowired
    TestService testService;

    @Override
    public ResponseEntity<String> add(Map<String, String> requestMap) {
        try {
            return testService.add(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return QcmUtils.getResponseEntity(QcmConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> addQcmToTest(Integer testId, Qcm qcmRequest) {
        try {
            return testService.addQcmToTest(testId, qcmRequest);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Test>> getAllTest() {
        try {
            return testService.getAllTest();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Test> getTestById(Integer testId) {
        try {
            return testService.getTestById(testId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<TestWrapper>> getTestByThemeId(Integer themeId) {
        try {
            return testService.getTestByThemeId(themeId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<TestWrapper>> getAllTestByPassageId(Integer PassageId) {
        try {
            return testService.getAllTestByPassageId(PassageId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateTest(Map<String, String> requestMap) {
        try {
            return testService.updateTest(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return QcmUtils.getResponseEntity(QcmConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteTest(Integer id) {
        try {
            return testService.deleteTest(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return QcmUtils.getResponseEntity(QcmConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteQcmInTest(Integer testId, Integer id) {
        try {

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return QcmUtils.getResponseEntity(QcmConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
