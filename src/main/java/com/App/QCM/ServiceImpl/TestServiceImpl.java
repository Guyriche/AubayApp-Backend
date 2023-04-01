package com.App.QCM.ServiceImpl;

import com.App.QCM.JWT.JwtFilter;
import com.App.QCM.Model.*;
import com.App.QCM.Service.TestService;
import com.App.QCM.Utils.QcmUtils;
import com.App.QCM.Wrapper.TestWrapper;
import com.App.QCM.constents.QcmConstants;
import com.App.QCM.dao.QcmDao;
import com.App.QCM.dao.TestDao;
import com.App.QCM.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    JwtFilter jwtFilter;

    @Autowired
    TestDao testDao;

    @Autowired
    QcmDao qcmDao;

    @Autowired
    UserDao userDao;

    @Override
    public ResponseEntity<String> add(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                if (validateTest(requestMap, false)) {
                    testDao.save(getTestFromMap(requestMap, false));
                    return QcmUtils.getResponseEntity("Test Added Successfully..", HttpStatus.OK);
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

    private boolean validateTest(Map<String, String> requestMap, boolean b) {
        if (requestMap.containsKey("title_test") && requestMap.containsKey("description_test")) {
            if (requestMap.containsKey("id") && b) {
                return true;
            } else if (!b) {
                return true;
            }
        }
        return false;
    }

    private Test getTestFromMap(Map<String, String> requestMap, boolean isAdd) {
        Test test = new Test();

        Theme theme = new Theme();
        theme.setId(Integer.parseInt(requestMap.get("themeId")));

        User userObj = userDao.findByEmail(jwtFilter.getCurrentUser());

        if (isAdd) {
            test.setId(Integer.parseInt(requestMap.get("id")));
        }
        test.setTitle_test(requestMap.get("title_test"));
        test.setDescription_test(requestMap.get("description_test"));
        test.setLevel(AllLevel.valueOf(requestMap.get("level")));
        test.setTheme(theme);
        test.setUser(userObj);

        return test;
    }

    @Override
    public ResponseEntity<String> addQcmToTest(Integer testId, Qcm qcmRequest) {
        try {
            if (jwtFilter.isAdmin()) {
                Optional<Test> optTest = testDao.findById(testId);
                if (optTest.isEmpty()) {
                    return QcmUtils.getResponseEntity("This Test does not Exist", HttpStatus.NOT_FOUND);
                }

                Test test = optTest.get();
                if (qcmRequest.getId() != null) {
                    Optional<Qcm> optionalQcm = qcmDao.findById(qcmRequest.getId());
                    if (!optionalQcm.isEmpty()) {
                        qcmRequest.setTest(test);
                        qcmDao.save(qcmRequest);
                        return QcmUtils.getResponseEntity("This Test Added Successfully..", HttpStatus.OK);
                    } else {
                        return QcmUtils.getResponseEntity("This Question does not exist", HttpStatus.NOT_FOUND);
                    }
                } else {
                    Qcm resQcm = qcmDao.save(new Qcm(qcmRequest.getTitle_qcm(), qcmRequest.getDescription_qcm(),
                            qcmRequest.getDifficulty_qcm(), qcmRequest.getNum_qcm(), qcmRequest.getDuration_qcm(), test));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return QcmUtils.getResponseEntity(QcmConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Test>> getAllTest() {
        try {
            return new ResponseEntity<>(testDao.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Test> getTestById(Integer testId) {
        try {
            Optional<Test> optTest = testDao.findById(testId);
            if (!optTest.isEmpty()) {
                optTest.get();
                return new ResponseEntity<>(optTest.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateTest(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                if (validateTest(requestMap, true)) {
                    Optional<Test> optTest = testDao.findById(Integer.parseInt(requestMap.get("id")));
                    if (optTest.isPresent()) {
                        testDao.save(getTestFromMap(requestMap, true));
                        return QcmUtils.getResponseEntity("Test Updated Successfully", HttpStatus.OK);
                    } else {
                        return QcmUtils.getResponseEntity("This Test Does not Exist..", HttpStatus.OK);
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
    public ResponseEntity<String> deleteTest(Integer id) {
        try {
            if (jwtFilter.isAdmin()) {
                Optional<Test> optTest = testDao.findById(id);
                if (!optTest.isEmpty()) {
                    testDao.deleteById(id);
                    return QcmUtils.getResponseEntity("Test Deleted Successfully", HttpStatus.OK);
                }
                return QcmUtils.getResponseEntity("This Test does not exist", HttpStatus.OK);
            } else {
                return QcmUtils.getResponseEntity(QcmConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return QcmUtils.getResponseEntity(QcmConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<TestWrapper>> getTestByThemeId(Integer themeId) {
        try {
            return new ResponseEntity<>(testDao.getTestByThemeId(themeId), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<TestWrapper>> getAllTestByPassageId(Integer passageId) {
        try {
            return new ResponseEntity<>(testDao.getAllTestByPassageId(passageId), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
