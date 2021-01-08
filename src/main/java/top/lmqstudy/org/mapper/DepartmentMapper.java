package top.lmqstudy.org.mapper;


import org.apache.ibatis.annotations.Param;
import top.lmqstudy.basic.mapper.BaseMapper;
import top.lmqstudy.org.domain.Department;


import java.util.List;

public interface DepartmentMapper extends BaseMapper<Department> {


    /**
     * 得到部门树形结构
     * @return
     */
    List<Department> getDeptTree();
}
