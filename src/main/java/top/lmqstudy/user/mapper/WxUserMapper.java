package top.lmqstudy.user.mapper;

import org.apache.ibatis.annotations.Param;
import top.lmqstudy.basic.mapper.BaseMapper;
import top.lmqstudy.user.domain.WxUser;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/15/18:31
 * @Description:
 */
public interface WxUserMapper extends BaseMapper<WxUser> {
    /**
     * @Author Mr.Li
     * @Description 通过Openid和Unionid查找微信用户
     * @Date 2021/1/15 18:37
     * @Param [openid, unionid]
     * @return top.lmqstudy.user.domain.WxUser
     **/
    WxUser findByOpenidAndUnionid(@Param("openid") String openid,@Param("unionid") String unionid);
}
