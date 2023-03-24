package com.App.QCM.RestImpl;

import com.App.QCM.Model.Theme;
import com.App.QCM.Rest.ThemeRest;
import com.App.QCM.Service.ThemeService;
import com.App.QCM.Utils.QcmUtils;
import com.App.QCM.Wrapper.ThemeWrapper;
import com.App.QCM.constents.QcmConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ThemeRestImpl implements ThemeRest {

    @Autowired
    ThemeService themeService;

    @Override
    public ResponseEntity<String> addNewTheme(Map<String, String> requestMap) {
        try {
            return themeService.addNewTheme(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return QcmUtils.getResponseEntity(QcmConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<ThemeWrapper>> getAllTheme() {
        try {
            return themeService.getAllTheme();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Theme> getThemeById(Integer themeId) {
        try {
            return themeService.getThemeById(themeId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateTheme(Map<String, String> requestMap) {
        try {
            return themeService.updateTheme(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return QcmUtils.getResponseEntity(QcmConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteTheme(Integer id) {
        try {
            System.out.print("test");
            return themeService.deleteTheme(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.print("test2");
        return QcmUtils.getResponseEntity(QcmConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
