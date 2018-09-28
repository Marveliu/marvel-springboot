package com.marveliu.web.dao.entity;

import com.marveliu.common.component.domain.AbstractModel;
import lombok.Data;
import org.hibernate.annotations.Target;

import javax.persistence.*;
import java.util.List;

/**
 * @Author: Marveliu
 * @Date: 2018/9/24 下午4:52
 * @Description:
 **/

@Data
@Entity
public class User extends AbstractModel<Integer> {

    @Id
    @GeneratedValue
    private Integer uid;

    /**
     * 帐号
     */
    @Column(unique = true)
    private String username;

    /**
     * 名称（昵称或者真实姓名，不同系统不同定义）
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 密码盐
     */
    private String salt;

    /**
     * 用户状态
     * 0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 , 1:正常状态,2：用户被锁定.
     */
    private Integer status;

    /**
     * 立即从数据库中进行加载数据;
     * 一个用户具有多个角色
     */
    @Target(SysRole.class)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SysUserRole", joinColumns = {@JoinColumn(name = "uid")}, inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<SysRole> roles;

    @Override
    public Integer getId() {
        return uid;
    }

    /**
     * 密码盐.
     *
     * @return
     */
    public String getCredentialsSalt() {
        return this.username + this.salt;
    }
}
