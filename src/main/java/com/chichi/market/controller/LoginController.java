package com.chichi.market.controller;

import com.chichi.market.dao.GoodsDao;
import com.chichi.market.dao.UserDao;
import com.chichi.market.entity.Goods;
import com.chichi.market.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.engine.TemplateData;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    UserDao userDao;

    @Autowired
    GoodsDao goodsDao;

    public static User currentUser;

    @PostMapping("/loginAction")
    String login(User user,Model model){
        List<Goods> allGoods = goodsDao.selectAll();
        model.addAttribute("allGoods", allGoods);
        User existingUser = userDao.selectUserByUserName(user.getUserName());
        if(existingUser == null) return "redirect:/loginPage?fail";
        else if(!existingUser.getPassword().equals(user.getPassword())) return "redirect:/loginPage?fail";
        currentUser = existingUser;
        model.addAttribute("currentUser", existingUser);
        return "redirect:/index?success";
    }

    @GetMapping("/registerPage")
    String registerPage(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "registerPage";
    }
}
