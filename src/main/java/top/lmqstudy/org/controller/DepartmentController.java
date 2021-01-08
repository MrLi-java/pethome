package top.lmqstudy.org.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.lmqstudy.basic.util.AjaxResult;
import top.lmqstudy.basic.util.PageList;
import top.lmqstudy.org.domain.Department;
import top.lmqstudy.org.query.DepartmentQuery;
import top.lmqstudy.org.service.IDepartmentService;

import java.util.List;

@RestController
@RequestMapping("/dept")
@Api(tags = "部门接口",description = "部门接口详细描述")
public class DepartmentController {
    @Autowired
    private IDepartmentService departmentService;

    @GetMapping("{id}")
    @ApiOperation(value = "根据id查询部门",notes = "需要前台页面传入id")
    public Department getById(@PathVariable("id") Long id){
        return departmentService.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "部门添加或修改",notes = "如果有id就是修改否则就是添加")
    public AjaxResult addOrUpdate(@RequestBody Department department){
        try {
            if (department.getId() == null) {
                departmentService.add(department);
            }else {
                departmentService.update(department);
            }
            return AjaxResult.me();
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.me().setMsg("系统繁忙！！"+e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "根据id删除部门",notes = "需要前台页面传入id")
    public AjaxResult deleteById(@PathVariable Long id){
        try {
            departmentService.del(id);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMsg("系统繁忙！！"+e.getMessage());
        }
    }

    @GetMapping
    @ApiOperation(value = "查询所有部门",notes = "不需要任何参数")
    public List<Department> getAll(){
        return departmentService.getAll();
    }

    @PostMapping
    @ApiOperation(value = "分页查询和高级查询",notes = "需要前台页面传入分页的条件和高级查询的条件")
    public PageList<Department> queryPage(@RequestBody DepartmentQuery query){
        return departmentService.queryPage(query);
    }

    @PatchMapping
    @ApiOperation(value = "批量删除",notes = "前端传入要删除部门id的集合")
    public AjaxResult patchDelete(@RequestBody List<Long> ids){
        System.out.println(ids);
        try {
            departmentService.patchDelete(ids);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMsg(e.getMessage());
        }
    }

    @GetMapping("/deptTree")
    @ApiOperation(value = "部门树形结构",notes = "")
    public List<Department> getDeptTree(){
        return  departmentService.getDeptTree();
    }
}
