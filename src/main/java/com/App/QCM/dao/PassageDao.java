package com.App.QCM.dao;

import com.App.QCM.Model.Passage;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

public interface PassageDao extends JpaRepository<Passage, Integer> {

    @Transactional
    @Modifying
    Integer updateStatus(@Param("status_passage") String status_passage, @Param("id") Integer id);

    /*@Transactional
    @Modifying
    Integer updateScore(@Param("score") String score, @Param("id") Integer id);*/


}
