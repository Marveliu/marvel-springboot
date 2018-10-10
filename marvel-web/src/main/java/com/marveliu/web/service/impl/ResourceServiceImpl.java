package com.marveliu.web.service.impl;

import com.marveliu.web.component.dao.BaseDao;
import com.marveliu.web.component.service.impl.BaseServiceImpl;
import com.marveliu.web.dao.entity.Resource;
import com.marveliu.web.dao.repository.ResourceRepository;
import com.marveliu.web.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Marveliu
 * @Date: 2018/10/10 上午9:27
 * @Description:
 **/

@Slf4j
@Service
public class ResourceServiceImpl extends BaseServiceImpl<Resource, Integer> implements ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    @Override
    public BaseDao<Resource, Integer> getDAO() {
        return resourceRepository;
    }
}
