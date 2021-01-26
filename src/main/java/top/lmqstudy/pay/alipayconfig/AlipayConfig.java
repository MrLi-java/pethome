package top.lmqstudy.pay.alipayconfig;

import java.io.FileWriter;
import java.io.IOException;

/**
 * 支付宝的配置常量
 * @author Administrator
 * @version 1.0
 * @date 2021/1/22 11:53
 */
public class AlipayConfig {
    //↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2021000117603936";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCJEXTAgDeAOwqmBfqs0krtEj7aK/RmhT7zHnN7HkvGrNz/oWuatwXykoXnYpnDIMif1RKVx3TQnmnIdNM2ShFb5EILXJxh9m70iSLNos9hW25D4C8XdVxpAGafy2OG84zRmqokK5vOdG+AvEEQx4JSGdOO+sH2PbmFruphd1DQGakR6Rgugv6cWH71wS/xm6Swwq2+WRhCUNr39NZ0e1VUqENyAdtRrVtyqDRO6svVdT/teOzlK4Jkxy2blCNhK38ZQt1nJh+vl6F/rLPQaNRVvJuVwEaOEsx7iZAsxrhC4fA15H9h0gzWDFHpJjEDF0FpbIGL/RVPacT9K2Bcf5dlAgMBAAECggEAAK+drfj2TiX9f6c4AfVrepREzEK8DmdMUVstqMgWCQCEeqMMLtILrZMeKHJN4rQfTO18ElPVOdYRIXe0NulX/OW/jt7/yV26YG7vRagy8YHLo3tTwQJNXBksalBSRzKg2cu0Xkn5labvh+1pRfQ5qr+GV4jZv6m/a/PcKHC4btIwK1JkrZJ64KgRbR1tBt+K26bGo85UfqCjUam0IhTqGEp2trDlzO96nI5bfwd3Cpmi/diLJ+kJNYodo/OCGMPU3pTBHsmsTn53jCLi5Zsq+WBpIWqD18lR3csWu+QMcH8aiKGLu5fZ7vysxV9LMcPQ9rv5tVNsvJkScFqbEmSlYQKBgQDR2a6vaB58tZRjwvv+kDzfUG6u3Ery/q16svjShuRm0S/vSbGfXSOs9hXb8noC9GCDJE3jgStlhzm5d+Rw6rj6PFEjz+6JM26A/fcEuXCsEhcdPr+BUN2i058Yt/qpiICtSjooWpKDd9/y9hdHSYglBj5xSn2DR3tJyvrrbhmFGwKBgQCnNjpozUsuSF6UB/V486hYvLYcFl8U6De142fhwRCMytbDpX6uCWSSXJiMWiEerpm9MI4oQYJcapE3NYWDa5OVtjakGCivVIhQbRQY2gpvpOg4Oy6d+r09FHBMi5nZOryODfHcYhwA6KASbMOLPXQBa9APPX316mwcbLDnqe+dfwKBgF5ZuzrOW3bCxVcdYeOQwm29YiZokaIzc5hJQ6qUT7kSsLSrs5rta47mjel4WfdeHM6Z0hNKDlL1u7Rx/Vnvjv+jgu/RG3TAlRyWEywinFAhShcyETR5QU1mTI/2mFMFNqWzS/a80kcd7Kj2kHzSyM2swwJ49XnC9+Gzy6FyvTKlAoGBAIDIUEYlTzDvRgCG/LVSHCdz+GI7jLGChF3PLlP+KSv2kWAP1zhdPZtIO6llRTrnSLsgaWxxv+BDp2I5E4J7WSimNQE/Jh6bYX1n9WGKlR0VilC8rBO6aQNg+XjK0Awxo8VY0h1lZqIAiECIijZ0dyMdERqbxqafDPL7d2iFRmorAoGADdKppfqM9bty5adnCT+P0sHZEfW2gXJDIT3NY1A5fSElB3hE4EcGFLmUsVJEVzGriSsbph9HTJX80d1CLA/IvAeDm47dbDC0VHoFRjibv/stC0c8D9nG9zrHaqVKW/JlEaEMPHpYQdQuyJmlnxDrnp+LOKNa+KRArzdI+dAL65g=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAo3t4tWRZfG+PgNkhB1lBwIlkbhttwsTx3S44bmNYcAykMmzyVGsth0v7BDkVd0J5+lKojU7+pLmmJRTfgBvxwCXn3n9k+qYlhILycsOCIfouqqIt4w6RC67WfILFFrgBEqPcl8iZibnObaRC8e+Sn8UVDEBYfkrbMQElhrqevn/9upxpLZnpl4LCy/l1NRF6iYggt/TqVT84yErE8x5d9HDAsngl9bWmuiwicM2bcSvs+I2vqkLr+6SUkZhy17q+kwWqZXl6QvHPND5hPMAv6Z2i4ZA5nUu7fnerMM1Z/EgvaI7ebF/+Osg/+3/wZgOGBvdI+dR2AtjB7CZCkgulQwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://inuinx.natappfree.cc/alipay/notify";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost/success.html";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 数据格式
    public static String format = "JSON";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";

    //↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
