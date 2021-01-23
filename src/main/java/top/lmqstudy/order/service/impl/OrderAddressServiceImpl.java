package top.lmqstudy.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lmqstudy.basic.service.impl.BaseServiceImpl;
import top.lmqstudy.order.domain.OrderAddress;
import top.lmqstudy.order.mapper.OrderAddressMapper;
import top.lmqstudy.order.service.IOrderAddressService;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/22/20:38
 * @Description:
 */
@Service
public class OrderAddressServiceImpl extends BaseServiceImpl<OrderAddress> implements IOrderAddressService {
    @Autowired
    private OrderAddressMapper orderAddressMapper;


    @Override
    public OrderAddress getOrderAddress(String orderSn) {
        return orderAddressMapper.getOrderAddress(orderSn);
    }
}
