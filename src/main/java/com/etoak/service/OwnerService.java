package com.etoak.service;

import com.etoak.bean.Et1908Page;
import com.etoak.bean.Owner;
import com.etoak.bean.Repair;
import com.etoak.mapper.OwnerMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OwnerService {
    @Autowired
    OwnerMapper ownerMapper;

    public void addOwner(Owner owner) {
        ownerMapper.addOwner(owner);
    }

    public Owner queryByName(String name) {
        return ownerMapper.queryByName(name);
    }

    public Et1908Page<Owner> queryList(int pageNum, int pageSize, String name) {
        PageHelper.startPage(pageNum, pageSize);
        List<Owner> list = ownerMapper.queryList(name);
        PageInfo<Owner> pageInfo = new PageInfo<>(list);
        return new Et1908Page<>(pageInfo.getPageNum(), pageInfo.getPageSize(),
                list, pageInfo.getTotal(), pageInfo.getPages());
    }

    public void delOwner(Integer id) {
        ownerMapper.delOwner(id);
    }

    public void updateOwner(Owner owner) {
        ownerMapper.updateOwner(owner);
    }

    public Owner queryByNameAndPassword(String name, String password) {
        return ownerMapper.queryByNameAndPassword(name,password);
    }

    public void addRepair(Repair repair) {
        ownerMapper.addRepair(repair);
    }

    public Et1908Page<Owner> queryRepairList(int pageNum, int pageSize, String queryLocation) {
        PageHelper.startPage(pageNum, pageSize);
        List<Owner> list = ownerMapper.queryRepairList(queryLocation);
        PageInfo<Owner> pageInfo = new PageInfo<>(list);
        return new Et1908Page<>(pageInfo.getPageNum(), pageInfo.getPageSize(),
                list, pageInfo.getTotal(), pageInfo.getPages());
    }

    public Map<String,String> queryXname() throws Exception{
        List<Repair> repairs = ownerMapper.queryThree();
        System.out.println(repairs);
        Set<String> set = new HashSet<>();
        TreeMap<String,String> map = new TreeMap<String,String>(new Comparator<String>() {
            public int compare(String obj1, String obj2) {
                // 降序排序
                return obj1.compareTo(obj2);
            }
        });
        for(Repair repair : repairs){
            String time = repair.getPublish_time();
            Integer id = repair.getId();
            Integer cost = repair.getCost();
            if(!set.contains(time)){
                for(Repair repair1 : repairs){
                    if(time.equals(repair1.getPublish_time())&&!id.equals(repair1.getId())){
                        cost+=repair1.getCost();
                    }
                }
                map.put(time,cost.toString());
            }
            set.add(time);
        }

        return map;
    }


    public Map<String,String> query30Xname() {
        List<Repair> repairs = ownerMapper.queryThirty();
        Set<String> set = new HashSet<>();
        TreeMap<String,String> map = new TreeMap<String,String>(new Comparator<String>() {
            public int compare(String obj1, String obj2) {
                // 降序排序
                return obj1.compareTo(obj2);
            }
        });
        for(Repair repair : repairs){
            String time = repair.getPublish_time();
            Integer id = repair.getId();
            Integer cost = repair.getCost();
            if(!set.contains(time)){
                for(Repair repair1 : repairs){
                    if(time.equals(repair1.getPublish_time())&&!id.equals(repair1.getId())){
                        cost+=repair1.getCost();
                    }
                }
                map.put(time,cost.toString());
            }
            set.add(time);
        }
        return map;
    }
}
