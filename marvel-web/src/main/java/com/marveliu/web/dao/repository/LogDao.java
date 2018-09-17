package com.marveliu.web.dao.repository;

import com.marveliu.common.component.dao.BaseDao;
import com.marveliu.web.dao.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @Author Marveliu
 * @Date 2018/9/17 下午10:55
 **/

@Repository
public interface LogDao extends BaseDao<Log, Long> {
}
