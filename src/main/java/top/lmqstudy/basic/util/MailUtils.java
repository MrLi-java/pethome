package top.lmqstudy.basic.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * TODO
 *
 * @author Administrator
 * @version 1.0
 * @date 2021/1/12 18:50
 */
@Component
public class MailUtils {

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 发送邮件
     * @param to
     * @param subject
     * @param content
     */
    public void send(String to, String subject, String content){
        try {
            //创建复杂邮件对象
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            //发送复杂邮件的工具类
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"utf-8");

            //发件人：必须与mail.properties配置中的发件人一致
            helper.setFrom("320899771@qq.com");

            //邮件标题
            helper.setSubject(subject);

            //邮件内容
            helper.setText(content,true);
            //添加附件
            //helper.addAttachment("罗宾.jpg",new File("C:\\Users\\hm\\Desktop\\work\\aa.jpg"));
            //helper.addAttachment("压缩文件", new File("C:\\Users\\hm\\Desktop\\20191010\\2020-02-05-智能商贸-DAY4\\resources\\resources.zip"));
            //收件人
            helper.setTo(to);

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


}
