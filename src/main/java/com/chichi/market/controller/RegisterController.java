package com.chichi.market.controller;

import com.chichi.market.dao.UserDao;
import com.chichi.market.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import javax.validation.Valid;

@Controller
public class RegisterController {
    @Autowired
    UserDao userDao;

    @PostMapping("/registerAction")
    public String registration(@Valid @ModelAttribute("user") User user,
                               BindingResult result,
                               Model model){

        User existingUser = userDao.selectUserByUserName(user.getUserName());

        if(existingUser != null && existingUser.getUserName() != null && !existingUser.getUserName().isEmpty()){
            result.rejectValue("userName", "There is already an account registered with the same username");
        }

        if(result.hasErrors()){
            model.addAttribute("user", user);
            return "redirect:/registerPage?fail";
        }

        User user1 = new User();
        user1.setUserName(user.getUserName());
        user1.setPassword(user.getPassword());

        userDao.insert(user1);
        return "redirect:/registerPage?success";
    }
}
