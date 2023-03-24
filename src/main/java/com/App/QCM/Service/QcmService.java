package com.App.QCM.Service;

import com.App.QCM.Model.Qcm;
import com.App.QCM.Model.Question;
import com.App.QCM.Wrapper.QcmWrapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface QcmService {

    ResponseEntity<String> addNewQcm(Map<String, String> requestMAp);

    ResponseEntity<String> addQuestionIntoQcm(Integer qcmId, Question questionRequest);

    ResponseEntity<List<Qcm>> getAllQcm();

    ResponseEntity<Qcm> getQcmById(Integer qcmId);

    ResponseEntity<String> updateQcm(Map<String, String> requestMap);

    ResponseEntity<String> deleteQcm(Integer id);

    ResponseEntity<List<QcmWrapper>> getAllQcmByTestId(Integer testId);
}
