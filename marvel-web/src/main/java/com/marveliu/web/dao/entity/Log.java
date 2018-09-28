package com.marveliu.web.dao.entity;

import com.marveliu.common.component.domain.AbstractModel;
import lombok.Data;

import javax.persistence.*;

/**
 * @Author Marveliu
 * @Date 2018/9/17 下午10:41
 **/

@Data
@Entity
public class Log extends AbstractModel<Long> {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "int(11) " +
            "NOT NULL " +
            "DEFAULT 0 " +
            "COMMENT '消息类型'")
    private Integer type;

    @Column(columnDefinition = "varchar(100) " +
            "NOT NULL " +
            "COMMENT '消息标签'")
    private String tag;

    @Column(columnDefinition = "varchar(200) " +
            "NOT NULL " +
            "COMMENT '消息来源类'")
    private String src;

    /***
     * 消息发送
     */
    @Column(columnDefinition = "text " +
            "NOT NULL " +
            "COMMENT '消息内容'")
    private String Log;


    public Log() {

    }

    @Override
    public Long getId() {
        return id;
    }

}
