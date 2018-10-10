package com.marveliu.web.dao.entity;

import com.marveliu.web.component.domain.AbstractModel;
import lombok.Data;

import javax.persistence.*;

/**
 * @Author Marveliu
 * @Date 2018/9/17 下午10:41
 **/

@Data
@Entity
public class SysLog extends AbstractModel<Long> {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "tinyint not null DEFAULT 0 comment '消息类型'")
    private Integer type;

    @Column(columnDefinition = "varchar(100) not null comment '消息标签'")
    private String tag;

    @Column(columnDefinition = "varchar(200) not null comment '消息来源类'")
    private String src;

    @Column(columnDefinition = "text not null comment '消息内容'")
    private String msg;


    public SysLog() {

    }

    @Override
    public Long getId() {
        return id;
    }

}
