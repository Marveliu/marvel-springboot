package com.marveliu.common.component.service.impl;

import com.marveliu.common.constants.Status;
import com.marveliu.common.constants.QueryType;
import com.marveliu.common.component.dao.BaseDao;
import com.marveliu.common.component.domain.AbstractModel;
import com.marveliu.common.component.domain.BaseModel;
import com.marveliu.common.component.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.util.*;

/**
 * @Author Marveliu
 * @Date 2018/7/18 8:43 PM
 * @Description: 基于Jpa进行Crud的封装
 **/
@SuppressWarnings("unchecked")
@Slf4j
public abstract class BaseServiceImpl<T extends BaseModel<ID>, ID extends Serializable> implements BaseService<T, ID> {


    public abstract BaseDao<T, ID> getDAO();

    /**
     * 保存实体
     * todo: 创建者和更新者id走session
     *
     * @param t
     * @return
     */
    @Override
    public T save(T t) {
        if (t instanceof AbstractModel) {
            AbstractModel<T> baseModel = (AbstractModel<T>) t;
            Long current = System.currentTimeMillis();
            if (baseModel.getId() == null) {
                baseModel.setCreateTime(current);
            } else {
                copyNullProperties(this.findById(t.getId()), baseModel);
            }
            baseModel.setOperateTime(current);
        }
        return getDAO().save(t);
    }

    /**
     * 保存多个实体
     *
     * @param entities
     * @return
     */
    @Override
    public Iterable<T> save(Iterable<T> entities) {
        for (T t : entities) {
            if (t instanceof AbstractModel) {
                AbstractModel<T> baseModel = (AbstractModel<T>) t;
                Long current = System.currentTimeMillis();
                if (baseModel.getId() == null) {
                    baseModel.setCreateTime(current);
                } else {
                    copyNullProperties(this.findById(t.getId()), baseModel);
                }
                baseModel.setOperateTime(current);
            }
        }
        return getDAO().saveAll(entities);
    }

    /**
     * 主键删除实体
     *
     * @param id
     */
    @Override
    public void del(ID id) {
        T t = findById(id);
        if (t == null) {
            return;
        }
        getDAO().delete(t);
    }

    /**
     * 删除实体
     *
     * @param t
     */
    @Override
    public void del(T t) {
        getDAO().delete(t);
    }

    /**
     * 根据ID虚删除
     *
     * @param id
     */
    @Override
    public void vdel(ID id) {
        T t = findById(id);
        AbstractModel<T> baseModel = (AbstractModel<T>) t;
        baseModel.setOperateTime(System.currentTimeMillis());
        baseModel.setDel(Status.DEL_YES);
        getDAO().save(t);
    }

    /**
     * 根据实体虚删除
     *
     * @param t
     */
    @Override
    public void vdel(T t) {
        AbstractModel<T> baseModel = (AbstractModel<T>) t;
        baseModel.setOperateTime(System.currentTimeMillis());
        baseModel.setDel(Status.DEL_NO);
        getDAO().save(t);
    }

    /**
     * 通过主键查询
     *
     * @param id
     * @return
     */
    @Override
    public T findById(ID id) {
        return getDAO().findById(id).orElse(null);
    }

    /**
     * 获得所有的数据
     *
     * @return
     */
    @Override
    public List<T> findAll() {
        return getDAO().findAll();
    }

    /**
     * 分页
     *
     * @param pageable
     * @return
     */
    @Override
    public Page<T> findAll(Pageable pageable) {
        return getDAO().findAll(pageable);
    }

