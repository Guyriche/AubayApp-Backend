package com.App.QCM.ServiceImpl;

import com.App.QCM.JWT.JwtFilter;
import com.App.QCM.Model.Difficulty;
import com.App.QCM.Model.Qcm;
import com.App.QCM.Model.Question;
import com.App.QCM.Model.Theme;
import com.App.QCM.Service.QcmService;
import com.App.QCM.Utils.QcmUtils;
import com.App.QCM.Wrapper.QcmWrapper;
import com.App.QCM.constents.QcmConstants;
import com.App.QCM.dao.QcmDao;
import com.App.QCM.dao.QuestionDao;
import com.App.QCM.dao.ThemeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class QcmServiceImpl implements QcmService {

    @Autowired
    QcmDao qcmDao;

    @Autowired
    QuestionDao questionDao;

    @Autowired
    ThemeDao themeDao;

    @Autowired
    JwtFilter jwtFilter;

    @Override
    public ResponseEntity<String> addNewQcm(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                if (validateQcm(requestMap, false)) {
                    qcmDao.save(getQcmFromMap(requestMap, false));
                    return QcmUtils.getResponseEntity("Qcm Added Successfully..", HttpStatus.OK);
                } else {
                    return QcmUtils.getResponseEntity("Data Isn't Correct.", HttpStatus.BAD_REQUEST);
                }
            } else {
                return QcmUtils.getResponseEntity(QcmConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return QcmUtils.getResponseEntity(QcmConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private Qcm getQcmFromMap(Map<String, String> requestMap, boolean isAdd) {
        Qcm qcm = new Qcm();

        if (isAdd) {
            qcm.setId(Integer.parseInt(requestMap.get("id")));
        }
        qcm.setTitle_qcm(requestMap.get("title_qcm"));
        qcm.setDescription_qcm(requestMap.get("description_qcm"));
        qcm.setDifficulty_qcm(Difficulty.valueOf(requestMap.get("difficulty_qcm")));
        qcm.setDuration_qcm(Integer.parseInt(requestMap.get("duration_qcm")));
        qcm.setNum_qcm(Integer.parseInt(requestMap.get("num_qcm")));
        //qcm.setTotal_questions(qcm.getQuestions().size());

        return qcm;
    }

    private boolean validateQcm(Map<String, String> requestMap, boolean b) {
        if (requestMap.containsKey("title_qcm") && requestMap.containsKey("description_qcm"))
            if (requestMap.containsKey("id") && b) {
                return true;
            } else if (!b) {
                return true;
            }
        return false;
    }

    @Override
    public ResponseEntity<String> addQuestionIntoQcm(Integer qcmId, Question questionRequest) {
        try {
            if (jwtFilter.isAdmin()) {
                Optional<Qcm> optQcm = qcmDao.findById(qcmId);
                if (optQcm.isEmpty()) {
                    return QcmUtils.getResponseEntity("This QCM does not exist", HttpStatus.NOT_FOUND);
                }

                Qcm qcm = optQcm.get();
                Theme theme = questionDao.findThemeByQuestionId(questionRequest.getId());
                if (questionRequest.getId() != null) {
                    Optional<Question> optQuestion = questionDao.findById(questionRequest.getId());
                    if (!optQuestion.isEmpty()) {
                        questionRequest.setQcm(qcm);
                        questionRequest.setTheme(theme);
                        questionDao.save(questionRequest);
                        return QcmUtils.getResponseEntity("This Question Added Successfully", HttpStatus.OK);
                    } else {
                        return QcmUtils.getResponseEntity("This Question does not exist", HttpStatus.NOT_FOUND);
                    }
                } else {
                    Question resQuestion = questionDao.save(new Question(questionRequest.getNumQuestion(), questionRequest.getContent_question(),
                            questionRequest.getDifficulty(), questionRequest.getPropositions(), questionRequest.getTheme(), qcm));
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
    public ResponseEntity<List<Qcm>> getAllQcm() {
        try {
            return new ResponseEntity<>(qcmDao.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Qcm> getQcmById(Integer qcmId) {
        try {
            Optional<Qcm> optQcm = qcmDao.findById(qcmId);
            if (!optQcm.isEmpty()) {
                optQcm.get();
                return new ResponseEntity<>(optQcm.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateQcm(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                if (validateQcm(requestMap, true)) {
                    Optional<Qcm> optQcm = qcmDao.findById(Integer.parseInt(requestMap.get("id")));
                    if (optQcm.isPresent()) {
                        qcmDao.save(getQcmFromMap(requestMap, true));
                        return QcmUtils.getResponseEntity("Qcm Updated Successfully", HttpStatus.OK);
                    } else {
                        return QcmUtils.getResponseEntity("This Qcm does not exist", HttpStatus.OK);
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
    public ResponseEntity<String> deleteQcm(Integer id) {
        try {
            if (jwtFilter.isAdmin()) {
                Optional optQcm = qcmDao.findById(id);
                if (!optQcm.isEmpty()) {
                    qcmDao.deleteById(id);
                    return QcmUtils.getResponseEntity("Qcm Deleted Successfully..", HttpStatus.OK);
                }
                return QcmUtils.getResponseEntity("This Qcm does not exist..", HttpStatus.OK);
            } else {
                return QcmUtils.getResponseEntity(QcmConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return QcmUtils.getResponseEntity(QcmConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<QcmWrapper>> getAllQcmByTestId(Integer testId) {
        try {
            return new ResponseEntity<>(qcmDao.getAllQcmByTestId(testId), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
