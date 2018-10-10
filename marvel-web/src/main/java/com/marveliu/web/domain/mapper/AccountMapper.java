package com.marveliu.web.domain.mapper;

import com.marveliu.web.dao.entity.User;
import com.marveliu.web.domain.vo.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @Author: Marveliu
 * @Date: 2018/10/7 下午7:59
 * @Description:
 **/

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mappings({
            @Mapping(source = "user.uid", target = "appId")
    })
    public Account from(User user);


}
