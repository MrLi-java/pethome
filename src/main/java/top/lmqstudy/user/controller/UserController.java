package top.lmqstudy.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lmqstudy.basic.util.RedisUtils;
import top.lmqstudy.user.domain.User;
import top.lmqstudy.user.domain.UserAddress;
import top.lmqstudy.user.service.IUserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/21/18:42
 * @Description:
 */
@RestController
public class UserController {
    @Autowired
    private IUserService userService;


    /**
     * @Author Mr.Li
     * @Description 通过前台用户id查找地址
     * @Date 2021/1/21 18:49
     * @Param [request]
     * @return java.util.List<top.lmqstudy.user.domain.UserAddress>
     **/
    @GetMapping("/userAddress")
    public List<UserAddress> loadUserAddress(HttpServletRequest request){
        User user = RedisUtils.INSTANCE.getUser(request, User.class);
        return userService.loadUserAddressByUserId(user.getId());
    }


}
