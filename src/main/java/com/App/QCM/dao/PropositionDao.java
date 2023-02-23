package com.App.QCM.dao;

import com.App.QCM.Model.Proposition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropositionDao extends JpaRepository<Proposition, Integer> {

    List<Proposition> getAllProposition();

    List<Proposition> findPropositionByQuestionsId(Integer questionId);

    //Proposition findPropositionByID(int propositionId);
}
