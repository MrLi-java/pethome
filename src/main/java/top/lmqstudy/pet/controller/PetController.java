package top.lmqstudy.pet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.lmqstudy.basic.util.AjaxResult;
import top.lmqstudy.basic.util.PageList;
import top.lmqstudy.basic.util.RedisUtils;
import top.lmqstudy.org.domain.Employee;
import top.lmqstudy.pet.domain.Pet;
import top.lmqstudy.pet.query.PetQuery;
import top.lmqstudy.pet.service.IPetService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/20/20:43
 * @Description:
 */
@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    private IPetService petService;

    /**
     * @Author Mr.Li
     * @Description 店铺处理结果
     * @Date 2021/1/20 20:48
     * @Param [id, handleResult]
     * @return top.lmqstudy.basic.util.AjaxResult
     **/
    @GetMapping("/{id}/{handleResult}")
    public AjaxResult handleAdopt(@PathVariable("id")Long id,@PathVariable("handleResult")Integer handleResult){
        try {
            petService.handleAdopt(id,handleResult);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMsg("系统错误！！！");
        }
    }

    @PostMapping
    public PageList<Pet> pageList(@RequestBody PetQuery query, HttpServletRequest request){
        Employee user = RedisUtils.INSTANCE.getUser(request, Employee.class);
        query.setShop_id(user.getShop_id());
        return petService.queryPage(query);
    }

    /**
     * @Author Mr.Li
     * @Description 宠物添加和修改
     * @Date 2021/1/20 22:16
     * @Param [pet]
     * @return top.lmqstudy.basic.util.AjaxResult
     **/

    @PutMapping
    public AjaxResult saveAndUpdate(@RequestBody Pet pet,HttpServletRequest request){
        try {
            Employee user = RedisUtils.INSTANCE.getUser(request, Employee.class);
            pet.setShop_id(user.getShop_id());
            petService.saveAndUpdate(pet);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMsg("操作失败！！");
        }
    }

    @DeleteMapping("/{id}")
    public AjaxResult delete(@PathVariable("id")Long id){
        try {
            petService.del(id);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMsg("删除失败！！！");
        }
    }


    /**
     * @Author Mr.Li
     * @Description 宠物批量上架
     * @Date 2021/1/20 22:46
     * @Param [ids]
     * @return top.lmqstudy.basic.util.AjaxResult
     **/
    @PatchMapping("/onsale")
    public AjaxResult onsale(@RequestBody List<Long> ids){
        try {
            petService.onsale(ids);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMsg("上架失败");
        }
    }


    /**
     * @Author Mr.Li
     * @Description 宠物批量下架
     * @Date 2021/1/17 20:37
     * @Param [ids]
     * @return top.lmqstudy.basic.util.AjaxResult
     **/
    @PatchMapping("/offsale")
    public AjaxResult offsale(@RequestBody List<Long> ids){
        try {
            petService.offsale(ids);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMsg("上架失败");
        }
    }

    @GetMapping("/{id}")
    public Pet loadById(@PathVariable("id")Long id){
        return petService.getById(id);
    }


}
