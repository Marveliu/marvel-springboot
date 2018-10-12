package com.marveliu.web.component.domain;

import com.marveliu.web.constant.CommonEnum;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @Author Marveliu
 * @Date 2018/7/18 8:33 PM
 **/

@MappedSuperclass
public abstract class AbstractModel<ID extends Serializable> implements BaseModel<ID> {

    private static final long serialVersionUID = 1195969732659409799L;

    @Column(columnDefinition = "int default 0")
    private int creator = 0;

    // todo:createTime应该存储long
    @Column(updatable = false, columnDefinition = "TIMESTAMP " +
            "NOT NULL " +
            "DEFAULT CURRENT_TIMESTAMP " +
            "COMMENT '创建时间'")
    private Long createTime;

    @Column(columnDefinition = "int default 0")
    private int updator = 0;

    // @Column(columnDefinition = "COMMENT '最近修改时间'")
    private Long operateTime;

    @Column(columnDefinition = "varchar(200) " +
            "COMMENT '备注'")
    private String remark;

    @Column(name = "del", columnDefinition = "tinyint default 0 comment '标记删除字段 0未删除 1已删除'")
    private int del = CommonEnum.DEL_NO.getCode();

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public int getUpdator() {
        return updator;
    }

    public void setUpdator(int updator) {
        this.updator = updator;
    }

    public Long getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Long operateTime) {
        this.operateTime = operateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getDel() {
        return del;
    }

    public void setDel(int del) {
        this.del = del;
    }
}

