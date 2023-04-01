package com.App.QCM.Service;

import com.App.QCM.Model.Passage;
import com.App.QCM.Model.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface PassageService {

    ResponseEntity<String> deletePassage(Integer id);

    ResponseEntity<String> updatePassage(Map<String, String> requestMap);

    ResponseEntity<Passage> getPassageById(Integer passageId);

    ResponseEntity<List<Passage>> getAllPassage();

    ResponseEntity<String> addTestToPassage(Integer passageId, Test testRequest);

    ResponseEntity<String> add(Map<String, String> requestMap);

    ResponseEntity<String> updateStatus(Map<String, String> requestMap);
}
