package top.lmqstudy.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.lmqstudy.basic.contant.Contant;
import top.lmqstudy.basic.service.impl.BaseServiceImpl;
import top.lmqstudy.basic.util.AjaxResult;
import top.lmqstudy.basic.util.RedisUtils;
import top.lmqstudy.basic.util.SmsUtils;
import top.lmqstudy.basic.util.StrUtils;
import top.lmqstudy.user.domain.User;
import top.lmqstudy.user.domain.dto.UserDto;
import top.lmqstudy.user.mapper.UserMapper;
import top.lmqstudy.user.service.IUserService;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/11/15:09
 * @Description: User模块的service实现类
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * @Author Mr.Li
     * @Description 判断手机号是否存在
     * @Date 2021/1/11 16:20
     * @Param [type]  判断是登录还是注册 phone_reg:注册   phone_login:登录
     * @Param [phone]  前台传入的手机号
     * @return top.lmqstudy.basic.util.AjaxResult
     **/
    @Override
    public AjaxResult validPhone(String type, String phone) {
        User user = userMapper.findByPhone(phone);
        if(Contant.PHONE_REG.equals(type)){
            if(user!=null){
                return AjaxResult.me().setMsg("抱歉，您输入的手机号已经注册");
            }
        }else if(Contant.PHONE_LOGIN.equals(type)){
            if(user==null){
                return AjaxResult.me().setMsg("抱歉，您输入的手机号没有注册");
            }
        }
        return AjaxResult.me();
    }

    /**
     * @Author Mr.Li 
     * @Description 手机验证码发送
     * @Date 2021/1/11 19:15 
     * @Param [type, phone]
     * @return top.lmqstudy.basic.util.AjaxResult
     **/
    @Override
    public AjaxResult sendMobileCode(String type, String phone) {
        //随机生成一个验证码
        String verifyCode = StrUtils.getRandomString(6);
        //从Redis中获取验证码
        String value  = RedisUtils.INSTANCE.get(type + "-" + phone);
        //获取当前时间戳
        long currentTime = System.currentTimeMillis();
        //如果Value存在
        if(StringUtils.hasText(value)){
            //获取Redis中存入的验证码
            verifyCode = value.split("-")[0];
            //获取上一个存入的时间戳
            long lastTime = Long.parseLong(value.split("-")[1]);
            //当时间差小于1分钟提示输入频繁
            if(currentTime-lastTime <= 60*1000){
                return AjaxResult.me().setMsg("亲，您输入的过于频繁，请稍后再试");
            }else if (currentTime-lastTime<=300*1000){
                //5分钟以内提示将以前的验证码重新存入Redis
                RedisUtils.INSTANCE.set(type + "-" + phone, verifyCode+"-"+currentTime, 300);
                String content = "您注册账户的验证码为："+verifyCode+"，请您在5分钟之内输入！";
                SmsUtils.send(phone,content);
                System.out.println(content);
            }
        }else {
            //当大于5分钟或者第一次输入
            RedisUtils.INSTANCE.set(type + "-" + phone, verifyCode+"-"+currentTime, 300);
            String content = "您注册账户的验证码为："+verifyCode+"，请您在5分钟之内输入！";
            SmsUtils.send(phone,content);
            System.out.println(content);
        }
        return new AjaxResult().setMsg("验证码已发送，请在5分钟之内输入！").setSuccess(true);
    }

    /**
     * @Author Mr.Li
     * @Description 注册表单提交
     * @Date 2021/1/11 20:44
     * @Param [userDto]
     * @return top.lmqstudy.basic.util.AjaxResult
     **/
    @Override
    public AjaxResult phoneReg(UserDto userDto) {
        if(Contant.PHONE_REG.equals(userDto.getType())){
            String value = RedisUtils.INSTANCE.get(userDto.getType() + "-" + userDto.getPhone());
            if(StringUtils.hasText(value)){
                String verifyCode = value.split("-")[0];
                if(verifyCode.equals(userDto.getVerifyCode())){
                    userDto.setUsername(userDto.getPassword());
                    userDto.setSalt(StrUtils.getComplexRandomString(32));
                    userMapper.save(userDto);
                    return AjaxResult.me();
                }
            }
            return AjaxResult.me().setMsg("验证码错误");
        }else if(Contant.EMAIL_REG.equals(userDto.getType())) {
            return AjaxResult.me();
        }
        return AjaxResult.me().setMsg("登录失败，我们已经将程序员干掉祭天了！");
    }
}
