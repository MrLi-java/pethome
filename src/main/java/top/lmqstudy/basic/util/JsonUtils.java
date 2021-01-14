package top.lmqstudy.basic.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/14/15:39
 * @Description: 将对象转化为Json字符串和将Json字符串转化为特指的对象
 */
public class JsonUtils {
    /**
     * @Author Mr.Li
     * @Description 将对象转化为Json字符串
     * @Date 2021/1/14 15:42
     * @Param [obj]
     * @return java.lang.String
     **/
    public static String toJsonStr(Object obj){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return  objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Author Mr.Li
     * @Description 将Json字符串转化为特指的对象
     * @Date 2021/1/14 15:44
     * @Param [jsonStr, clz]
     * @return T
     **/
    public static <T> T toObject(String jsonStr,Class<T> clz){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonStr,clz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
