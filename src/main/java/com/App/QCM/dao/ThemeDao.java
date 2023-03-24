package com.App.QCM.dao;

import com.App.QCM.Model.Theme;
import com.App.QCM.Wrapper.ThemeWrapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThemeDao extends JpaRepository<Theme, Integer> {

    List<ThemeWrapper> getAllTheme();
}
