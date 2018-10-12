package com.marveliu.web.component.page;

import com.marveliu.web.constant.PageCons;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @Author Marveliu
 * @Date 2018/9/13 下午11:26
 **/

@Data
public class PageQuery {

    private static String DEFAULT_SORT = "";

    @Min(value = 1, message = "当前页码不合法")
    private int pageNo = 1;

    @Min(value = 1, message = "每页展示数量不合法")
    @Max(value = 100, message = "每页展示数量不合法")
    private int pageSize = 10;

    //
    private String sorter = "";

    /**
     * 默认排序为最近一次更新时间
     */
    private Sort sort = new Sort(Sort.Direction.DESC, "createTime");

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
     * pageQuery转化成page
     *
     * @return
     */
    public PageRequest getPage() {
        if (!StringUtils.isEmpty(sorter)) {
            String[] sorts = sorter.split(PageCons.SEPARATOR);
            // 校验sort的长度，以及sort[1]是否符合规则
            if (sorts.length != 2 || !(sorts[1].equals(PageCons.ASC) || sorts[1].equals(PageCons.DESC))) {
                // throw new MethodArgumentNotValidException("排序参数不合法");
            }
            if (sorts[1].equals(PageCons.ASC)) {
                sort = new Sort(Sort.Direction.ASC, sorts[0]);
            }
            if (sorts[1].equals(PageCons.DESC)) {
                sort = new Sort(Sort.Direction.DESC, sorts[0]);
            }
        }
        return getSortPage();
    }

    /**
     * 自定义分页
     *
     * @param pageNo
     * @param pageSize
     */
    public PageQuery(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
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

}
