package top.lmqstudy.basic.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import top.lmqstudy.basic.contant.Contant;
import top.lmqstudy.basic.util.AjaxResult;
import top.lmqstudy.basic.util.RedisUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/14/15:39
 * @Description: 用户登录拦截器设置
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(request.getRequestURI());
        //获取userLoken消息头
        String userToken = request.getHeader("userToken");
        //判断userLoken是否为空
        if(StringUtils.hasText(userToken)){
            String userJsonStr = RedisUtils.INSTANCE.get(userToken);
            //判断Redis是否有该用户的登录信息
            if(StringUtils.hasText(userJsonStr)){
                //刷新用户登录信息的过期时间
                RedisUtils.INSTANCE.set(userToken,userJsonStr, Contant.EXPIRE_TIME_IN_REDIS);
                return true;
            }
        }
        AjaxResult ajaxResult = AjaxResult.me().setMsg("noUser");
        //利用jackson工具将对象转化为json字符串
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = objectMapper.writeValueAsString(ajaxResult);
        //利用响应对象的输出流输出这个json字符串
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(jsonStr);
        response.getWriter().flush();
        response.getWriter().close();
        return false;
    }
}
