package com.marveliu.web.domain.mapper;

import com.marveliu.web.dao.entity.User;
import com.marveliu.web.domain.param.RegUserParam;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @Author: Marveliu
 * @Date: 2018/10/10 下午3:39
 * @Description:
 **/

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(source = "salt", target = "salt")
    })
    User from(RegUserParam regUserParam, String salt);
}
