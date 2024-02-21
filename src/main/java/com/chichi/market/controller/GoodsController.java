package com.chichi.market.controller;

import com.chichi.market.dao.GoodsDao;
import com.chichi.market.entity.Goods;
import com.chichi.market.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class GoodsController {

    @Autowired
    GoodsDao goodsDao;

    @RequestMapping("/goods")
    String goodsPage(Model model){
        Goods goods = new Goods();
        model.addAttribute("goods", goods);
        return "goodsPage";
    }

    @PostMapping("/addGoods")
    public String registration(@Valid @ModelAttribute("goods") Goods goods,
                               BindingResult result,
                               Model model){

        Goods goods1 = new Goods();

        goods1.setGoodsName(goods.getGoodsName());
        goods1.setPrice(goods.getPrice());
        goods1.setCount(1);
        goods1.setImg(goods.getImg());

        goodsDao.insert(goods1);
        return "redirect:/goodsPage?success";
    }
}
