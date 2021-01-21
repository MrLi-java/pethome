package top.lmqstudy.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.lmqstudy.basic.service.impl.BaseServiceImpl;
import top.lmqstudy.basic.util.CodeGenerateUtils;
import top.lmqstudy.order.domain.AdoptOrder;
import top.lmqstudy.order.domain.OrderAddress;
import top.lmqstudy.order.mapper.AdoptOrderMapper;
import top.lmqstudy.order.mapper.OrderAddressMapper;
import top.lmqstudy.order.service.IAdoptOrderService;
import top.lmqstudy.pet.domain.Pet;
import top.lmqstudy.pet.mapper.PetMapper;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/21/19:49
 * @Description:
 */
@Service
public class AdoptOrderServiceImpl extends BaseServiceImpl<AdoptOrder> implements IAdoptOrderService {
    @Autowired
    private AdoptOrderMapper adoptOrderMapper;

    @Autowired
    private OrderAddressMapper orderAddressMapper;

    @Autowired
    private PetMapper petMapper;

    /**
     * @Author Mr.Li
     * @Description 前台用户领养宠物下单
     * @Date 2021/1/21 19:50
     * @Param [adoptOrder]
     * @return void
     **/
    @Override
    @Transactional
    public void orderAdopt(AdoptOrder adoptOrder) {
        //利用工具类生成一个随机的订单号
        String orderSn = CodeGenerateUtils.generateOrderSn(adoptOrder.getUser().getId());
        //保存订单的地址
        OrderAddress orderAddress = adoptOrder.getOrderAddress();
        orderAddress.setOrderSn(orderSn);
        orderAddressMapper.save(orderAddress);

        //保存订单信息
        adoptOrder.setOrderSn(orderSn);
        adoptOrder.setPrice(adoptOrder.getPet().getSaleprice());
        adoptOrder.setDigest("【"+adoptOrder.getOrderAddress().getContacts()+" 领养了 "+adoptOrder.getPet().getName());
        adoptOrder.setAddress_id(orderAddress.getId());
        adoptOrder.setShop_id(adoptOrder.getPet().getShop_id());
        adoptOrderMapper.save(adoptOrder);

        //将order_id存入orderAddress
        orderAddress.setOrder_id(adoptOrder.getId());
        orderAddressMapper.update(orderAddress);

        //修改宠物状态以及用户
        Pet pet = adoptOrder.getPet();
        pet.setState(-1);
        pet.setUser(adoptOrder.getUser());
        petMapper.update(pet);

    }
}
