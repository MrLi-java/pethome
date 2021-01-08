package top.lmqstudy.basic.service;

import top.lmqstudy.basic.util.PageList;
import top.lmqstudy.basic.query.BaseQuery;
import top.lmqstudy.org.domain.Department;

import java.util.List;

public interface IBaseService<T> {
    /**
     * 添加数据
     * @param
     */
    void add(T t);

    /**
     * 删除数据
     * @param id
     */
    void del(Long id);

    /**
     * 修改数据
     * @param d
     * @return
     */
    void update(T t);

    /**
     * 通过id查找数据
     * @param id
     * @return
     */
    T getById(Long id );

    /**
     * 查询所有数据
     * @return
     */
    List<T> getAll();

    /**
     * 查询分页数据
     * @param query
     * @return
     */
    PageList<T> queryPage(BaseQuery query);



    /**
     * 批量删除
     * @param ids
     */
    void patchDelete(List<Long> ids);

}
