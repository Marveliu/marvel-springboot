package com.marveliu.web.service.impl;

import com.marveliu.web.component.dao.BaseDao;
import com.marveliu.web.component.service.impl.BaseServiceImpl;
import com.marveliu.web.dao.entity.Resource;
import com.marveliu.web.dao.repository.ResourceRepository;
import com.marveliu.web.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: Marveliu
 * @Date: 2018/10/10 上午9:27
 * @Description:
 **/

@Slf4j
@Service
@CacheConfig(cacheNames = "resource")
public class ResourceServiceImpl extends BaseServiceImpl<Resource, Integer> implements ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    @Override
    public BaseDao<Resource, Integer> getDAO() {
        return resourceRepository;
    }

    /**
     * 获得所有的数据
     *
     * @return
     */
    // @Cacheable(cacheNames = "resource.service.all")
    @Override
    public List<Resource> findAll() {
        return super.findAll();
    }

}
