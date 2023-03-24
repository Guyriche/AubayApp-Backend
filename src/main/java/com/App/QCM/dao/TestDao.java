package com.App.QCM.dao;

import com.App.QCM.Model.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestDao extends JpaRepository<Test, Integer> {

    List<Test> getAllTest();
}
