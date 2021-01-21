package top.lmqstudy.user.domain;

import lombok.Data;

import java.util.Date;

/**
 * 用户地址
 *
 * @author Administrator
 * @version 1.0
 * @date 2021/1/21 14:31
 */
@Data
public class UserAddress {
    private Long id;
    private Date createTime = new Date();
    private Date updateTime;
    //联系人
    private String contacts;
    //地区编号
    private String areaCode;
    //详细街道地址
    private String address;
    //全地址
    private String fullAddress;
    //联系电话
    private String phone;
    //备用联系电话
    private String phoneBack;
    //固定电话
    private String tel;
    //邮政编码
    private String postCode;
    //邮箱
    private String email;
    //所属用户
    private Long user_id;
}
