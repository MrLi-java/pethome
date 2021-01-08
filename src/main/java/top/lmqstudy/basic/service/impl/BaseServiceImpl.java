package top.lmqstudy.basic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import top.lmqstudy.basic.mapper.BaseMapper;
import top.lmqstudy.basic.service.IBaseService;
import top.lmqstudy.basic.util.PageList;
import top.lmqstudy.basic.query.BaseQuery;
import top.lmqstudy.org.domain.Department;

import java.util.List;

@Transactional(readOnly = true,propagation = Propagation.SUPPORTS )
public class BaseServiceImpl<T> implements IBaseService<T> {
    @Autowired
    private BaseMapper<T> mapper;



    @Override
    @Transactional
    public void add(T t) {
        mapper.save(t);
    }

    @Override
    @Transactional
    public void del(Long id) {
        mapper.remove(id);
    }

    @Override
    @Transactional
    public void update(T t) {
        mapper.update(t);
    }

    @Override
    public T getById(Long id) {
        return mapper.loadById(id);
    }

    @Override
    public List<T> getAll() {
        return mapper.loadAll();
    }

    @Override
    public PageList<T> queryPage(BaseQuery query) {
        Long count = mapper.queryCount(query);
        List<T> list = mapper.queryData(query);
        return new PageList<>(count,list);
    }

    @Override
    @Transactional
    public void patchDelete(List<Long> ids) {
        mapper.patchDelete(ids);
    }


}
