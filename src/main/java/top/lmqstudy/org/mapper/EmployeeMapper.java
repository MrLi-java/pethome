package top.lmqstudy.org.mapper;



import top.lmqstudy.basic.mapper.BaseMapper;
import top.lmqstudy.org.domain.Employee;



public interface EmployeeMapper extends BaseMapper<Employee> {


    Employee findByAccount(String username);
}
