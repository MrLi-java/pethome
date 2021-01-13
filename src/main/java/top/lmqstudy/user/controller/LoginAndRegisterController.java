package top.lmqstudy.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.lmqstudy.basic.util.AjaxResult;
import top.lmqstudy.org.domain.Shop;
import top.lmqstudy.org.service.IShopService;
import top.lmqstudy.user.domain.dto.UserDto;
import top.lmqstudy.user.service.IUserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
        try {
            return shopService.settledIn(shop);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMsg("入驻失败，我们正在殴打程序猿！"+e.getMessage());
        }
    }


    /**
     * @Author Mr.Li
     * @Description 商家激活
     * @Date 2021/1/13 10:34
     * @Param [id]
     * @return void
     **/
    @GetMapping("/shopRegActive/{id}")
    public void shopRegActive(@PathVariable("id")Long id, HttpServletResponse response) throws IOException {
        shopService.shopRegActive(id);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write("<h1>Congratulations on your admission to Pet Home!!!!!!</h1>");
    }
}
