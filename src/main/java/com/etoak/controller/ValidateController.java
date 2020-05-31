package com.etoak.controller;

import com.etoak.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/validate")
public class ValidateController {
    @Autowired
    OwnerService ownerService;

    @RequestMapping("/name")
    @ResponseBody
    public String name(@RequestParam String name) {
        if(ownerService.queryByName(name)!=null) {
            return "false"; // 不可用
        }
        return "true"; // 可以用
    }
}
