package top.lmqstudy.user.service;

import top.lmqstudy.basic.service.IBaseService;
import top.lmqstudy.basic.util.AjaxResult;
import top.lmqstudy.user.domain.User;
import top.lmqstudy.user.domain.dto.UserDto;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/11/15:04
 * @Description: User模块service层
 */
public interface IUserService extends IBaseService<User> {

    /**
     * @Author Mr.Li
     * @Description 判断手机号是否存在
     * @Date 2021/1/11 16:18
     * @Param [type]  判断是登录还是注册 phone_reg:注册   phone_login:登录
     * @Param [phone]  前台传入的手机号
     * @return top.lmqstudy.basic.util.AjaxResult
     **/
    AjaxResult validPhone(String type, String phone);

    /**
     * @Author Mr.Li
     * @Description 手机验证码发送
     * @Date 2021/1/11 19:15
     * @Param [type, phone]
     * @return top.lmqstudy.basic.util.AjaxResult
     **/
    AjaxResult sendMobileCode(String type, String phone);

    /**
     * @Author Mr.Li
     * @Description 注册表单提交
     * @Date 2021/1/11 20:43
     * @Param [userDto]
     * @return top.lmqstudy.basic.util.AjaxResult
     **/
    AjaxResult phoneReg(UserDto userDto);


    /**
     * @Author Mr.Li
     * @Description 前台用户登录
     * @Date 2021/1/14 15:01
     * @Param [userDto]
     * @return top.lmqstudy.basic.util.AjaxResult
     **/
    AjaxResult userLogin(UserDto userDto);
}
