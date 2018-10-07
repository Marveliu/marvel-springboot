package com.marveliu.web.component.domain;

import java.io.Serializable;

/**
 * @Author Marveliu
 * @Date 2018/7/18 8:32 PM
 **/

public interface BaseModel<ID extends Serializable> extends Serializable {

    ID getId();

}