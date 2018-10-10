package com.marveliu.web.service.impl;

import com.marveliu.web.component.dao.BaseDao;
import com.marveliu.web.component.service.impl.BaseServiceImpl;
import com.marveliu.web.dao.entity.SysLog;
import com.marveliu.web.dao.repository.LogRepository;
import com.marveliu.web.service.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Marveliu
 * @Date 2018/9/17 下午10:52
 * @Description: 日志service
 **/

@Slf4j
@Service
public class SysLogServiceImpl extends BaseServiceImpl<SysLog, Long> implements SysLogService {


    @Autowired
    private LogRepository logDao;

    @Override
    public BaseDao<SysLog, Long> getDAO() {
        return logDao;
    }


}
