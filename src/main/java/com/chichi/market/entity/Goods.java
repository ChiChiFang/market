package com.chichi.market.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * goods
 */
@Data
public class Goods implements Serializable {
    private Integer goodsId;

    private String goodsName;

    private String price;

    private String img;

    private String description;

    private Integer count;

    private static final long serialVersionUID = 1L;
}