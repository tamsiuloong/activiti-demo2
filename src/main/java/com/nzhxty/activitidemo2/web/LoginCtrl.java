package com.nzhxty.activitidemo2.web;

import com.nzhxty.activitidemo2.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LoginCtrl {


    @PostMapping("login")
    public String login(@RequestBody User user, HttpSession session)
    {
        //将用户保存到session中
        session.setAttribute("user",user);
        return "ok";
    }

}