    /**
     * 条件查询
     *
     * @param params {"username:like":"test"} 键的格式为字段名:过滤方式,过滤方式见{@code QueryTypeEnum}
     * @return
     */
    @Override
    public List<T> list(final Map<String, Object> params) {
        Specification<T> spec = new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    Object value = entry.getValue();
                    if (value == null || StringUtils.isBlank(value.toString())) {
                        continue;
                    }
                    String key = entry.getKey();
                    String[] arr = key.split(":");
                    Predicate predicate = getPredicate(arr, value, root, cb);
                    list.add(predicate);
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        };
        List<T> list = getDAO().findAll(spec);
        return list;
    }

    /**
     * 分页条件查询
     *
     * @param params   {"username:like":"test"} 键的格式为字段名:过滤方式,过滤方式见{@code QueryTypeEnum}
     * @param pageable 分页信息 new PageRequest(page, size,new Sort(Direction.DESC, "updateTime"))
     * @return
     */
    @Override
    public Page<T> list(final Map<String, Object> params, Pageable pageable) {
        Specification<T> spec = new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    Object value = entry.getValue();
                    if (value == null || StringUtils.isBlank(value.toString())) {
                        continue;
                    }
                    String key = entry.getKey();
                    String[] arr = key.split(":");
                    Predicate predicate = getPredicate(arr, value, root, cb);
                    list.add(predicate);
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        };
        Page<T> page = getDAO().findAll(spec, pageable);
        return page;
    }

    private Predicate getPredicate(String[] arr, Object value,
                                   Root<T> root, CriteriaBuilder cb) {
        if (arr.length == 1) {
            return cb.equal(root.get(arr[0]).as(value.getClass()), value);
        }

        if (QueryType.equal.name().equals(arr[1])) {
            return cb.equal(root.get(arr[0]).as(value.getClass()), value);
        }
        // todo:多类型in支持
        if (QueryType.in.name().equals(arr[1])) {
            if (QueryType.in.name().equals(arr[1])) {
                // todo:Integer 判定
                CriteriaBuilder.In<Object> in = cb.in(root.get(arr[0]));
                try {
                    for (Integer item : (List<Integer>) value) {
                        in.value(item);
                    }
                } catch (Exception e) {
                    log.error("error", e);
                }
                return in;
            }
        }
        if (QueryType.like.name().equals(arr[1])) {
            return cb.like(root.get(arr[0]).as(String.class), String.format("%%%s%%", value));
        }
        if (QueryType.ne.name().equals(arr[1])) {
            return cb.notEqual(root.get(arr[0]).as(value.getClass()), value);
        }
        if (QueryType.lt.name().equals(arr[1])) {
            return getLessThanPredicate(arr, value, root, cb);
        }
        if (QueryType.lte.name().equals(arr[1])) {
            return getLessThanOrEqualToPredicate(arr, value, root, cb);
        }
        if (QueryType.gt.name().equals(arr[1])) {
            return getGreaterThanPredicate(arr, value, root, cb);
        }
        if (QueryType.gte.name().equals(arr[1])) {
            return getGreaterThanOrEqualToPredicate(arr, value, root, cb);
        }
        throw new UnsupportedOperationException(String.format("不支持的查询类型[%s]", arr[1]));
    }

    private Predicate getLessThanPredicate(String[] arr, Object value,
                                           Root<T> root, CriteriaBuilder cb) {
        if (Integer.class == value.getClass()) {
            return cb.lessThan(root.get(arr[0]).as(Integer.class), (int) value);
        }
        if (Long.class == value.getClass()) {
            return cb.lessThan(root.get(arr[0]).as(Long.class), (long) value);
        }
        if (Double.class == value.getClass()) {
            return cb.lessThan(root.get(arr[0]).as(Double.class), (double) value);
        }
        if (Float.class == value.getClass()) {
            return cb.lessThan(root.get(arr[0]).as(Float.class), (float) value);
        }
        // 不建议存date
        if (Date.class == value.getClass()) {
            return cb.lessThan(root.get(arr[0]).as(Date.class), (Date) value);
        }
        if (Date.class == value.getClass()) {
            return cb.lessThan(root.get(arr[0]).as(Date.class), (Date) value);
        }
        return cb.lessThan(root.get(arr[0]).as(String.class), String.valueOf(value));
    }

    private Predicate getLessThanOrEqualToPredicate(String[] arr,
                                                    Object value, Root<T> root, CriteriaBuilder cb) {
        if (Integer.class == value.getClass()) {
            return cb.lessThanOrEqualTo(root.get(arr[0]).as(Integer.class), (int) value);
        }
        if (Long.class == value.getClass()) {
            return cb.lessThanOrEqualTo(root.get(arr[0]).as(Long.class), (long) value);
        }
        if (Double.class == value.getClass()) {
            return cb.lessThanOrEqualTo(root.get(arr[0]).as(Double.class), (double) value);
        }
        if (Float.class == value.getClass()) {
            return cb.lessThanOrEqualTo(root.get(arr[0]).as(Float.class), (float) value);
        }
        // 不建议存date
        if (Date.class == value.getClass()) {
            return cb.lessThanOrEqualTo(root.get(arr[0]).as(Date.class), (Date) value);
        }
        if (Date.class == value.getClass()) {
            return cb.lessThanOrEqualTo(root.get(arr[0]).as(Date.class), (Date) value);
        }
        return cb.lessThanOrEqualTo(root.get(arr[0]).as(String.class), String.valueOf(value));
    }

    private Predicate getGreaterThanPredicate(String[] arr,
                                              Object value, Root<T> root, CriteriaBuilder cb) {
        if (Integer.class == value.getClass()) {
            return cb.greaterThan(root.get(arr[0]).as(Integer.class), (int) value);
        }
        if (Long.class == value.getClass()) {
            return cb.greaterThan(root.get(arr[0]).as(Long.class), (long) value);
        }
        if (Double.class == value.getClass()) {
            return cb.greaterThan(root.get(arr[0]).as(Double.class), (double) value);
        }
        if (Float.class == value.getClass()) {
            return cb.greaterThan(root.get(arr[0]).as(Float.class), (float) value);
        }
        // 不建议存date
        if (Date.class == value.getClass()) {
            return cb.greaterThan(root.get(arr[0]).as(Date.class), (Date) value);
        }
        if (Date.class == value.getClass()) {
            return cb.greaterThan(root.get(arr[0]).as(Date.class), (Date) value);
        }
        return cb.greaterThan(root.get(arr[0]).as(String.class), String.valueOf(value));
    }

    private Predicate getGreaterThanOrEqualToPredicate(String[] arr, Object value,
                                                       Root<T> root, CriteriaBuilder cb) {
        if (Integer.class == value.getClass()) {
            return cb.greaterThanOrEqualTo(root.get(arr[0]).as(Integer.class), (int) value);
        }
        if (Long.class == value.getClass()) {
            return cb.greaterThanOrEqualTo(root.get(arr[0]).as(Long.class), (long) value);
        }
        if (Double.class == value.getClass()) {
            return cb.greaterThanOrEqualTo(root.get(arr[0]).as(Double.class), (double) value);
        }
        if (Float.class == value.getClass()) {
            return cb.greaterThanOrEqualTo(root.get(arr[0]).as(Float.class), (float) value);
        }
        // 不建议存date
        if (Date.class == value.getClass()) {
            return cb.greaterThanOrEqualTo(root.get(arr[0]).as(Date.class), (Date) value);
        }
        if (Date.class == value.getClass()) {
            return cb.greaterThanOrEqualTo(root.get(arr[0]).as(Date.class), (Date) value);
        }
        return cb.lessThanOrEqualTo(root.get(arr[0]).as(String.class), String.valueOf(value));
    }

    /**
     * @param target 目标源数据
     * @return 将目标源中不为空的字段取出
     */
    private static String[] getNoNullProperties(Object target) {
        BeanWrapper srcBean = new BeanWrapperImpl(target);
        PropertyDescriptor[] pds = srcBean.getPropertyDescriptors();
        Set<String> noEmptyName = new HashSet<>();
        for (PropertyDescriptor p : pds) {
            Object value = srcBean.getPropertyValue(p.getName());
            if (value != null) noEmptyName.add(p.getName());
        }
        String[] result = new String[noEmptyName.size()];
        return noEmptyName.toArray(result);
    }

    /**
     * 将目标源中不为空的字段过滤，将数据库中查出的数据源复制到提交的目标源中
     *
     * @param source 用id从数据库中查出来的数据源
     * @param target 提交的实体，目标源
     */
    public static void copyNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNoNullProperties(target));
    }

}
