package com.etoak.mapper;


import com.etoak.bean.Et1908Page;
import com.etoak.bean.Owner;
import com.etoak.bean.Repair;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OwnerMapper {
    void addOwner(Owner owner);

    Owner queryByName(@Param("name") String name);

    List<Owner> queryList(@Param("name") String name);

    void delOwner(@Param("id") Integer id);

    void updateOwner(Owner owner);

    Owner queryByNameAndPassword(@Param("name") String name, @Param("password") String password);

    void addRepair(Repair repair);

    List<Owner> queryRepairList(@Param("location") String queryLocation);

    List<Repair> queryThree();

    List<Repair> queryThirty();
}
