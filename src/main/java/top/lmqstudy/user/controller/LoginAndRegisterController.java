package top.lmqstudy.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.lmqstudy.basic.util.AjaxResult;
import top.lmqstudy.org.domain.Shop;
import top.lmqstudy.org.service.IShopService;
import top.lmqstudy.user.domain.dto.UserDto;
import top.lmqstudy.user.service.IUserService;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/11/12:43
 * @Description: 用户注册和登录
 */
@RestController
@RequestMapping("/lr")
public class LoginAndRegisterController {
    @Autowired
    private IUserService userService;

    @Autowired
    private IShopService shopService;

    /**
     * @Author Mr.Li
     * @Description 判断手机号是否存在
     * @Date 2021/1/11 15:01
     * @Param [type]  判断是登录还是注册
     * @Param [phone]  前台传入的手机号
     * @return top.lmqstudy.basic.util.AjaxResult
     **/
    @GetMapping("/validatePhone/{type}/{phone}")
    public AjaxResult validPhone(@PathVariable("type")String type,@PathVariable("phone")String phone){
        return userService.validPhone(type, phone);
    }

    /**
     * @Author Mr.Li
     * @Description 手机验证码发送
     * @Date 2021/1/11 19:14
     * @Param [type, phone]
     * @return top.lmqstudy.basic.util.AjaxResult
     **/
    @GetMapping("/sendMobileCode/{type}/{phone}")
    public AjaxResult sendMobileCode(@PathVariable("type")String type,@PathVariable("phone")String phone){
        return userService.sendMobileCode(type,phone);
    }

    /**
     * @Author Mr.Li
     * @Description 注册表单提交
     * @Date 2021/1/11 20:43
     * @Param [userDto]
     * @return top.lmqstudy.basic.util.AjaxResult
     **/
    @PutMapping("/phoneReg")
    public AjaxResult phoneReg(@RequestBody UserDto userDto){
        return userService.phoneReg(userDto);
    }


    /**
     * @Author Mr.Li
     * @Description 商家入驻
     * @Date 2021/1/12 20:29
     * @Param [shop]
     * @return top.lmqstudy.basic.util.AjaxResult
     **/
    @PostMapping("/settledIn")
    public AjaxResult settledIn(@RequestBody Shop shop){
        return shopService.settledIn(shop);
    }
}
