package top.lmqstudy.org.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.lmqstudy.basic.util.AjaxResult;
import top.lmqstudy.basic.util.PageList;
import top.lmqstudy.org.domain.Employee;
import top.lmqstudy.org.query.EmployeeQuery;
import top.lmqstudy.org.service.IEmployeeService;


import java.util.List;

@RestController
@RequestMapping("/employee")
@Api(tags = "员工接口",description = "员工接口详细描述")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    @GetMapping("{id}")
    @ApiOperation(value = "根据id查询员工",notes = "需要前台页面传入id")
    public Employee getById(@PathVariable("id") Long id){
        return employeeService.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "员工添加或修改",notes = "如果有id就是修改否则就是添加")
    public AjaxResult addOrUpdate(@RequestBody Employee employee){
        try {
            if (employee.getId() == null) {
                employeeService.add(employee);
            }else {
                employeeService.update(employee);
            }
            return AjaxResult.me();
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.me().setMsg("系统繁忙！！"+e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "根据id删除员工",notes = "需要前台页面传入id")
    public AjaxResult deleteById(@PathVariable Long id){
        try {
            employeeService.del(id);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMsg("系统繁忙！！"+e.getMessage());
        }
    }

    @PostMapping
    @ApiOperation(value = "分页查询和高级查询",notes = "需要前台页面传入分页的条件和高级查询的条件")
    public PageList<Employee> queryPage(@RequestBody EmployeeQuery query){
        return employeeService.queryPage(query);
    }

    @PatchMapping
    @ApiOperation(value = "批量删除",notes = "前端传入要删除员工id的集合")
    public AjaxResult patchDelete(@RequestBody List<Long> ids){
        System.out.println(ids);
        try {
            employeeService.patchDelete(ids);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMsg(e.getMessage());
        }
    }

}
