package top.lmqstudy.org.mapper;

import top.lmqstudy.basic.mapper.BaseMapper;
import top.lmqstudy.org.controller.SystemDictionaryDetailController;
import top.lmqstudy.org.domain.SystemDictionaryDetail;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/20/18:00
 * @Description:
 */
public interface SystemDictionaryDetailMapper extends BaseMapper<SystemDictionaryDetail> {
    List<SystemDictionaryDetailController> getCoatColor(String sn);
}

