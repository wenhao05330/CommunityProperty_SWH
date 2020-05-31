package com.etoak.bean;

import lombok.Data;

/**
 * Created by P870TM1-G on 2019/12/27.
 */
@Data
public class Repair {
    private Integer id;
    private String userid;
    private String name;
    private String location;
    private String remark;
    private Integer cost;
    private String publish_time;
}
