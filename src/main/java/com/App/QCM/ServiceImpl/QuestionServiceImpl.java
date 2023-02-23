package com.App.QCM.ServiceImpl;

import com.App.QCM.JWT.JwtFilter;
import com.App.QCM.Model.Difficulty;
import com.App.QCM.Model.Question;
import com.App.QCM.Service.QuestionService;
import com.App.QCM.Utils.QcmUtils;
import com.App.QCM.constents.QcmConstants;
import com.App.QCM.dao.QuestionDao;
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
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    JwtFilter jwtFilter;

    @Autowired
    QuestionDao questionDao;

    @Override
    public ResponseEntity<String> addNewQuestion(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                if (validateQuestionMap(requestMap, false)) {
                    questionDao.save(getQuestionFromMap(requestMap, false));
                    return QcmUtils.getResponseEntity("Question Added Successfully..", HttpStatus.OK);
                } else {
                    return QcmUtils.getResponseEntity("Echos", HttpStatus.OK);
                }
            } else {
                QcmUtils.getResponseEntity(QcmConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return QcmUtils.getResponseEntity(QcmConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateQuestionMap(Map<String, String> requestMap, boolean b) {
        if (requestMap.containsKey("content_question") && requestMap.containsKey("num_question"))
            if (requestMap.containsKey("id") && b) {
                return true;
            } else if (!b) {
                return true;
            }
        return false;
    }

    private Question getQuestionFromMap(Map<String, String> requestMap, boolean isAdd) {
        Question question = new Question();
        if (isAdd) {
            question.setId(Integer.parseInt(requestMap.get("id")));
        }
        question.setContent_question(requestMap.get("content_question"));
        question.setNumQuestion(Integer.parseInt(requestMap.get("num_question")));
        question.setDifficulty(Difficulty.valueOf(requestMap.get("difficulty")));

        return question;
    }

    @Override
    public ResponseEntity<List<Question>> getAllQuestion(String filterValue) {
        try {
            if (!Strings.isNullOrEmpty(filterValue) && filterValue.equalsIgnoreCase("true")) {
                return new ResponseEntity<List<Question>>(questionDao.getAllQuestion(), HttpStatus.OK);
            }
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<List<Question>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateQuestion(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                if (validateQuestionMap(requestMap, true)) {
                    Optional optional = questionDao.findById(Integer.parseInt(requestMap.get("id")));
                    if (optional.isPresent()) {
                        questionDao.save(getQuestionFromMap(requestMap, true));
                        return QcmUtils.getResponseEntity("Question Updated Successfully..", HttpStatus.OK);
                    } else {
                        return QcmUtils.getResponseEntity("Question id does not exist", HttpStatus.OK);
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
    public ResponseEntity<String> deleteQuestion(Integer id) {
        try {
            if (jwtFilter.isAdmin()) {
                Optional optional = questionDao.findById(id);
                if (optional.isEmpty()) {
                    questionDao.deleteById(id);
                    return QcmUtils.getResponseEntity("Question Deleted Successfully..", HttpStatus.OK);
                }
                return QcmUtils.getResponseEntity("Question id does not exist..", HttpStatus.OK);
            } else {
                return QcmUtils.getResponseEntity(QcmConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return QcmUtils.getResponseEntity(QcmConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Question> getQuestionById(Integer questionId) {
        try {
            Optional<Question> optionalQuestion = questionDao.findById(questionId);
            if (!optionalQuestion.isEmpty()) {
                optionalQuestion.get();
                return new ResponseEntity<>(optionalQuestion.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
