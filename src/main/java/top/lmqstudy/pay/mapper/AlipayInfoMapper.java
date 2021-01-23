package top.lmqstudy.pay.mapper;

import top.lmqstudy.basic.mapper.BaseMapper;
import top.lmqstudy.pay.domain.AlipayInfo;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/22/19:19
 * @Description:
 */
public interface AlipayInfoMapper extends BaseMapper<AlipayInfo> {
    /**
     * @Author Mr.Li
     * @Description 通过shop_id查找店铺信息
     * @Date 2021/1/22 19:19
     * @Param [shop_id]
     * @return top.lmqstudy.pay.domain.AlipayInfo
     **/
    AlipayInfo findByShopId(Long shop_id);
}
