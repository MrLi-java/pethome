package top.lmqstudy.org.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lmqstudy.org.domain.Employee;
import top.lmqstudy.org.service.IEmployeeService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/14/20:29
 * @Description:
 */
@RestController
@RequestMapping("/util")
public class UtilController {

    @Autowired
    private IEmployeeService employeeService;

    @GetMapping("/employee")
    @ApiOperation(value = "查询所有员工",notes = "不需要任何参数")
    public List<Employee> getAll(){
        return employeeService.getAll();
    }


}
