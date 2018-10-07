package com.marveliu.web.shiro.rule;

import com.marveliu.web.util.JwtUtil;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Set;

/**
 * @Author: Marveliu
 * @Date: 2018/9/29 下午2:17
 * @Description:
 **/

@Data
public class RolePermRule implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 资源URL
     */
    private String url;

    /**
     * 访问资源所需要的角色列表，多个列表用逗号间隔
     */
    private String needRoles;

    public StringBuilder toFilterChain() {
        if (null == this.url || this.url.isEmpty()) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        Set<String> setRole = JwtUtil.split(this.getNeedRoles());
        // 约定若role_anon角色拥有此uri资源的权限,则此uri资源直接访问不需要认证和权限
        if (!StringUtils.isEmpty(this.getNeedRoles()) && setRole.contains("role_anon")) {
            stringBuilder.append("anon");
        }
        //  其他自定义资源uri需通过jwt认证和角色认证
        if (!StringUtils.isEmpty(this.getNeedRoles()) && !setRole.contains("role_anon")) {
            stringBuilder.append("jwt" + "[" + this.getNeedRoles() + "]");
        }
        return stringBuilder.length() > 0 ? stringBuilder : null;
    }
}
