package com.App.QCM.ServiceImpl;

import com.App.QCM.Service.DashboardService;
import com.App.QCM.dao.*;
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

    @Autowired
    QuestionDao questionDao;

    @Autowired
    QcmDao qcmDao;

    @Autowired
    TestDao testDao;

    @Autowired
    UserDao userDao;

    @Override
    public ResponseEntity<Map<String, Object>> getCount() {
        Map<String, Object> map = new HashMap<>();
        map.put("theme", themeDao.count());
        map.put("question", questionDao.count());
        map.put("qcm", qcmDao.count());
        map.put("test", testDao.count());
        map.put("user", userDao.countUser());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
