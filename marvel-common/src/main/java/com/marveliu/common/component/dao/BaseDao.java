package com.marveliu.common.component.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @Author Marveliu
 * @Date 2018/7/18 8:25 PM
 **/

@NoRepositoryBean
public interface BaseDao<T, ID extends Serializable> extends QuerydslPredicateExecutor<T>, JpaSpecificationExecutor<T>, JpaRepository<T, ID> {

}
