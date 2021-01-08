package top.lmqstudy.org.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lmqstudy.basic.service.impl.BaseServiceImpl;
import top.lmqstudy.org.domain.Department;
import top.lmqstudy.org.mapper.DepartmentMapper;
import top.lmqstudy.org.service.IDepartmentService;

import java.util.List;

@Service
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public void add(Department department) {
        departmentMapper.save(department);
        this.update(department);
    }

    @Override
    public void update(Department department) {
        if(department.getParent() == null || department.getParent().getId() == null){
            department.setDirPath("/"+department.getId());
        }else {
            String dirPath = departmentMapper.loadById(department.getParent().getId()).getDirPath();
            department.setDirPath(dirPath+"/"+department.getId());
        }
        departmentMapper.update(department);
    }



    @Override
    public List<Department> getDeptTree(){
        return departmentMapper.getDeptTree();
    }
}
