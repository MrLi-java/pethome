package top.lmqstudy.org.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.lmqstudy.basic.service.impl.BaseServiceImpl;
import top.lmqstudy.org.domain.Employee;
import top.lmqstudy.org.service.IEmployeeService;



@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements IEmployeeService {


}
