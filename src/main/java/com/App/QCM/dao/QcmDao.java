package com.App.QCM.dao;

import com.App.QCM.Model.Qcm;
import com.App.QCM.Wrapper.QcmWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QcmDao extends JpaRepository<Qcm, Integer> {

    List<Qcm> getAllQcm();

    List<QcmWrapper> getAllQcmByTestId(@Param("id") Integer testId);

}
