package com.etoak.controller;

import com.etoak.bean.Et1908Page;
import com.etoak.bean.Owner;
import com.etoak.bean.Repair;
import com.etoak.common.Et1908LoginException;
import com.etoak.service.OwnerService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/owner")
public class OwnerController {
    @Autowired
    OwnerService ownerService;

    @RequestMapping("/add")
    @ResponseBody
    public Map<String, String> addOwner(Owner owner){
        SimpleDateFormat p = new SimpleDateFormat("yyyy-MM-dd");
        owner.setPublish_time(p.format(new Date()));
        owner.setLevel(1);
        ownerService.addOwner(owner);
        Map<String,String > msg = new HashMap<>();
        msg.put("msg","success");
        return msg;
    }

    @RequestMapping("/to_add")
    public String toAdd(){
        return "add";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Et1908Page<Owner> queryList(@RequestParam(required = false, defaultValue = "1")int pageNum, @RequestParam(required = false, defaultValue = "10")int pageSize, @RequestParam("queryName") String queryName){
        return ownerService.queryList(pageNum, pageSize, queryName);
    }

    @RequestMapping("/to_list")
    public String toList(){
        return "list";
    }

    @RequestMapping("/del")
    @ResponseBody
    public String delOwner(Integer id){
        ownerService.delOwner(id);
        return "success";
    }

    @RequestMapping("/update")
    public String updateOwner(Owner owner){
        ownerService.updateOwner(owner);
        return "redirect:/owner/to_list";
    }

    @RequestMapping("/to_login")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        // 销毁session之后，跳转到登录页面
        return "redirect:/user/to_login";
    }

    @RequestMapping("/to_repair")
    public String repair(){
        return "repair/addRepair";
    }

    @RequestMapping("/addRepair")
    @ResponseBody
    public Map<String,String> addRepair(Repair repair){
        SimpleDateFormat p = new SimpleDateFormat("yyyy-MM-dd");
        repair.setPublish_time(p.format(new Date()));
        ownerService.addRepair(repair);
        Map<String,String> res = new HashMap<>();
        res.put("msg","success");
        return res;
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request,
                        @RequestParam String name, @RequestParam String password) {

        // 先对比验证码
        HttpSession session = request.getSession();
        Owner owner = ownerService.queryByNameAndPassword(name, password);
        if(owner == null) {
            throw new Et1908LoginException("用户名或密码错误");
        }

        if(owner.getLevel()==1){
            // 销毁之前session，这样的话之前保存的验证码就失效了
            session.invalidate();

            // 重新获取一个session保存用户信息
            request.getSession().setAttribute("user", owner);
            return "redirect:/owner/to_repair";
        }

        // 销毁之前session，这样的话之前保存的验证码就失效了
        session.invalidate();

        // 重新获取一个session保存用户信息
        request.getSession().setAttribute("user", owner);
        return "redirect:/owner/to_list";
    }

    @RequestMapping("/listRepair")
    @ResponseBody
    public Et1908Page<Owner> queryRepairList(@RequestParam(required = false, defaultValue = "1")int pageNum, @RequestParam(required = false, defaultValue = "10")int pageSize, @RequestParam("queryLocation") String queryLocation){
        return ownerService.queryRepairList(pageNum, pageSize, queryLocation);
    }

    @RequestMapping("/to_listRepair")
    public String toListRepair(){
        return "/repair/listRepair";
    }

    @RequestMapping("/to_analysis")
    public String toAnalysis(){
        return "analysis";
    }

    @RequestMapping("/getThreeList")
    @ResponseBody
    public Map<String,String> getThreeList(){
        try{
            Map<String,String> map = ownerService.queryXname();
            return map;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/getThirtyList")
    @ResponseBody
    public Map<String,String> getThirtyList(){
        Map<String,String> map = ownerService.query30Xname();
        return map;
    }

}
