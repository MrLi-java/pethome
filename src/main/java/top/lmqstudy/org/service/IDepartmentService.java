package top.lmqstudy.org.service;


import top.lmqstudy.basic.service.IBaseService;
import top.lmqstudy.org.domain.Department;


import java.util.List;

public interface IDepartmentService extends IBaseService<Department> {



    /**
     * 得到部门树形结构
     * @return
     */
    List<Department> getDeptTree();
}

