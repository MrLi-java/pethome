package top.lmqstudy.org.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lmqstudy.org.service.ISystemDictionaryDetailService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/20/17:47
 * @Description:
 */
@RestController
@RequestMapping("/systemDictionaryDetail")
public class SystemDictionaryDetailController {

    @Autowired
    private ISystemDictionaryDetailService systemDictionaryDetailService;

    /**
     * @Author Mr.Li
     * @Description 得到宠物毛色信息
     * @Date 2021/1/20 17:56
     * @Param [sn]
     * @return java.util.List<top.lmqstudy.org.controller.SystemDictionaryDetailController>
     **/
    @GetMapping("/{sn}")
    public List<SystemDictionaryDetailController> getCoatColor(@PathVariable("sn")String sn){
        return systemDictionaryDetailService.getCoatColor(sn);
    }

}
