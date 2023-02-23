package com.App.QCM.ServiceImpl;

import com.App.QCM.JWT.JwtFilter;
import com.App.QCM.Model.Theme;
import com.App.QCM.Service.ThemeService;
import com.App.QCM.Utils.QcmUtils;
import com.App.QCM.constents.QcmConstants;
import com.App.QCM.dao.ThemeDao;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ThemeServiceImpl implements ThemeService {

    @Autowired
    ThemeDao themeDao;

    @Autowired
    JwtFilter jwtFilter;

    @Override
    public ResponseEntity<String> addNewTheme(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                if (validateThemeMap(requestMap, false)) {
                    themeDao.save(getThemeFromMap(requestMap, false));
                    return QcmUtils.getResponseEntity("Theme Added Successfully..", HttpStatus.OK);
                }
            } else {
                return QcmUtils.getResponseEntity(QcmConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return QcmUtils.getResponseEntity(QcmConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateThemeMap(Map<String, String> requestMap, boolean b) {
        if (requestMap.containsKey("wording") && requestMap.containsKey("description")) {
            if (requestMap.containsKey("id") && b) {
                return true;
            } else if (!b) {
                return true;
            }
        }
        return false;
    }

    private Theme getThemeFromMap(Map<String, String> requestMap, Boolean isAdd) {
        Theme theme = new Theme();
        if (isAdd) {
            theme.setId(Integer.parseInt(requestMap.get("id")));
        }
        theme.setWording(requestMap.get("wording"));
        theme.setDescription(requestMap.get("description"));

        return theme;
    }


    @Override
    public ResponseEntity<List<Theme>> getAllTheme(String filterValue) {
        try {
            if (!Strings.isNullOrEmpty(filterValue) && filterValue.equalsIgnoreCase("true")) {
                return new ResponseEntity<List<Theme>>(themeDao.getAllTheme(), HttpStatus.OK);
            }
            return new ResponseEntity<>(themeDao.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<List<Theme>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateTheme(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                if (validateThemeMap(requestMap, true)) {
                    Optional optional = themeDao.findById(Integer.parseInt(requestMap.get("id")));
                    if (!optional.isEmpty()) {
                        themeDao.save(getThemeFromMap(requestMap, true));
                        return QcmUtils.getResponseEntity("Theme Updated Successfully..", HttpStatus.OK);
                    } else {
                        return QcmUtils.getResponseEntity("Theme id does not exist", HttpStatus.OK);
                    }
                }
                return QcmUtils.getResponseEntity(QcmConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            } else {
                return QcmUtils.getResponseEntity(QcmConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return QcmUtils.getResponseEntity(QcmConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteTheme(Integer id) {
        try {
            if (jwtFilter.isAdmin()) {
                Optional optional = themeDao.findById(id);
                if (optional.isEmpty()) {
                    themeDao.deleteById(id);
                    return QcmUtils.getResponseEntity("Theme Deleted Successfully..", HttpStatus.OK);
                }
                return QcmUtils.getResponseEntity("Theme id does not exist..", HttpStatus.OK);
            } else {
                return QcmUtils.getResponseEntity(QcmConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return QcmUtils.getResponseEntity(QcmConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
