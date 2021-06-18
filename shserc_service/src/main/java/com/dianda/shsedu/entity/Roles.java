package com.dianda.shsedu.entity;

import com.dianda.common.entity.BaseEntity;

public class Roles extends BaseEntity {

    private String roleName;

   private  String description;

   private  String permissionIds;

    public boolean hasPermission(String permissionId) {
        return permissionIds.indexOf(permissionId) >= 0;
    }
    
}
