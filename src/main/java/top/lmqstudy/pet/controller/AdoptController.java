package top.lmqstudy.pet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.lmqstudy.basic.util.AjaxResult;
import top.lmqstudy.basic.util.PageList;
import top.lmqstudy.basic.util.RedisUtils;
import top.lmqstudy.org.domain.Employee;
import top.lmqstudy.pet.domain.Adopt;
import top.lmqstudy.pet.query.AdoptQuery;
import top.lmqstudy.pet.service.IAdoptService;
import top.lmqstudy.user.domain.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/20/17:03
 * @Description:
 */
@RestController
@RequestMapping("/adopt")
public class AdoptController {

    @Autowired
    private IAdoptService adoptService;

    /**
     * @Author Mr.Li
     * @Description 发布寻主消息
     * @Date 2021/1/20 17:06
     * @Param [adopt]
     * @return top.lmqstudy.basic.util.AjaxResult
     **/
    @PutMapping
    public AjaxResult publishAdopt(@RequestBody Adopt adopt, HttpServletRequest request){
        try {
            User user = RedisUtils.INSTANCE.getUser(request, User.class);
            adopt.setUser(user);
            adopt.setUser_id(user.getId());
            adoptService.publishAdopt(adopt);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();;
            return AjaxResult.me().setMsg("寻主发布失败！！！");
        }
    }
    @PostMapping
    public PageList<Adopt> pageList(@RequestBody AdoptQuery query ,HttpServletRequest request){
        Employee user = RedisUtils.INSTANCE.getUser(request, Employee.class);
        query.setShop_id(user.getShop_id());
        return adoptService.queryPage(query);
    }
}
