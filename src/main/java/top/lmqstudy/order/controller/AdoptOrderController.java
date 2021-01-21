package top.lmqstudy.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.lmqstudy.basic.util.AjaxResult;
import top.lmqstudy.basic.util.RedisUtils;
import top.lmqstudy.order.domain.AdoptOrder;
import top.lmqstudy.order.service.IAdoptOrderService;
import top.lmqstudy.pet.domain.Adopt;
import top.lmqstudy.user.domain.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/21/19:44
 * @Description:
 */
@RestController
public class AdoptOrderController {
    @Autowired
    private IAdoptOrderService adoptOrderService;

    /**
     * @Author Mr.Li
     * @Description 前台用户领养宠物下单
     * @Date 2021/1/21 19:48
     * @Param [adoptOrder]
     * @return top.lmqstudy.basic.util.AjaxResult
     **/
    @PutMapping("/adoptOrder")
    public AjaxResult orderAdopt(@RequestBody AdoptOrder adoptOrder, HttpServletRequest request){
        try {
            User user = RedisUtils.INSTANCE.getUser(request, User.class);
            adoptOrder.setUser(user);
            adoptOrderService.orderAdopt(adoptOrder);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMsg("下单失败，让我去干了那该死的程序员！！");
        }
    }
}
