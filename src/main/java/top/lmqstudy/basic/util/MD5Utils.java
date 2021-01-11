package top.lmqstudy.basic.util;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

    /**
     * 加密
     * @param context
     */
    public static String encrypByMd5(String context) {
        try {  
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(context.getBytes());//update处理  
            byte [] encryContext = md.digest();//调用该方法完成计算  
  
            int i;  
            StringBuffer buf = new StringBuffer("");  
            for (int offset = 0; offset < encryContext.length; offset++) {//做相应的转化（十六进制）  
                i = encryContext[offset];  
                if (i < 0) i += 256;  
                if (i < 16) buf.append("0");  
                buf.append(Integer.toHexString(i));  
           }  
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block  
            e.printStackTrace();
            return  null;
        }  
    }

    public static void main(String[] args) {
        //加密
        System.out.println(MD5Utils.encrypByMd5("123456"));
        //加密加盐 查询用户时，除了查到加密密码外，还能查到颜值。 把输入密码+盐值加密和数据库存放密码比对就OK
        System.out.println(MD5Utils.encrypByMd5("123456"+ StrUtils.getComplexRandomString(32)));
        System.out.println(MD5Utils.encrypByMd5("123456"+ StrUtils.getComplexRandomString(32)));
        System.out.println(MD5Utils.encrypByMd5("123456"+ StrUtils.getComplexRandomString(32)));
    }

}