package com.marveliu.web.dao.entity;

import com.marveliu.common.component.domain.AbstractModel;
import lombok.Data;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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

    @Column(columnDefinition = "int(11) " +
            "NOT NULL " +
            "COMMENT '接受用户id'")
    private Integer to;


    /***
     * 消息发送
     */
    @Column(columnDefinition = "text " +
            "NOT NULL " +
            "COMMENT '消息内容'")
    private String Log;

    /**
     * 阅读时间
     */
    @Column(updatable = false, columnDefinition = "datetime(3) " +
            "COMMENT '创建时间'")
    private Long readTime;

    /**
     * 消息状态
     */
    @Column(columnDefinition = "int(11) " +
            "NOT NULL " +
            "DEFAULT '0' " +
            "COMMENT '消息状态 是否阅读 0 未读 1 已读'")
    private Integer status;

    public Log() {

    }

    @Override
    public Long getId() {
        return id;
    }

}
