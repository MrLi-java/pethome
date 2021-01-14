package top.lmqstudy.user.mapper;

import top.lmqstudy.basic.mapper.BaseMapper;
import top.lmqstudy.user.domain.User;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/11/16:21
 * @Description:
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * @Author Mr.Li
     * @Description 通过手机号查找User
     * @Date 2021/1/11 16:22
     * @Param [phone]
     * @return top.lmqstudy.user.domain.User
     **/
    User findByPhone(String phone);

    /**
     * @Author Mr.Li
     * @Description 根据用户的邮箱，电话，用户名查找用户
     * @Date 2021/1/14 15:49
     * @Param [username]
     * @return void
     **/
    User findByAccount(String username);
}
