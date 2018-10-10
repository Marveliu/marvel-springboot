package com.marveliu.web.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marveliu.web.component.domain.AbstractModel;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @Author: Marveliu
 * @Date: 2018/9/24 下午4:54
 * @Description:
 **/

@Data
@Entity
public class Role extends AbstractModel<Integer> {

    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 角色名称
     */
    private String name;

    private String code;

    /**
     * 角色描述,UI界面显示使用
     */
    private String description;

    /**
     * 是否可用,如果不可用将不会添加给用户
     */
    private Boolean available = Boolean.FALSE;

    /**
     * 角色 -- 权限关系：多对多关系;
     */
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "RoleResource", joinColumns = {@JoinColumn(name = "roleId")}, inverseJoinColumns = {@JoinColumn(name = "resourceId")})
    private List<Resource> resources;
}
