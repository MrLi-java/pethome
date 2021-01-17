package top.lmqstudy.user.service.impl;

import cn.hutool.http.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import top.lmqstudy.basic.contant.Contant;
import top.lmqstudy.basic.service.impl.BaseServiceImpl;
import top.lmqstudy.basic.util.*;
import top.lmqstudy.org.domain.Employee;
import top.lmqstudy.org.domain.Shop;
import top.lmqstudy.org.mapper.EmployeeMapper;
import top.lmqstudy.org.mapper.ShopMapper;
import top.lmqstudy.user.domain.User;
import top.lmqstudy.user.domain.WxUser;
import top.lmqstudy.user.domain.dto.AccessTokenDto;
import top.lmqstudy.user.domain.dto.UserDto;
import top.lmqstudy.user.domain.dto.UserinfoDto;
import top.lmqstudy.user.mapper.UserMapper;
import top.lmqstudy.user.mapper.WxUserMapper;
import top.lmqstudy.user.service.IUserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private WxUserMapper wxUserMapper;

    @Autowired
    private ShopMapper shopMapper;


    /**
     * @Author Mr.Li
     * @Description 判断手机号是否存在
     * @Date 2021/1/11 16:20
     * @Param [type]  判断是登录还是注册 phone_reg:注册   phone_login:登录
     * @Param [phone]  前台传入的手机号
     * @return top.lmqstudy.basic.util.AjaxResult
     **/
    @Override
    @Transactional
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
        }else if(Contant.BIND_PHONE.equals(type)){
            if(user!=null){
                return AjaxResult.me().setMsg("抱歉，您输入的手机号已经注册");
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
    @Transactional
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
                //sms短信平台接口
                //SmsUtils.send(phone,content);
                System.out.println(content);
            }
        }else {
            //当大于5分钟或者第一次输入
            RedisUtils.INSTANCE.set(type + "-" + phone, verifyCode+"-"+currentTime, 300);
            String content = "您注册账户的验证码为："+verifyCode+"，请您在5分钟之内输入！";
            //sms短信平台接口
            //SmsUtils.send(phone,content);
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
    @Transactional
    public AjaxResult phoneReg(UserDto userDto) {
        if(Contant.PHONE_REG.equals(userDto.getType())){
            String value = RedisUtils.INSTANCE.get(userDto.getType() + "-" + userDto.getPhone());
            if(StringUtils.hasText(value)){
                String verifyCode = value.split("-")[0];
                if(verifyCode.equals(userDto.getVerifyCode())){
                    userDto.setUsername("Mr.Li");
                    userDto.setSalt(StrUtils.getComplexRandomString(32));
                    userDto.setPassword(MD5Utils.encrypByMd5(userDto.getPassword()+userDto.getSalt()));
                    userMapper.save(userDto);
                    return AjaxResult.me();
                }
            }
            return AjaxResult.me().setMsg("验证码错误");
        }else if(Contant.EMAIL_REG.equals(userDto.getType())) {
            return AjaxResult.me();
        }
        return AjaxResult.me().setMsg("注册失败，我们已经将程序员干掉祭天了！");
    }

    /**
     * @Author Mr.Li
     * @Description 前台和后台用户登录
     * @Date 2021/1/14 15:47
     * @Param [userDto]
     * @return top.lmqstudy.basic.util.AjaxResult
     **/
    @Override
    @Transactional
    public AjaxResult userLogin(UserDto userDto) {
        //前台用户账户登录
        if(Contant.FRONT.equals(userDto.getType())){
            //通过账户查找用户
            User loginUser = userMapper.findByAccount(userDto.getUsername());

            if(loginUser!=null){
                //账户存在判断密码是否一样（传入的密码进行加密处理）
                if(loginUser.getPassword().equals(MD5Utils.encrypByMd5(userDto.getPassword()+loginUser.getSalt()))){
                    //将密码设为空，避免被破译
                    loginUser.setPassword(null);
                    //利用UUID生成一个userToken
                    String userToken = UUID.randomUUID().toString();
                    //将loginUser对象转换为json字符串
                    String userJsonStr = JsonUtils.toJsonStr(loginUser);
                    //将数据存入Redis
                    RedisUtils.INSTANCE.set(userToken,userJsonStr,Contant.EXPIRE_TIME_IN_REDIS);
                    //将userToken和userJsonStr存入map集合
                    Map<String,Object> map = new HashMap<>();
                    map.put("userToken",userToken);
                    map.put("loginUser",userJsonStr);
                    //将map集合存入AjaxResult的data中返回给前端
                    return AjaxResult.me().setData(map);
                }
                return AjaxResult.me().setMsg("密码错误！");
            }
            return AjaxResult.me().setMsg("您的账户不存在！");
        }else if(Contant.ADMIN.equals(userDto.getType())){
            Employee loginUser = employeeMapper.findByAccount(userDto.getUsername());
            if(loginUser!=null && loginUser.getState() == Contant.STATE_NORMAL){
                //账户存在判断密码是否一样（传入的密码进行加密处理）
                if(loginUser.getPassword().equals(MD5Utils.encrypByMd5(userDto.getPassword()+loginUser.getSalt()))){
                    //将密码设为空，避免被破译
                    loginUser.setPassword(null);
                    //通过shop_id查找shop
                    Shop shop = shopMapper.loadById(loginUser.getShop_id());

                    loginUser.setHeadImg(shop.getLogo());
                    //利用UUID生成一个userToken
                    String userToken = UUID.randomUUID().toString();
                    //将loginUser对象转换为json字符串
                    String userJsonStr = JsonUtils.toJsonStr(loginUser);
                    //将数据存入Redis
                    RedisUtils.INSTANCE.set(userToken,userJsonStr,Contant.EXPIRE_TIME_IN_REDIS);
                    //将userToken和userJsonStr存入map集合
                    Map<String,Object> map = new HashMap<>();
                    map.put("userToken",userToken);
                    map.put("loginUser",userJsonStr);
                    //将map集合存入AjaxResult的data中返回给前端
                    return AjaxResult.me().setData(map);
                }
                return AjaxResult.me().setMsg("密码错误！");
            }
            return AjaxResult.me().setMsg("您的账户不存在或者您的账户状态异常！");
        }else if(Contant.PHONE_LOGIN.equals(userDto.getType())){
            String value = RedisUtils.INSTANCE.get(userDto.getType() + "-" + userDto.getPhone());
            User loginUser = userMapper.findByPhone(userDto.getPhone());
            if(StringUtils.hasText(value)){
                String verifyCode = value.split("-")[0];
                if(verifyCode.equals(userDto.getVerifyCode())){
                    //将Redis中的验证码移除
                    RedisUtils.INSTANCE.del(userDto.getType() + "-" + userDto.getPhone());
                    //利用UUID生成一个userToken
                    String userToken = UUID.randomUUID().toString();
                    //将loginUser对象转换为json字符串
                    String userJsonStr = JsonUtils.toJsonStr(loginUser);
                    //将数据存入Redis
                    RedisUtils.INSTANCE.set(userToken,userJsonStr,Contant.EXPIRE_TIME_IN_REDIS);
                    //将userToken和userJsonStr存入map集合
                    Map<String,Object> map = new HashMap<>();
                    map.put("userToken",userToken);
                    map.put("loginUser",userJsonStr);
                    //将map集合存入AjaxResult的data中返回给前端
                    return AjaxResult.me().setData(map);
                }
            }
            return AjaxResult.me().setMsg("验证码错误");
        }
        return null;
    }

    /**
     * @Author Mr.Li
     * @Description 微信登录接口：获取code参数，利用Java程序发送Http请求获取access_token
     * @Date 2021/1/15 16:47
     * @Param [code]
     * @return top.lmqstudy.basic.util.AjaxResult
     **/
    @Override
    @Transactional
    public AjaxResult wechatLogin(String code) {
        String accessTokenUrl = Contant.ACCESS_TOKEN_URL.replace("APPID", Contant.APPID).replace("SECRET", Contant.APPSECRET).replace("CODE",code);
        //利用糊涂小工具和code获取
        String result = HttpUtil.get(accessTokenUrl);
        if(!StringUtils.hasText(result)){
            return AjaxResult.me().setMsg("微信登录失败，我们正在鞭策程序员！！");
        }
        //将json字符串转换为实体类对象
        AccessTokenDto accessTokenDto = JsonUtils.toObject(result, AccessTokenDto.class);
        //判断返回的结果是否正确
        if(accessTokenDto.getErrcode()==null && !StringUtils.hasText(accessTokenDto.getErrmsg())){
            //通过Openid和Unionid查找微信用户
            WxUser wxUser = wxUserMapper.findByOpenidAndUnionid(accessTokenDto.getOpenid(),accessTokenDto.getUnionid());
            if(wxUser!=null){
                //利用UUID生成一个userToken
                String userToken = UUID.randomUUID().toString();
                //通过wxuser中的user_id找到user;
                User user = userMapper.findById(wxUser.getUser_id());
                user.setUsername(wxUser.getNickname());
                //将loginUser对象转换为json字符串
                String userJsonStr = JsonUtils.toJsonStr(user);
                //将数据存入Redis
                RedisUtils.INSTANCE.set(userToken,userJsonStr,Contant.EXPIRE_TIME_IN_REDIS);
                //将userToken和userJsonStr存入map集合
                Map<String,Object> map = new HashMap<>();
                map.put("userToken",userToken);
                map.put("loginUser",userJsonStr);
                //将map集合存入AjaxResult的data中返回给前端
                return AjaxResult.me().setData(map);
            }
            //当微信用户没有绑定返回给前端
            return AjaxResult.me().setMsg("unbind").setData(accessTokenDto);
        }
        return AjaxResult.me().setMsg("服务器繁忙请稍后再试！！");
    }

    /**
     * @Author Mr.Li
     * @Description 微信登录绑定
     * @Date 2021/1/15 19:17
     * @Param [userinfoDto]
     * @return top.lmqstudy.basic.util.AjaxResult
     **/
    @Override
    @Transactional
    public AjaxResult userBind(UserDto userDto) {
        String userinfoUrl = Contant.WECHAT_USERINFO_URL.replace("ACCESS_TOKEN", userDto.getAccess_token()).replace("OPENID", userDto.getOpenid());
        String result = HttpUtil.get(userinfoUrl);
        System.out.println(result);
        if(!StringUtils.hasText(result)){
            return AjaxResult.me().setMsg("绑定失败，程序员已经连夜跑路了！！");
        }
        UserinfoDto userinfoDto = JsonUtils.toObject(result, UserinfoDto.class);
        //微信登录账号绑定
        if(Contant.BIND_ACCOUNT.equals(userDto.getType())){
            //保存数据到t_user表，返回主键
            userDto.setSalt(StrUtils.getComplexRandomString(32));   //盐值
            userDto.setPassword(MD5Utils.encrypByMd5(userDto.getPassword() + userDto.getSalt()));   //密码加密
            userDto.setHeadImg(userinfoDto.getHeadimgurl());
            userMapper.save(userDto);
            //保存数据到t_wxuser表
            userDto.setUsername(userinfoDto.getNickname());
            WxUser wxUser = new WxUser();
            wxUser.setOpenid(userinfoDto.getOpenid());
            wxUser.setNickname(userinfoDto.getNickname());
            wxUser.setSex(userinfoDto.getSex());
            wxUser.setAddress(userinfoDto.getCountry()+ " " + userinfoDto.getProvince()+ " " + userinfoDto.getCity());
            wxUser.setHeadimgurl(userinfoDto.getHeadimgurl());
            wxUser.setUnionid(userinfoDto.getUnionid());
            wxUser.setUser_id(userDto.getId());
            wxUserMapper.save(wxUser);

            //利用UUID生成一个userToken
            String userToken = UUID.randomUUID().toString();
            //将loginUser对象转换为json字符串
            String userJsonStr = JsonUtils.toJsonStr(userDto);
            //将数据存入Redis
            RedisUtils.INSTANCE.set(userToken,userJsonStr,Contant.EXPIRE_TIME_IN_REDIS);
            //将userToken和userJsonStr存入map集合
            Map<String,Object> map = new HashMap<>();
            map.put("userToken",userToken);
            map.put("loginUser",userJsonStr);
            //将map集合存入AjaxResult的data中返回给前端
            return AjaxResult.me().setData(map);
            //微信登录手机绑定
        }else if(Contant.BIND_PHONE.equals(userDto.getType())){
            String value = RedisUtils.INSTANCE.get(userDto.getType() + "-" + userDto.getPhone());
            if(StringUtils.hasText(value)){
                String verifyCode = value.split("-")[0];
                if(verifyCode.equals(userDto.getVerifyCode())){
                    userDto.setUsername(userinfoDto.getNickname());
                    userDto.setSalt(StrUtils.getComplexRandomString(32));
                    userDto.setPassword(MD5Utils.encrypByMd5(userDto.getPassword()+userDto.getSalt()));
                    userDto.setHeadImg(userinfoDto.getHeadimgurl());
                    userMapper.save(userDto);

                    //保存数据到t_wxuser表
                    System.out.println(userinfoDto);
                    WxUser wxUser = new WxUser();
                    wxUser.setOpenid(userinfoDto.getOpenid());
                    wxUser.setNickname(userinfoDto.getNickname());
                    wxUser.setSex(userinfoDto.getSex());
                    wxUser.setAddress(userinfoDto.getCountry()+ " " + userinfoDto.getProvince()+ " " + userinfoDto.getCity());
                    wxUser.setHeadimgurl(userinfoDto.getHeadimgurl());
                    wxUser.setUnionid(userinfoDto.getUnionid());
                    wxUser.setUser_id(userDto.getId());
                    wxUserMapper.save(wxUser);

                    //利用UUID生成一个userToken
                    String userToken = UUID.randomUUID().toString();
                    //将loginUser对象转换为json字符串
                    String userJsonStr = JsonUtils.toJsonStr(userDto);
                    //将数据存入Redis
                    RedisUtils.INSTANCE.set(userToken,userJsonStr,Contant.EXPIRE_TIME_IN_REDIS);
                    //将userToken和userJsonStr存入map集合
                    Map<String,Object> map = new HashMap<>();
                    map.put("userToken",userToken);
                    map.put("loginUser",userJsonStr);
                    //将map集合存入AjaxResult的data中返回给前端
                    return AjaxResult.me().setData(map);
                }
            }
            return AjaxResult.me().setMsg("验证码错误");
        }
        return null;
    }

    /**
     * @Author Mr.Li
     * @Description 前台用户退出
     * @Date 2021/1/15 21:15
     * @Param [userDto]
     * @return top.lmqstudy.basic.util.AjaxResult
     **/
    @Override
    @Transactional
    public AjaxResult logout(UserDto userDto, HttpServletRequest request) {
        if(Contant.USER_LOGOUT.equals(userDto.getType())){
            RedisUtils.INSTANCE.del(userDto.getUserToken());
            return AjaxResult.me();
        }else if (Contant.ADMIN_LOGOUT.equals(userDto.getType())){
            Employee user = RedisUtils.INSTANCE.getUser(request, Employee.class);
            if(user!=null){
                RedisUtils.INSTANCE.del(request.getHeader(Contant.TOKEN_REQUST_HEADER));
            }
        }
        return AjaxResult.me().setMsg("退出失败！！");
    }

}
