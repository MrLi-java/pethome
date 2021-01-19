package top.lmqstudy.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.lmqstudy.basic.util.AjaxResult;
import top.lmqstudy.basic.util.RedisUtils;
import top.lmqstudy.product.domain.Shopcart;
import top.lmqstudy.product.service.IShopcartService;
import top.lmqstudy.user.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/18/20:38
 * @Description:
 */
@RestController
@RequestMapping("/shopcart")
public class ShopcartController {
    @Autowired
    private IShopcartService shopcartService;

    /**
     * @Author Mr.Li
     * @Description 加入购物车
     * @Date 2021/1/18 20:40
     * @Param [shopcart]
     * @return top.lmqstudy.basic.util.AjaxResult
     **/
    @PostMapping
    public AjaxResult save(@RequestBody Shopcart shopcart, HttpServletRequest request){
        try {
            shopcartService.addShopcart(shopcart,request);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMsg("操作失败！！");
        }
    }

    @GetMapping
    public List<Shopcart> getMyShopcarts(HttpServletRequest request){
        //获取当前登录用户
        User user = RedisUtils.INSTANCE.getUser(request, User.class);
        //根据当前登录用户id，查询它的购物车数据
        return shopcartService.getMyShopcarts(user.getId());
    }

    @PutMapping("/counts")
    public Integer getCounts(HttpServletRequest request){
        //获取当前登录用户
        User user = RedisUtils.INSTANCE.getUser(request, User.class);

        return shopcartService.getCounts(user.getId());
    }
}
