package top.lmqstudy.basic.mapper;

import org.apache.ibatis.annotations.Param;
import top.lmqstudy.basic.query.BaseQuery;
import java.util.List;

public interface BaseMapper<T> {

    void save(T t);

    /**
     * 删除数据
     * @param id
     */
    void remove(Long id);

    /**
     * 修改数据
     * @param d
     */
    void update(T t);

    /**
     * 通过id查找数据
     * @param id
     * @return
     */
    T loadById(Long id );

    /**
     * 查询所有数据
     * @return
     */
    List<T> loadAll();

    /**
     * 查询总条数
     * @param query
     * @return
     */
    Long queryCount(BaseQuery query);

    /**
     * 查询分页数据
     * @param query
     * @return
     */
    List<T> queryData(BaseQuery query);


    /**
     * 批量删除
     * @param ids
     */
    void patchDelete(@Param("ids") List<Long> ids);

}
