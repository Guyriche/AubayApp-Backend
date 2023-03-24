package com.App.QCM.ServiceImpl;

import com.App.QCM.JWT.JwtFilter;
import com.App.QCM.Model.Difficulty;
import com.App.QCM.Model.Proposition;
import com.App.QCM.Model.Question;
import com.App.QCM.Model.Theme;
import com.App.QCM.Service.QuestionService;
import com.App.QCM.Utils.QcmUtils;
import com.App.QCM.Wrapper.QuestionWrapper;
import com.App.QCM.constents.QcmConstants;
import com.App.QCM.dao.PropositionDao;
import com.App.QCM.dao.QuestionDao;
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

    @Autowired
    PropositionDao propositionDao;

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
        if (requestMap.containsKey("content_question") && requestMap.containsKey("numQuestion"))
            if (requestMap.containsKey("id") && b) {
                return true;
            } else if (!b) {
                return true;
            }
        return false;
    }

    private Question getQuestionFromMap(Map<String, String> requestMap, boolean isAdd) {
        Question question = new Question();

        Theme theme = new Theme();
        theme.setId(Integer.parseInt(requestMap.get("themeId")));

        if (isAdd) {
            question.setId(Integer.parseInt(requestMap.get("id")));
        }
        question.setTheme(theme);
        question.setContent_question(requestMap.get("content_question"));
        question.setNumQuestion(Integer.parseInt(requestMap.get("numQuestion")));
        question.setDifficulty(Difficulty.valueOf(requestMap.get("difficulty")));

        return question;
    }

    @Override
    public ResponseEntity<String> addPropositionToQuestion(Integer questionId, Proposition propositionRequest) {
        try {
            if (jwtFilter.isAdmin()) {
                Optional<Question> optQuestion = questionDao.findById(questionId);
                if (optQuestion.isEmpty()) {
                    return QcmUtils.getResponseEntity("This Question doesn't Exist", HttpStatus.NOT_FOUND);
                }

                Question question = optQuestion.get();
                if (propositionRequest.getId() != null) {
                    Optional<Proposition> optProposition = propositionDao.findById(propositionRequest.getId());
                    //Proposition proposition = optProposition.get();
                    if (!optProposition.isEmpty()) {

                        if (question.getPropositions().contains(optProposition)) {
                            return QcmUtils.getResponseEntity("This Proposition is Already Exist", HttpStatus.OK);
                        }
                        question.addProposition(optProposition.get());
                        questionDao.save(question);

                    }
                    return QcmUtils.getResponseEntity("Proposition Added Successfully..", HttpStatus.OK);
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
    public ResponseEntity<List<Question>> getAllQuestion() {
        try {
            return new ResponseEntity<>(questionDao.getAllQuestion(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
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
                if (!optional.isEmpty()) {
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

    @Override
    public ResponseEntity<List<QuestionWrapper>> getQuestionByThemeId(Integer themeId) {
        try {
            return new ResponseEntity<>(questionDao.getQuestionByTheme(themeId), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<QuestionWrapper>> getAllQuestionByQcmId(Integer qcmId) {
        try {
            System.out.print("test2");
            return new ResponseEntity<>(questionDao.getAllQuestionByQcmId(qcmId), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.print("error2");
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<QuestionWrapper>> getAllQuestionWrapper() {
        try {
            System.out.print("test1");
            return new ResponseEntity<>(questionDao.getAllQuestionWrapper(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
