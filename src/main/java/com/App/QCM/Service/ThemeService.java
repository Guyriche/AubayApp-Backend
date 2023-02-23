package com.App.QCM.Service;

import com.App.QCM.Model.Theme;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ThemeService {

    ResponseEntity<String> addNewTheme(Map<String, String> requestMap);

    ResponseEntity<List<Theme>> getAllTheme(String filterValue);

    ResponseEntity<String> updateTheme(Map<String, String> requestMap);

    ResponseEntity<String> deleteTheme(Integer id);
}
