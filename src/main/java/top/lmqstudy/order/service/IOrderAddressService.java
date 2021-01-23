package top.lmqstudy.order.service;

import top.lmqstudy.basic.service.IBaseService;
import top.lmqstudy.order.domain.OrderAddress;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/22/20:37
 * @Description:
 */
public interface IOrderAddressService extends IBaseService<OrderAddress> {
    OrderAddress getOrderAddress(String orderSn);
}
