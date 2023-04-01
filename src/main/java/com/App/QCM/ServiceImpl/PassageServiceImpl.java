package com.App.QCM.ServiceImpl;

import com.App.QCM.JWT.JwtFilter;
import com.App.QCM.Model.Passage;
import com.App.QCM.Model.Test;
import com.App.QCM.Model.Theme;
import com.App.QCM.Model.User;
import com.App.QCM.Service.PassageService;
import com.App.QCM.Utils.QcmUtils;
import com.App.QCM.constents.QcmConstants;
import com.App.QCM.dao.PassageDao;
import com.App.QCM.dao.TestDao;
import com.App.QCM.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PassageServiceImpl implements PassageService {

    @Autowired
    TestDao testDao;

    @Autowired
    PassageDao passageDao;

    @Autowired
    JwtFilter jwtFilter;

    @Autowired
    UserDao userDao;


    @Override
    public ResponseEntity<String> deletePassage(Integer id) {
        try {
            if (jwtFilter.isAdmin()) {
                Optional<Passage> optPassage = passageDao.findById(id);
                if (!optPassage.isEmpty()) {
                    passageDao.deleteById(id);
                    return QcmUtils.getResponseEntity("Passage Quiz Deleted Successfully", HttpStatus.OK);
                }
                return QcmUtils.getResponseEntity("This Passage Quiz does not exist", HttpStatus.OK);
            } else {
                return QcmUtils.getResponseEntity(QcmConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return QcmUtils.getResponseEntity(QcmConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updatePassage(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                if (validatePassage(requestMap, true)) {
                    Optional<Passage> optPassage = passageDao.findById(Integer.parseInt(requestMap.get("id")));
                    if (optPassage.isPresent()) {
                        passageDao.save(getPassageFromMap(requestMap, true));
                        return QcmUtils.getResponseEntity("Passage Quiz Updated Successfully", HttpStatus.OK);
                    } else {
                        return QcmUtils.getResponseEntity("This Passage Quiz Does not Exist..", HttpStatus.OK);
                    }
                } else {
                    return QcmUtils.getResponseEntity(QcmConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
                }
            } else {
                return QcmUtils.getResponseEntity(QcmConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return QcmUtils.getResponseEntity(QcmConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateStatus(Map<String, String> requestMap) {
        try {
            Optional<Passage> optPassage = passageDao.findById(Integer.parseInt(requestMap.get("id")));
            if (!optPassage.isEmpty()) {
                passageDao.updateStatus(requestMap.get("status"), Integer.parseInt(requestMap.get("id")));
                return QcmUtils.getResponseEntity("Passage Quiz Status Updated Successfully", HttpStatus.OK);
            } else {
                QcmUtils.getResponseEntity("Passage Quiz id doesn't not exist", HttpStatus.OK);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return QcmUtils.getResponseEntity(QcmConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Passage> getPassageById(Integer passageId) {
        try {
            Optional<Passage> optionalPassage = passageDao.findById(passageId);
            if (!optionalPassage.isEmpty()) {
                return new ResponseEntity<>(optionalPassage.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Passage>> getAllPassage() {
        try {
            return new ResponseEntity<>(passageDao.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> addTestToPassage(Integer passageId, Test testRequest) {
        try {
            if (jwtFilter.isAdmin()) {
                Optional<Passage> optionalPassage = passageDao.findById(passageId);
                if (optionalPassage.isEmpty()) {
                    return QcmUtils.getResponseEntity("This Passage Quiz does not Exist", HttpStatus.NOT_FOUND);
                }

                Passage passage = optionalPassage.get();

                User objUser = userDao.findByEmail(jwtFilter.getCurrentUser());
                Theme theme = testDao.findThemeByTestId(testRequest.getId());
                if (testRequest.getId() != null) {
                    Optional<Test> optionalTest = testDao.findById(testRequest.getId());
                    if (!optionalTest.isEmpty()) {
                        testRequest.setPassage(passage);
                        testRequest.setUser(objUser);
                        testRequest.setTheme(theme);
                        testDao.save(testRequest);
                        return QcmUtils.getResponseEntity("This Passage Quiz Added Successfully", HttpStatus.OK);
                    } else {
                        return QcmUtils.getResponseEntity("This Passage Quiz does not exist", HttpStatus.NOT_FOUND);
                    }
                } else {
                    Test resTest = testDao.save(new Test(testRequest.getTitle_test(), testRequest.getDescription_test(),
                            testRequest.getLevel(), testRequest.getTheme(), testRequest.getUser(), passage));
                }
            } else {
                return QcmUtils.getResponseEntity(QcmConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return QcmUtils.getResponseEntity(QcmConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> add(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                if (validatePassage(requestMap, false)) {
                    passageDao.save(getPassageFromMap(requestMap, false));
                    return QcmUtils.getResponseEntity("Passage Quiz Added Successfully..", HttpStatus.OK);
                } else {
                    return QcmUtils.getResponseEntity("Data Isn't Correct..", HttpStatus.BAD_REQUEST);
                }
            } else {
                return QcmUtils.getResponseEntity(QcmConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return QcmUtils.getResponseEntity(QcmConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validatePassage(Map<String, String> requestMap, boolean b) {
        if (requestMap.containsKey("ref")) {
            if (requestMap.containsKey("id") && b) {
                return true;
            } else if (!b) {
                return true;
            }
        }
        return false;
    }

    private Passage getPassageFromMap(Map<String, String> requestMap, boolean isAdd) {
        Passage passage = new Passage();

        User userObj = userDao.findByEmail(jwtFilter.getCurrentUser());
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedString = localDate.format(formatter);
        String formDate = requestMap.get("date_expiration").formatted(formatter);

        if (isAdd) {
            passage.setId(Integer.parseInt(requestMap.get("id")));
        }
        passage.setRef(requestMap.get("ref"));
        passage.setDate_creation(java.sql.Date.valueOf(formattedString));
        passage.setDate_expiration(java.sql.Date.valueOf(formDate));
        passage.setScore(0);
        passage.setCandidate(requestMap.get("candidate"));
        passage.setStatus_passage("false");
        passage.setUser(userObj);

        return passage;
    }
}
