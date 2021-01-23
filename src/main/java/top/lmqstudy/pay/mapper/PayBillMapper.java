package top.lmqstudy.pay.mapper;

import top.lmqstudy.basic.mapper.BaseMapper;
import top.lmqstudy.pay.domain.PayBill;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/22/19:10
 * @Description:
 */
public interface PayBillMapper extends BaseMapper<PayBill> {

    PayBill loadByOrderSn(String out_trade_no);
}
