package top.lmqstudy.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.lmqstudy.basic.util.AjaxResult;
import top.lmqstudy.org.domain.Employee;
import top.lmqstudy.org.domain.Shop;
import top.lmqstudy.org.service.IShopService;
import top.lmqstudy.user.domain.dto.AccessTokenDto;
import top.lmqstudy.user.domain.dto.UserDto;
import top.lmqstudy.user.domain.dto.UserinfoDto;
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
     * @return top.lmqstudy.basic.util.AjaxResult
     * @Author Mr.Li
     * @Description 判断手机号是否存在
     * @Date 2021/1/11 15:01
     * @Param [type]  判断是登录还是注册
     * @Param [phone]  前台传入的手机号
     **/
    @GetMapping("/validatePhone/{type}/{phone}")
    public AjaxResult validPhone(@PathVariable("type") String type, @PathVariable("phone") String phone) {
        return userService.validPhone(type, phone);
    }

    /**
     * @return top.lmqstudy.basic.util.AjaxResult
     * @Author Mr.Li
     * @Description 手机验证码发送
     * @Date 2021/1/11 19:14
     * @Param [type, phone]
     **/
    @GetMapping("/sendMobileCode/{type}/{phone}")
    public AjaxResult sendMobileCode(@PathVariable("type") String type, @PathVariable("phone") String phone) {
        try {
            return userService.sendMobileCode(type, phone);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMsg(e.getMessage());
        }
    }

    /**
     * @return top.lmqstudy.basic.util.AjaxResult
     * @Author Mr.Li
     * @Description 注册表单提交
     * @Date 2021/1/11 20:43
     * @Param [userDto]
     **/
    @PutMapping("/phoneReg")
    public AjaxResult phoneReg(@RequestBody UserDto userDto) {
        return userService.phoneReg(userDto);
    }


    /**
     * @return top.lmqstudy.basic.util.AjaxResult
     * @Author Mr.Li
     * @Description 商家入驻
     * @Date 2021/1/12 20:29
     * @Param [shop]
     **/
    @PostMapping("/settledIn")
    public AjaxResult settledIn(@RequestBody Shop shop) {
        try {
            return shopService.settledIn(shop);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMsg("入驻失败，我们正在殴打程序猿！" + e.getMessage());
        }
    }


    /**
     * @return void
     * @Author Mr.Li
     * @Description 商家激活
     * @Date 2021/1/13 10:34
     * @Param [id]
     **/
    @GetMapping("/shopRegActive/{id}")
    public void shopRegActive(@PathVariable("id") Long id, HttpServletResponse response) throws IOException {
        shopService.shopRegActive(id);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write("<h1>Congratulations on your admission to Pet Home!!!!!!</h1>");
    }

    /**
     * @return top.lmqstudy.basic.util.AjaxResult
     * @Author Mr.Li
     * @Description 前台用户账户登录
     * @Date 2021/1/14 14:04
     * @Param [userDto]
     **/
    @PostMapping("/userLogin")
    public AjaxResult userLogin(@RequestBody UserDto userDto) {
        return userService.userLogin(userDto);
    }

    /**
     * @return top.lmqstudy.basic.util.AjaxResult
     * @Author Mr.Li
     * @Description 手机验证码登录
     * @Date 2021/1/14 19:00
     * @Param [userDto]
     **/
    @PostMapping("/phoneLogin")
    public AjaxResult phoneLogin(@RequestBody UserDto userDto) {
        System.out.println(userDto);
        return userService.userLogin(userDto);
    }

    /**
     * @return top.lmqstudy.basic.util.AjaxResult
     * @Author Mr.Li
     * @Description 后台用户登录
     * @Date 2021/1/15 11:04
     * @Param [userDto]
     **/
    @PostMapping("/adminLogin")
    public AjaxResult adminLogin(@RequestBody UserDto userDto) {
        return userService.userLogin(userDto);
    }


    /**
     * @return top.lmqstudy.basic.util.AjaxResult
     * @Author Mr.Li
     * @Description 微信登录接口：获取code参数，利用Java程序发送Http请求获取access_token
     * @Date 2021/1/15 16:46
     * @Param [code]
     **/
    @GetMapping("/wechatLogin/{code}")
    public AjaxResult wechatLogin(@PathVariable("code") String code) {
        return userService.wechatLogin(code);
    }

    /**
     * @return top.lmqstudy.basic.util.AjaxResult
     * @Author Mr.Li
     * @Description 微信登录账号绑定
     * @Date 2021/1/15 19:16
     * @Param [userinfoDto]
     **/
    @PutMapping("/userBindAccount")
    public AjaxResult userBindAccount(@RequestBody UserDto userDto) {
        return userService.userBind(userDto);
    }

    /**
     * @Author Mr.Li
     * @Description 微信登录手机验证码绑定
     * @Date 2021/1/15 20:27
     * @Param [userDto]
     * @return top.lmqstudy.basic.util.AjaxResult
     **/
    @PutMapping("/userBindPhone")
    public AjaxResult userBindPhone(@RequestBody UserDto userDto){
        return userService.userBind(userDto);
    }


    /**
     * @Author Mr.Li
     * @Description 前台用户退出
     * @Date 2021/1/15 21:15
     * @Param [userDto]
     * @return top.lmqstudy.basic.util.AjaxResult
     **/
    @PostMapping("/userLogout")
    public AjaxResult userLogout(@RequestBody UserDto userDto){
        return userService.logout(userDto);
    }
}
