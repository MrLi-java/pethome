package top.lmqstudy.pay.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lmqstudy.basic.contant.Contant;
import top.lmqstudy.order.domain.AdoptOrder;
import top.lmqstudy.order.mapper.AdoptOrderMapper;
import top.lmqstudy.pay.alipayconfig.AlipayConfig;
import top.lmqstudy.pay.domain.AlipayInfo;
import top.lmqstudy.pay.domain.PayBill;
import top.lmqstudy.pay.mapper.AlipayInfoMapper;
import top.lmqstudy.pay.mapper.PayBillMapper;
import top.lmqstudy.pay.service.IAlipayService;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/22/18:51
 * @Description:
 */
@Service
public class AlipayServiceImpl implements IAlipayService {
    @Autowired
    private AlipayInfoMapper alipayInfoMapper;

    @Autowired
    private PayBillMapper payBillMapper;

    @Autowired
    private AdoptOrderMapper adoptOrderMapper;

    /**
     * @Author Mr.Li
     * @Description 调用支付接口获取一个html字符串
     * @Date 2021/1/22 19:18
     * @Param [adoptOrder]
     * @return java.lang.String
     **/
    @Override
    public String toPay(AdoptOrder adoptOrder){
        try {
            //先通过shop_id查询支付宝密钥数据
            AlipayInfo alipayInfo = alipayInfoMapper.findByShopId(adoptOrder.getShop_id());

            //获得初始化的AlipayClient
//            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
//                    AlipayConfig.merchant_private_key, AlipayConfig.format, AlipayConfig.charset,
//                    AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, alipayInfo.getAppid(),
                    alipayInfo.getMerchant_private_key(), AlipayConfig.format, AlipayConfig.charset,
                    alipayInfo.getAlipay_public_key(), AlipayConfig.sign_type);

            //设置请求参数
            AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
            alipayRequest.setReturnUrl(AlipayConfig.return_url);
            alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

            //商户订单号，商户网站订单系统中唯一订单号，必填
            String out_trade_no = adoptOrder.getOrderSn();
            //付款金额，必填
            String total_amount = adoptOrder.getPrice().toString();
            //订单名称，必填
            String subject = adoptOrder.getDigest();
            //商品描述，可空
            String body = adoptOrder.getDigest();
            //添加支付宝接口调用需要的参数
            alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                    + "\"total_amount\":\""+ total_amount +"\","
                    + "\"subject\":\""+ subject +"\","
                    + "\"body\":\""+ body +"\","
                    + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

            //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
            //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
            //		+ "\"total_amount\":\""+ total_amount +"\","
            //		+ "\"subject\":\""+ subject +"\","
            //		+ "\"body\":\""+ body +"\","
            //		+ "\"timeout_express\":\"10m\","
            //		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
            //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

            //发送请求，接收响应结果字符串
            String result = alipayClient.pageExecute(alipayRequest).getBody();
            return result;
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Author Mr.Li
     * @Description 修改订单状态和支付单状态
     * @Date 2021/1/22 20:12
     * @Param [out_trade_no, trade_no]
     * @return void
     **/
    @Override
    public void paySuccess(String out_trade_no, String trade_no) {
        //先通过订单编号查询支付单，获取业务类型和业务主键
        PayBill payBill = payBillMapper.loadByOrderSn(out_trade_no);
        payBill.setState(0);    //已支付
        payBill.setUpdateTime(new Date());
        payBill.setUnionPaySn(trade_no);        //支付宝单号
        payBillMapper.update(payBill);

        //宠物领养订单 t_order_adopt
        if(payBill.getBusinessType().equals(Contant.BUSINESS_TYPE_ADOPT)){
            //根据业务主键修改订单数据
            Date lastConfirmTime = DateUtils.addDays(new Date(), 7);
            adoptOrderMapper.updateStateAndPaySn(payBill.getBusinessKey(), trade_no, lastConfirmTime);
        }
        //商品服务订单 t_order_product
        else if(payBill.getBusinessType().equals(Contant.BUSINESS_TYPE_PRODUCT)){

        }
    }

}
