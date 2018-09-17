package com.marveliu.web.service.impl;

import com.marveliu.common.component.dao.BaseDao;
import com.marveliu.common.component.service.impl.BaseServiceImpl;
import com.marveliu.web.dao.entity.Log;
import com.marveliu.web.dao.repository.LogDao;
import com.marveliu.web.service.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Marveliu
 * @Date 2018/9/17 下午10:52
 * @Description: 日志service
 **/

@Service
public class LogServiceImpl extends BaseServiceImpl<Log, Long> implements LogService {

    private static final Logger logger = LoggerFactory.getLogger(LogServiceImpl.class);

    @Autowired
    private LogDao logDao;

    @Override
    public BaseDao<Log, Long> getDAO() {
        return logDao;
    }


}
