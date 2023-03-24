package com.App.QCM.Rest;

import com.App.QCM.Model.Theme;
import com.App.QCM.Wrapper.ThemeWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/theme")
public interface ThemeRest {

    @PostMapping(path = "/add")
    ResponseEntity<String> addNewTheme(@RequestBody(required = true)
                                               Map<String, String> requestMap);

    @GetMapping(path = "/get")
    ResponseEntity<List<ThemeWrapper>> getAllTheme();

    @GetMapping(path = "/get/{id}")
    ResponseEntity<Theme> getThemeById(@PathVariable(value = "id") Integer themeId);

    @PostMapping(path = "/update")
    ResponseEntity<String> updateTheme(@RequestBody(required = true)
                                               Map<String, String> requestMap);

    @PostMapping(path = "/delete/{id}")
    ResponseEntity<String> deleteTheme(@PathVariable(value = "id") Integer id);
}
