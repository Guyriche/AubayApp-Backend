package com.App.QCM.RestImpl;

import com.App.QCM.Model.Qcm;
import com.App.QCM.Model.Question;
import com.App.QCM.Rest.QcmRest;
import com.App.QCM.Service.QcmService;
import com.App.QCM.Utils.QcmUtils;
import com.App.QCM.Wrapper.QcmWrapper;
import com.App.QCM.constents.QcmConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class QcmRestImpl implements QcmRest {

    @Autowired
    QcmService qcmService;

    @Override
    public ResponseEntity<String> addNewQcm(Map<String, String> requestMap) {
        try {
            return qcmService.addNewQcm(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return QcmUtils.getResponseEntity(QcmConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> addQuestionIntoQcm(Integer qcmId, Question questionRequest) {
        try {
            return qcmService.addQuestionIntoQcm(qcmId, questionRequest);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Qcm>> getAllQcm() {
        try {
            return qcmService.getAllQcm();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    public ResponseEntity<Qcm> getQcmById(Integer qcmId) {
        try {
            return qcmService.getQcmById(qcmId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<Qcm>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<QcmWrapper>> getAllQcmByTestId(Integer testId) {
        try {
            return qcmService.getAllQcmByTestId(testId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateQcm(Map<String, String> requestMap) {
        try {
            return qcmService.updateQcm(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return QcmUtils.getResponseEntity(QcmConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteQcm(Integer id) {
        try {
            return qcmService.deleteQcm(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return QcmUtils.getResponseEntity(QcmConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteQuestionInQcm(Integer qcmId, Integer id) {
        try {

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return QcmUtils.getResponseEntity(QcmConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
