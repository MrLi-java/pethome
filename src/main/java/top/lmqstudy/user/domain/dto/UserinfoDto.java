package top.lmqstudy.user.domain.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 微信用户个人信息
 * @author Administrator
 * @version 1.0
 * @date 2021/1/15 19:14
 */
@Data
public class UserinfoDto {
    //微信用户全局唯一编号
    private String openid;
    //昵称
    private String nickname;
    //性别
    private Integer sex;
    //国家地区
    private String country;
    //省份
    private String province;
    //城市
    private String city;
    //头像地址
    private String headimgurl;
    private String language;
    private List<String> privilege = new ArrayList<>();
    private String unionid;
    private Integer errcode;
    private String errmsg;
}
