package top.lmqstudy.order.mapper;

import top.lmqstudy.basic.mapper.BaseMapper;
import top.lmqstudy.order.domain.OrderAddress;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/21/19:56
 * @Description:
 */
public interface OrderAddressMapper extends BaseMapper<OrderAddress> {
    OrderAddress getOrderAddress(String orderSn);
}
