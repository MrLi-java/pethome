package top.lmqstudy.user.service;

import top.lmqstudy.basic.service.IBaseService;
import top.lmqstudy.basic.util.AjaxResult;
import top.lmqstudy.user.domain.User;
import top.lmqstudy.user.domain.UserAddress;
import top.lmqstudy.user.domain.dto.AccessTokenDto;
import top.lmqstudy.user.domain.dto.UserDto;
import top.lmqstudy.user.domain.dto.UserinfoDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/11/15:04
 * @Description: User模块service层
 */
public interface IUserService extends IBaseService<User> {

    /**
     * @return top.lmqstudy.basic.util.AjaxResult
     * @Author Mr.Li
     * @Description 判断手机号是否存在
     * @Date 2021/1/11 16:18
     * @Param [type]  判断是登录还是注册 phone_reg:注册   phone_login:登录
     * @Param [phone]  前台传入的手机号
     **/
    AjaxResult validPhone(String type, String phone);

    /**
     * @return top.lmqstudy.basic.util.AjaxResult
     * @Author Mr.Li
     * @Description 手机验证码发送
     * @Date 2021/1/11 19:15
     * @Param [type, phone]
     **/
    AjaxResult sendMobileCode(String type, String phone);

    /**
     * @return top.lmqstudy.basic.util.AjaxResult
     * @Author Mr.Li
     * @Description 注册表单提交
     * @Date 2021/1/11 20:43
     * @Param [userDto]
     **/
    AjaxResult phoneReg(UserDto userDto);


    /**
     * @return top.lmqstudy.basic.util.AjaxResult
     * @Author Mr.Li
     * @Description 前台用户登录
     * @Date 2021/1/14 15:01
     * @Param [userDto]
     **/
    AjaxResult userLogin(UserDto userDto);

    /**
     * @Author Mr.Li
     * @Description 微信登录接口：获取code参数，利用Java程序发送Http请求获取access_token
     * @Date 2021/1/15 16:47
     * @Param [code]
     * @return top.lmqstudy.basic.util.AjaxResult
     **/
    AjaxResult wechatLogin(String code);

    /**
     * @Author Mr.Li
     * @Description 微信登录绑定
     * @Date 2021/1/15 19:16
     * @Param [userinfoDto]
     * @return top.lmqstudy.basic.util.AjaxResult
     **/
    AjaxResult userBind(UserDto userDto);

    /**
     * @Author Mr.Li
     * @Description 前台用户退出
     * @Date 2021/1/15 21:15
     * @Param [userDto]
     * @return top.lmqstudy.basic.util.AjaxResult
     **/
    AjaxResult logout(UserDto userDto, HttpServletRequest request);

    /**
     * @Author Mr.Li
     * @Description 通过前台用户id查找地址
     * @Date 2021/1/21 18:50
     * @Param [id]
     * @return java.util.List<top.lmqstudy.user.domain.UserAddress>
     **/
    List<UserAddress> loadUserAddressByUserId(Long id);
}
