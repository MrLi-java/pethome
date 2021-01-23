package top.lmqstudy.pay.domain;

import lombok.Data;

/**
 * 支付宝密钥信息
 *
 * @author Administrator
 * @version 1.0
 * @date 2021/1/22 11:43
 */
@Data
public class AlipayInfo {
    private Long id;
    private Long shop_id;
    private String shopName;
    private String appid;
    //应用私钥
    private String merchant_private_key;
    //支付宝公钥
    private String alipay_public_key;
}
