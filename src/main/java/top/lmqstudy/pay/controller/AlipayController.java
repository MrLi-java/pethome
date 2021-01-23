package top.lmqstudy.pay.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lmqstudy.pay.alipayconfig.AlipayConfig;
import top.lmqstudy.pay.service.IAlipayService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/22/20:08
 * @Description:
 */
@RestController
@RequestMapping("/alipay")
public class AlipayController {
    @Autowired
    private IAlipayService alipayService;

    @RequestMapping("/notify")
    public String notifyUrl(HttpServletRequest request){
        try {
            //获取支付宝POST过来反馈信息
            Map<String,String> params = new HashMap<String,String>();
            Map<String,String[]> requestParams = request.getParameterMap();
            for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
                String name = (String) iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
                }
                params.put(name, valueStr);
            }
            //二次验证签名：（验证支付金额、订单编号、支付宝单号是否与第一次请求时完全一致）
            boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名
            System.out.println("signVerified：" + signVerified);
            //——请在这里编写您的程序（以下代码仅作参考）——

        /* 实际验证过程建议商户务必添加以下校验：
        1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
        2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
        3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
        4、验证app_id是否为该商户本身。
        */
            if(signVerified) {//验证成功
                //商户订单号
                String out_trade_no = request.getParameter("out_trade_no");

                //支付宝交易号
                String trade_no = request.getParameter("trade_no");

                //交易状态
                String trade_status = request.getParameter("trade_status");

                //支付成功：实现自己商家的业务（修改订单状态和支付单状态）
                if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
                    //修改订单状态和支付单状态
                    alipayService.paySuccess(out_trade_no, trade_no);
                    System.out.println("老子终于进来了！！！！");
                    return "success";
                }
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return "failure";
    }

}
