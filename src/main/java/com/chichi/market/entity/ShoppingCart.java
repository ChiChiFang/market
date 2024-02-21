package com.chichi.market.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * shopping_cart
 */
@Data
public class ShoppingCart implements Serializable {
    private Integer shoppingCartId;

    private Integer userId;

    private String goodsIds;

    private static final long serialVersionUID = 1L;
}