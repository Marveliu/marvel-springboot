package com.marveliu.web.domain.vo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @Author: Marveliu
 * @Date: 2018/9/26 上午9:47
 * @Description:
 **/

@Data
public class UserOnlineVo implements Serializable {

    private static final long serialVersionUID = 3828664348416633856L;
    /**
     * session id
     */
    private String id;
    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 用户主机地址
     */
    private String host;

    /**
     * 用户登录时系统IP
     */
    private String systemHost;

    /**
     * 状态
     */
    private String status;

    /**
     * session创建时间
     */
    private Long startTimestamp;

    /**
     * session最后访问时间
     */
    private Long lastAccessTime;

    /**
     * 超时时间
     */
    private Long timeout;
}
