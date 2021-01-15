package top.lmqstudy.user.domain;

import lombok.Data;

@Data
public class WxUser {

  private Long id;
  private String openid;
  private String nickname;
  private Integer sex;
  private String address;
  private String headimgurl;
  private String unionid;
  private Long user_id;
  private User user;

}
