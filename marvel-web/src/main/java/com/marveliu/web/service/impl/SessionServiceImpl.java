package com.marveliu.web.service.impl;

import com.marveliu.web.bean.UserOnline;
import com.marveliu.web.dao.entity.User;
import com.marveliu.web.service.SessionService;
import com.marveliu.web.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author: Marveliu
 * @Date: 2018/9/26 上午9:44
 * @Description: session相关信息统计
 **/

@Slf4j
@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionDAO sessionDAO;

    /**
     * 在线用户
     *
     * @return
     */
    @Override
    public List<UserOnline> list() {
        List<UserOnline> list = new ArrayList<>();
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        for (Session session : sessions) {
            UserOnline userOnline = new UserOnline();
            User user = new User();
            SimplePrincipalCollection principalCollection = new SimplePrincipalCollection();
            if (session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY) == null) {
                continue;
            } else {
                principalCollection = (SimplePrincipalCollection) session
                        .getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
                user = (User) principalCollection.getPrimaryPrincipal();
                userOnline.setUsername(user.getUsername());
                userOnline.setUserId(user.getId().toString());
            }
            userOnline.setId(session.getId().toString());
            userOnline.setHost(session.getHost());
            userOnline.setStartTimestamp(DateUtil.d2TS(session.getStartTimestamp()));
            userOnline.setLastAccessTime(DateUtil.d2TS(session.getLastAccessTime()));
            Long timeout = session.getTimeout();
            if (timeout == 0L) {
                userOnline.setStatus("离线");
            } else {
                userOnline.setStatus("在线");
            }
            userOnline.setTimeout(timeout);
            list.add(userOnline);
        }
        return list;
    }

    /**
     * 强制下线
     *
     * @param sessionId
     * @return
     */
    @Override
    public boolean forceLogout(String sessionId) {
        return false;
    }
}
