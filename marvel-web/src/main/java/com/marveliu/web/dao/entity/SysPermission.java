package com.marveliu.web.dao.entity;

import com.marveliu.web.component.domain.AbstractModel;
import lombok.Data;
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
public class SysPermission extends AbstractModel<Integer> {

    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 资源类型，[menu|button]
     */
    @Column(columnDefinition = "enum('menu','button')")
    private String resourceType;

    /**
     * 资源路径
     */
    private String url;

    /**
     * 权限字符串
     * menu例子：role:*，
     * button例子：role:create,role:update,role:delete,role:view
     */
    private String permission;

    /**
     * 父编号
     */
    private Long parentId;

    /**
     * 父编号列表
     */
    private String parentIds;

    /**
     * 能否使用
     */
    private Boolean available = Boolean.FALSE;

    // /**
    //  * 关联角色
    //  */
    @ManyToMany
    @JoinTable(name = "SysRolePermission", joinColumns = {@JoinColumn(name = "permissionId")}, inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<SysRole> roles;
}
