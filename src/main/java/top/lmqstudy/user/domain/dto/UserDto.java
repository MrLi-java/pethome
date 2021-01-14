package top.lmqstudy.user.domain.dto;

import lombok.Data;
import top.lmqstudy.basic.util.StrUtils;
import top.lmqstudy.user.domain.User;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/11/20:42
 * @Description:
 */
@Data
public class UserDto extends User {
    private String verifyCode;
    private String type;

    private boolean rememberMe;
}
