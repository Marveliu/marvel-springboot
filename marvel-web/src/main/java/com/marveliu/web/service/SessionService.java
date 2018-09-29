package com.marveliu.web.service;

import com.marveliu.web.domain.vo.UserOnline;

import java.util.List;

/**
 * @Author: Marveliu
 * @Date: 2018/9/26 上午9:43
 * @Description:
 **/

public interface SessionService {

    /**
     * 在线用户
     *
     * @return
     */
    List<UserOnline> list();

    /**
     * 强制下线
     *
     * @param sessionId
     * @return
     */
    boolean forceLogout(String sessionId);
}
