package com.marveliu.common.common.page;

import com.marveliu.common.constants.Page;
import com.marveliu.common.exceptions.ParamException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.Min;

/**
 * @Author Marveliu
 * @Date 2018/9/13 下午11:26
 **/

public class PageQuery {


    @Min(value = 1, message = "当前页码不合法")
    private int pageNo = 1;

    @Min(value = 1, message = "每页展示数量不合法")
    private int pageSize = 10;

    private String sorter = "";

    /**
     * 默认排序为最近一次更新时间
     */
    private Sort sort = new Sort(Sort.Direction.DESC, "operateTime");


    public PageQuery(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }


    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }


    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public String getSorter() {
        return sorter;
    }

    public void setSorter(String sorter) {
        this.sorter = sorter;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


    /**
     * 计算页面偏移
     *
     * @return
     */
    public int getOffset() {
        return (pageNo - 1) * pageSize;
    }

    /**
     * 转化成page
     *
     * @return
     */
    public PageRequest getSortPage() {
        return PageRequest.of(this.getPageNo() - 1, this.pageSize, sort);
    }

    /**
     * 根据sort转化成page
     *
     * @param sort
     * @return
     */
    public PageRequest getPage(Sort sort) {
        this.sort = sort;
        return PageRequest.of(this.getPageNo() - 1, this.pageSize);
    }

    /**
     * pageQuery转化成page
     *
     * @return
     */
    public PageRequest getPage() {
        // 校验sort是否符合规则
        if (!StringUtils.isEmpty(sorter)) {
            String[] sorts = sorter.split(Page.SEPARATOR);
            // 校验sort的长度，以及sort[1]是否符合规则
            if (sorts.length != 2 || !(sorts[1].equals(Page.ASC) || sorts[1].equals(Page.DESC))) {
                throw new ParamException("排序参数不合法");
            }
            if (sorts[1].equals(Page.ASC)) {
                sort = new Sort(Sort.Direction.ASC, sorts[0]);
            }
            if (sorts[1].equals(Page.DESC)) {
                sort = new Sort(Sort.Direction.DESC, sorts[0]);
            }
        }
        return getSortPage();
    }


    @Override
    public String toString() {
        return "PageQuery{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", sorter='" + sorter + '\'' +
                ", sort=" + sort +
                '}';
    }
}
