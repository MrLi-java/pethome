package top.lmqstudy.org.service.impl;

import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lmqstudy.basic.service.impl.BaseServiceImpl;
import top.lmqstudy.org.controller.SystemDictionaryDetailController;
import top.lmqstudy.org.domain.SystemDictionaryDetail;
import top.lmqstudy.org.mapper.SystemDictionaryDetailMapper;
import top.lmqstudy.org.service.ISystemDictionaryDetailService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/20/17:56
 * @Description:
 */
@Service
public class SystemDictionaryDetailServiceImpl extends BaseServiceImpl<SystemDictionaryDetail> implements ISystemDictionaryDetailService {
    @Autowired
    private SystemDictionaryDetailMapper systemDictionaryDetailMapper;

    @Override
    public List<SystemDictionaryDetailController> getCoatColor(String sn) {
        return systemDictionaryDetailMapper.getCoatColor(sn);
    }
}
