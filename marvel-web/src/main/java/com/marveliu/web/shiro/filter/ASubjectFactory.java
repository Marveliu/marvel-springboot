package com.marveliu.web.shiro.filter;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

/**
 * @Author: Marveliu
 * @Date: 2018/9/29 下午2:14
 * @Description: 对于无状态的TOKEN不创建session 这里都不使用session
 **/

public class ASubjectFactory extends DefaultWebSubjectFactory {

    private final DefaultSessionStorageEvaluator sessionStorageEvaluator;

    public ASubjectFactory(DefaultSessionStorageEvaluator sessionStorageEvaluator) {
        this.sessionStorageEvaluator = sessionStorageEvaluator;
    }

    @Override
    public Subject createSubject(SubjectContext context) {
        // 这里都不创建session
        AuthenticationToken token = context.getAuthenticationToken();
        context.setSessionCreationEnabled(Boolean.FALSE);
        this.sessionStorageEvaluator.setSessionStorageEnabled(Boolean.FALSE);
        return super.createSubject(context);
    }

}
