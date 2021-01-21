package top.lmqstudy.basic.contant;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/11/15:02
 * @Description: 系统常量字段
 */
public class Contant {

    /*
    百度地图开放平台AK
     */
    public static final String BAIDU_SERVICE_AK = "gNxcxhf3KoDVUOnLqKOGwxQucttQMT7c";

    /*
    前台用户手机注册
     */
    public static final String PHONE_REG = "phone_reg";

    /*
    前台用户手机登录
     */
    public static final String PHONE_LOGIN = "phone_login";

    /*
    前台用户邮件注册
     */
    public static final String EMAIL_REG = "email_reg";

    /*
    前台用户邮件登录
     */
    public static final String EMAIL_LOGIN = "email_login";

    /*
    前台用户登录
     */
    public static final String FRONT = "front";

    /*
    后台用户登录
     */
    public static final String ADMIN = "admin";

    /*
    前台用户退出
     */
    public static final String USER_LOGOUT = "user_logout";

    /*
    后台用户退出
     */
    public static final String ADMIN_LOGOUT = "admin_logout";



    public static final String TOKEN_REQUST_HEADER = "userToken";






    /*
    登录用户存储在Redis的过期时间
     */
    public static final Integer EXPIRE_TIME_IN_REDIS=30*60;

    //SMS网建通
    public static final String SMS_UID = "Mr.Li_nb";
    public static final String SMS_KEY = "d41d8cd98f00b204e980";

    /**
     * 数据状态：正常
     */
    public static final Integer STATE_NORMAL = 0;
    /**
     * 数据状态：待审核
     */
    public static final Integer STATE_AUDIT = 1;
    /**
     * 数据状态：待激活
     */
    public static final Integer STATE_ACTIVE = 2;
    /**
     * 数据状态：禁用
     */
    public static final Integer STATE_DISABLED = -1;

    /**
     * 微信登录相关常量：通过code获取access_token的url
     */
    public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    public static final String WECHAT_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
    /**
     * APPID
     */
    public static final String APPID = "wxd853562a0548a7d0";
    /**
     * 密钥
     */
    public static final String APPSECRET = "4a5d5615f93f24bdba2ba8534642dbb6";

    /*
    微信登录账户绑定
     */
    public static final String BIND_ACCOUNT = "bind_account";
    /*
    微信登陆手机绑定
     */
    public static final String BIND_PHONE = "bind_phone";


}
