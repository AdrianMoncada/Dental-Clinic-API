package com.dh.dentalClinic.user.controllers;
import com.dh.dentalClinic.user.dao.UserDao;
import com.dh.dentalClinic.user.models.User;
import com.dh.dentalClinic.user.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody User user) {

        User loggedUser = userDao.obtainByCredentials(user);
        if (loggedUser != null) {
            String tokenJwt = jwtUtil.create(String.valueOf(loggedUser.getId()), loggedUser.getEmail());
            return tokenJwt;
        }
        return "FAIL";
    }

}
