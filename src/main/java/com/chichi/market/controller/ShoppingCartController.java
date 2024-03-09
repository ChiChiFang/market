package com.chichi.market.controller;

import com.chichi.market.dao.GoodsDao;
import com.chichi.market.entity.Goods;
import com.chichi.market.entity.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

@Controller
public class ShoppingCartController {

    @Autowired
    GoodsDao goodsDao;

    @PostMapping("/updateCart")
    String addToCart(@RequestParam String action, Model model) {
        List<Goods> selectedGoods = new ArrayList<>();
        if(action.endsWith("+")) IndexController.goodsIds.add(action.substring(0,action.length()-1));
        else IndexController.goodsIds.remove(action.substring(0,action.length()-1));
        for (String goodsId : IndexController.goodsIds) selectedGoods.add(goodsDao.selectByPrimaryKey(parseInt(goodsId)));
        model.addAttribute("selectedGoods", selectedGoods);
        return "shoppingCartPage";
    }
}
