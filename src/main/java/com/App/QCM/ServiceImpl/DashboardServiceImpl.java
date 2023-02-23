package com.App.QCM.ServiceImpl;

import com.App.QCM.Service.DashboardService;
import com.App.QCM.dao.ThemeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    ThemeDao themeDao;

    @Override
    public ResponseEntity<Map<String, Object>> getCount() {
        Map<String, Object> map = new HashMap<>();
        map.put("theme", themeDao.count());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
