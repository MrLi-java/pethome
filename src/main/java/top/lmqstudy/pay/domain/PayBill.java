package top.lmqstudy.pay.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 支付单
 *
 * @author Administrator
 * @version 1.0
 * @date 2021/1/22 11:11
 */
@Data
public class PayBill {
    private Long id;
    private String digest;
    private BigDecimal money;
    //支付状态 1待支付  0已支付  -1取消
    private Integer state = 1;
    //最迟支付时间：与订单表中的lastPayTime一致
    private Date lastPayTime;
    //支付方式：1银联 2微信 3支付宝
    private Integer payChannel;
    private Date createTime = new Date();
    private Date updateTime;
    //订单编号
    private String orderSn;
    //支付宝返回的支付单号
    private String unionPaySn;
    //业务类型：businessType_adopt_order表示领养订单  businessType_product_order表示商品订单
    private String businessType;
    //业务主键：根据业务类型来区分表
    private Long businessKey;
    //前台用户
    private Long user_id;
    //前台用户名
    private String nickName;
    //商家店铺
    private Long shop_id;
    //店铺名称
    private String shopName;
}
