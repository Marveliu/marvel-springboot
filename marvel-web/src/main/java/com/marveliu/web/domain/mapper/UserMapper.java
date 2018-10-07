package com.marveliu.web.domain.mapper;

import com.marveliu.web.dao.entity.User;
import com.marveliu.web.domain.bo.AuthUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

/**
 * @Author: Marveliu
 * @Date: 2018/10/7 下午7:59
 * @Description:
 **/

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({})
    public User from(AuthUser authUser);


}
