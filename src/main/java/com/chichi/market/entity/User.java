package com.chichi.market.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * user
 */
@Data
public class User implements Serializable {
    private Integer userId;

    private String userName;

    private String password;

    private static final long serialVersionUID = 1L;
}