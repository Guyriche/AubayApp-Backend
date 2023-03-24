package com.App.QCM.Service;

import com.App.QCM.Model.Theme;
import com.App.QCM.Wrapper.ThemeWrapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ThemeService {

    ResponseEntity<String> addNewTheme(Map<String, String> requestMap);

    ResponseEntity<List<ThemeWrapper>> getAllTheme();

    ResponseEntity<String> updateTheme(Map<String, String> requestMap);

    ResponseEntity<String> deleteTheme(Integer id);

    ResponseEntity<Theme> getThemeById(Integer themeId);
}
