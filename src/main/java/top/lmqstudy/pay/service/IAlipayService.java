package top.lmqstudy.pay.service;

import top.lmqstudy.order.domain.AdoptOrder;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/22/18:51
 * @Description:
 */
public interface IAlipayService {
    String toPay(AdoptOrder adoptOrder);

    void paySuccess(String out_trade_no, String trade_no);
}
