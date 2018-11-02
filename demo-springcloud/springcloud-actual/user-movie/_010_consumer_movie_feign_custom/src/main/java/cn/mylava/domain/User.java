package cn.mylava.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 10/10/2018
 * @company: (C) Copyright 58BTC 2018
 * @since: JDK 1.8
 * @description:
 */
@Data
public class User {
    private Long id;

    private String username;

    private String name;

    private Short age;

    private BigDecimal balance;
}
