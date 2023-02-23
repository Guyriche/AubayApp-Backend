package com.App.QCM.JWT;

import com.App.QCM.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
public class CustomerUserDetailService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    private com.App.QCM.Model.User userDetail;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        userDetail = userDao.findByEmailId(username);
        if (!Objects.isNull(userDetail)) {
            return new User(userDetail.getEmail(), userDetail.getPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not Found.");
        }
    }

    public com.App.QCM.Model.User getUserDetail() {
        return userDetail;
    }
}
