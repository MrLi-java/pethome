package top.lmqstudy.basic.util;


import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import top.lmqstudy.basic.contant.Contant;

import java.io.IOException;

/**
 * TODO
 * SMS短信平台调用工具类
 * @author Administrator
 * @version 1.0
 * @date 2021/1/11 15:56
 */
public class SmsUtils {

    /**
     * 发送短信
     * Java作为Http客户端发送Http请求：
     * 1）java.net包里面有个HttpUrlConnection，就是专门发送http请求的客户端工具类
     * 2）apache的commons-httpclient包对HttpUrlConnection进行了封装
     * 3）糊涂的hutool，HttpUtil工具类也是进行了封装
     * @param phone 接收短信的手机号
     * @param content 短信内容
     */
    public static void send(String phone, String content){
        try {
            HttpClient client = new HttpClient();
            PostMethod post = new PostMethod("http://utf8.api.smschinese.cn");
            post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");//在头文件中设置转码
            //键值对：请求参数名称与参数值形成键值对
            NameValuePair[] data = {
                    new NameValuePair("Uid", Contant.SMS_UID),
                    new NameValuePair("Key", Contant.SMS_KEY),
                    new NameValuePair("smsMob",phone),
                    new NameValuePair("smsText",content)
            };
            post.setRequestBody(data);

            client.executeMethod(post);
            Header[] headers = post.getResponseHeaders();
            int statusCode = post.getStatusCode();
            System.out.println("statusCode:"+statusCode);
            for(Header h : headers){
                System.out.println(h.toString());
            }
            String result = new String(post.getResponseBodyAsString().getBytes("utf-8"));
            System.out.println(result); //打印返回消息状态
            post.releaseConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
