package com.chichi.market.controller;


import com.chichi.market.dao.GoodsDao;
import com.chichi.market.entity.Goods;
import com.chichi.market.entity.ShoppingCart;
import com.chichi.market.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

@Controller
public class IndexController {

    @Autowired
    GoodsDao goodsDao;

    public static List<String> goodsIds = new ArrayList<>();

    @RequestMapping("/")
    String index(Model model) {
        List<Goods> allGoods = goodsDao.selectAll();
        model.addAttribute("allGoods", allGoods);
        model.addAttribute("currentUser", LoginController.currentUser);
        return "index";
    }

    @RequestMapping("/loginPage")
    String loginPage(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "loginPage";
    }

    @PostMapping("/addToCart")
    String addToCart(@RequestParam String id, Model model){
        List<Goods> allGoods = goodsDao.selectAll();
        model.addAttribute("allGoods", allGoods);
        goodsIds.add(id);
        return "index";
    }

    @RequestMapping("/goodsPage")
    String goodsPage(Model model){
        Goods goods = new Goods();
        model.addAttribute("goods", goods);
        return "goodsPage";
    }

    @RequestMapping("/shoppingCartPage")
    String shoppingCartPage(Model model){
        List<Goods> selectedGoods = new ArrayList<>();
        for (String goodsId : goodsIds) selectedGoods.add(goodsDao.selectByPrimaryKey(parseInt(goodsId)));
        model.addAttribute("selectedGoods", selectedGoods);
        return "shoppingCartPage";
    }

    @PostMapping("/paySelectedGoods")
    String paySelectedGoods(Model model){
        List<Goods> selectedGoods = new ArrayList<>();
        int totalPrice = 0;
        for (String goodsId : goodsIds) {
            Goods tempGoods = goodsDao.selectByPrimaryKey(parseInt(goodsId));
            selectedGoods.add(tempGoods);
            totalPrice += parseInt(tempGoods.getPrice());
        }
        model.addAttribute("selectedGoods", selectedGoods);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("currentUser", LoginController.currentUser);
        return "shoppingCartPage";
    }



}
