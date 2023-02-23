package com.App.QCM.ServiceImpl;

import com.App.QCM.JWT.JwtFilter;
import com.App.QCM.Model.Proposition;
import com.App.QCM.Model.Question;
import com.App.QCM.Service.PropositionService;
import com.App.QCM.Utils.QcmUtils;
import com.App.QCM.constents.QcmConstants;
import com.App.QCM.dao.PropositionDao;
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
public class PropositionServiceImpl implements PropositionService {

    @Autowired
    JwtFilter jwtFilter;

    @Autowired
    PropositionDao propositionDao;

    @Autowired
    QuestionDao questionDao;



    /*@Override
    public ResponseEntity<String> addNewProposition(Map<String, String> propositionRequestMap) {
        try {
            if(jwtFilter.isAdmin()){
                if(validatePropositionMap(propositionRequestMap, false)){
                    Optional optional = questionDao.findById(Integer.parseInt(propositionRequestMap.get("id")));
                    if(!optional.isEmpty()){
                        System.out.print("Test2");
                        propositionDao.save(getPropositionFromMap(propositionRequestMap, false));
                        System.out.print("Test3");
                        return QcmUtils.getResponseEntity("Proposition Added Successfully..", HttpStatus.OK);
                    }else {
                        return QcmUtils.getResponseEntity("Question id does not exist", HttpStatus.OK);
                    }
                }
                System.out.print("Test4");
                return QcmUtils.getResponseEntity(QcmConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
            else {
                return QcmUtils.getResponseEntity(QcmConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return QcmUtils.getResponseEntity(QcmConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }*/

    @Override
    public ResponseEntity<String> addNewProposition(Integer questionId, Map<String, String> propositionReq) {
        try {
            if (jwtFilter.isAdmin()) {
                Optional<Question> optionalQuestion = questionDao.findById(questionId);
                if (optionalQuestion.isEmpty()) {
                    return QcmUtils.getResponseEntity("This Question does not exist", HttpStatus.OK);
                }
                Question question = optionalQuestion.get();
                //Optional<Proposition> propositionOptional = propositionDao.findById(Integer.parseInt(propositionReq.get("id")));
                //Proposition proposition;
                //if(propositionOptional.isEmpty()){
                    /*proposition = propositionDao.save(new Proposition(propositionReq.getNum_proposition()
                            , propositionReq.getContent_proposition(), propositionReq.getStatus_proposition()));*/
                question.addProposition(getPropositionFromMap(propositionReq, false));
                questionDao.save(question);
                return QcmUtils.getResponseEntity("Proposition Added Successfully..", HttpStatus.OK);
                //}
                /*else {
                    question.addProposition(propositionOptional.get());
                    questionDao.save(question);
                    return  QcmUtils.getResponseEntity("Proposition updated Successfully..", HttpStatus.OK);
                }*/
                //return QcmUtils.getResponseEntity(QcmConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            } else {
                return QcmUtils.getResponseEntity(QcmConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return QcmUtils.getResponseEntity(QcmConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validatePropositionMap(Map<String, String> requestMap, boolean validateId) {
        if (requestMap.containsKey("content_proposition") && requestMap.containsKey("status_proposition")) {
            System.out.print("val");
            if (requestMap.containsKey("id") && validateId) {
                System.out.print("val1");
                return true;
            } else if (!validateId) {
                System.out.print("val2");
                return true;
            }
        }
        System.out.print(requestMap.containsKey("content_proposition"));
        System.out.print(requestMap.containsKey("status_proposition"));
        System.out.print(requestMap.containsKey("id"));
        return false;
    }

    private Proposition getPropositionFromMap(Map<String, String> requestMap, boolean isAdd) {
        Proposition proposition = new Proposition();

        if (isAdd) {
            proposition.setId(Integer.parseInt(requestMap.get("id")));
        }
        proposition.setNum_proposition(Integer.parseInt(requestMap.get("num_proposition")));
        proposition.setContent_proposition(requestMap.get("content_proposition"));
        proposition.setStatus_proposition(Boolean.parseBoolean("status_proposition"));

        return proposition;
    }

    @Override
    public ResponseEntity<List<Proposition>> getAllProposition(String filterValue) {
        try {
            if (!Strings.isNullOrEmpty(filterValue) && filterValue.equalsIgnoreCase("true")) {
                return new ResponseEntity<List<Proposition>>(propositionDao.getAllProposition(), HttpStatus.OK);
            }
            return new ResponseEntity<>(propositionDao.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<List<Proposition>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateProposition(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                Optional<Proposition> optionalProposition = propositionDao.findById(Integer.parseInt(requestMap.get("id")));
                if (optionalProposition.isEmpty()) {
                    return QcmUtils.getResponseEntity("Proposition " + Integer.parseInt(requestMap.get("id"))
                            + "not Found.", HttpStatus.NOT_FOUND);
                }
                propositionDao.save(getPropositionFromMap(requestMap, true));
                return QcmUtils.getResponseEntity("Proposition Updated Successfully..", HttpStatus.OK);
            } else {
                return QcmUtils.getResponseEntity(QcmConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return QcmUtils.getResponseEntity(QcmConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteProposition(Integer id) {
        try {
            if (jwtFilter.isAdmin()) {
                Optional optional = propositionDao.findById(id);
                if (optional.isEmpty()) {
                    propositionDao.deleteById(id);
                    return QcmUtils.getResponseEntity("Proposition Deleted Successfully..", HttpStatus.OK);
                }
                return QcmUtils.getResponseEntity("This Proposition does not exist..", HttpStatus.OK);
            } else {
                return QcmUtils.getResponseEntity(QcmConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return QcmUtils.getResponseEntity(QcmConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Proposition> getPropositionById(Integer propositionId) {
        try {
            if (jwtFilter.isAdmin()) {
                Optional<Proposition> optionalProposition = propositionDao.findById(propositionId);
                if (optionalProposition.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
                return new ResponseEntity<>(optionalProposition.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Proposition>> getAllPropositionByQuestionsId(Integer questionId) {
        try {
            if (!questionDao.existsById(questionId)) {
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
            }
            List<Proposition> propositions = propositionDao.findPropositionByQuestionsId(questionId);
            return new ResponseEntity<>(propositions, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deletePropositionFromQuestion(Integer questionId, Integer propositionId) {
        try {
            if (jwtFilter.isAdmin()) {
                Optional<Question> optionalQuestion = questionDao.findById(questionId);
                Question question = new Question();
                if (optionalQuestion.isEmpty()) {
                    return QcmUtils.getResponseEntity("Not Found Question With id " + questionId
                            , HttpStatus.NOT_FOUND);
                }
                question.removeProposition(propositionId);
                questionDao.save(question);
                return QcmUtils.getResponseEntity("Proposition " + propositionId +
                        " delete Successfully..", HttpStatus.OK);
            } else {
                return QcmUtils.getResponseEntity(QcmConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return QcmUtils.getResponseEntity(QcmConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
