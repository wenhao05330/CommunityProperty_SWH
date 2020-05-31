package com.etoak.bean;

import lombok.Data;

@Data
public class Owner {
    private Integer id;
    private String name;
    private String password;
    private String location;
    private String idno;
    private String phone;
    private Integer level;
    private String publish_time;
}
