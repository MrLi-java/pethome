package top.lmqstudy.order.domain;


import lombok.Data;
import top.lmqstudy.pet.domain.Pet;
import top.lmqstudy.user.domain.User;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 宠物领养订单
 *
 * @author Administrator
 * @version 1.0
 * @date 2021/1/21 15:49
 */
@Data
public class AdoptOrder {
    private Long id;
    //订单摘要
    private String digest;
    //订单状态：1待支付 2已支付 0已完成 -1已取消
    private Integer state = 1;
    //订单金额
    private BigDecimal price;
    //订单编号：采用工具类自动生成
    private String orderSn;
    //支付流水号：调用了支付宝接口后返回的单号
    private String paySn;
    //最迟支付时间：超过此时间未完成支付，则自动取消订单
    private Date lastPayTime;
    //最迟确认时间：默认是支付成功后的7天后
    private Date lastConfirmTime;
    //宠物id
    private Long pet_id;
    private Pet pet;
    //下单人
    private Long user_id;
    private User user;
    //店铺id
    private Long shop_id;
    //订单地址:用户地址是随时可以修改的，而订单地址是不能修改，除非联系商家客服在后台管理系统中修改
    private Long address_id;


    private Integer payType;

    private OrderAddress orderAddress;

}
