package top.lmqstudy.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.lmqstudy.basic.util.AjaxResult;
import top.lmqstudy.basic.util.FastDfsUtils;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/12/19:37
 * @Description:
 */
@RestController
@RequestMapping("/fastdfs")
public class FastdfsController {

    /**
     * @Author Mr.Li
     * @Description 文件上传
     * @Date 2021/1/12 19:48
     * @Param [file]
     * @return top.lmqstudy.basic.util.AjaxResult
     **/
    @RequestMapping("/upload")
    public AjaxResult upload(MultipartFile file){
        try {
            String filename = file.getOriginalFilename();
            String extName = filename.substring(filename.lastIndexOf(".") + 1);
            String filePath = FastDfsUtils.upload(filename.getBytes(), extName);

            return AjaxResult.me().setSuccess(true).setData(filePath);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMsg("上传失败，我们正在殴打程序员"+e.getMessage());
        }

    }

}
