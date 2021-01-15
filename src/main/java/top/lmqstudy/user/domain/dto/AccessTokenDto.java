package top.lmqstudy.user.domain.dto;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/15/17:56
 * @Description:
 */
@Data
public class AccessTokenDto {
    private String access_token;
    private Integer expires_in;
    private String refresh_token;
    private String openid;
    private String scope;
    private String unionid;
    private Integer errcode;
    private String errmsg;
}
