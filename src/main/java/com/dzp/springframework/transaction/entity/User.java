package com.dzp.springframework.transaction.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * user
 *
 * @author
 */
@Data
@AllArgsConstructor
public class User implements Serializable {
    private Integer id;

    private String name;

    private Integer age;

    private String email;

    private static final long serialVersionUID = 1L;


}