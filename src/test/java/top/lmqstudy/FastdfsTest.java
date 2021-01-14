package top.lmqstudy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.lmqstudy.basic.util.FastDfsUtils;
import top.lmqstudy.basic.util.MD5Utils;
import top.lmqstudy.user.domain.User;
import top.lmqstudy.user.mapper.UserMapper;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/12/20:12
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PethomeApplication.class)
public class FastdfsTest {

    @Autowired
    private UserMapper mapper;

    @Test
    public void test1(){
        User user = mapper.findByAccount("15608647407");
        System.out.println(user);
//        user.setPassword(MD5Utils.encrypByMd5(user.getPassword()+user.getSalt()));
//        mapper.update(user);

    }
}
