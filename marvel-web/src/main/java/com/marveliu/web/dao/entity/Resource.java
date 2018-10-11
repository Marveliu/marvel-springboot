package com.marveliu.web.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marveliu.web.component.domain.AbstractModel;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Target;

import javax.persistence.*;
import java.util.List;

/**
 * @Author: Marveliu
 * @Date: 2018/9/24 下午4:57
 * @Description:
 **/

@Data
@Entity
@ToString(exclude = {"roles"})
public class Resource extends AbstractModel<Integer> {

    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 编码
     */
    private String code;

    /**
     * 访问方式
     */
    @Column(columnDefinition = "varchar(10) comment '访问方式 GET POST PUT DELETE PATCH'")
    private String method;


    /**
     * 资源路径
     */
    @Column(columnDefinition = "varchar(100) comment '资源路径'")
    private String url;

    /**
     * 种类
     */
    @Column(columnDefinition = "smallint(4) comment '类型 1:菜单menu 2:资源element(rest-api) 3:资源分类'")
    private Integer type;

    /**
     * 父编号
     */
    private Long parentId;


    /**
     * 能否使用
     */
    private Boolean available = Boolean.FALSE;


    @JsonIgnore
    @Target(Role.class)
    @ManyToMany(mappedBy = "resources")
    private List<Role> roles;

    public String getUrlWithMethod() {
        return url + "==" + method;
    }

    public String getRolesName() {
        StringBuilder rs = new StringBuilder();
        for (Role role : getRoles()) {
            rs.append(role.getId() + ",");
        }
        return rs.toString();
    }
}
