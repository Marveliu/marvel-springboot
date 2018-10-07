package com.marveliu.web.dao.repository;

import com.marveliu.web.component.dao.BaseDao;
import com.marveliu.web.dao.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @Author: Marveliu
 * @Date: 2018/9/24 下午5:11
 * @Description:
 **/

@Repository
public interface UserRepository extends BaseDao<User, Integer> {

    User findUserByName(String name);

}
