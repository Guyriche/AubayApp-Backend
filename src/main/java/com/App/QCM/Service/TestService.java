package com.App.QCM.Service;

import com.App.QCM.Model.Qcm;
import com.App.QCM.Model.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface TestService {
    ResponseEntity<String> add(Map<String, String> requestMap);

    ResponseEntity<String> addQcmToTest(Integer testId, Qcm qcmRequest);

    ResponseEntity<List<Test>> getAllTest();

    ResponseEntity<Test> getTestById(Integer testId);

    ResponseEntity<String> updateTest(Map<String, String> requestMap);

    ResponseEntity<String> deleteTest(Integer id);
}
