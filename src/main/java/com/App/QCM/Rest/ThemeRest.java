package com.App.QCM.Rest;

import com.App.QCM.Model.Theme;
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
    ResponseEntity<List<Theme>> getAllTheme(@RequestParam(required = false)
                                                    String filterValue);

    @PostMapping(path = "/update")
    ResponseEntity<String> updateTheme(@RequestBody(required = true)
                                               Map<String, String> requestMap);

    @PostMapping(path = "/delete")
    ResponseEntity<String> deleteTheme(@PathVariable Integer id);
}
