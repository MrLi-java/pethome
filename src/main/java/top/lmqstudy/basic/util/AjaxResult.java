package top.lmqstudy.basic.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AjaxResult {
    private boolean success = true;
    private String msg = "操作成功！";

    public static AjaxResult me(){
        return new AjaxResult();
    }

    public AjaxResult setMsg(String msg) {
        this.success = false;
        this.msg = msg;
        return this;
    }

    public AjaxResult setSuccess(boolean success){
        this.success = true;
        return this;
    }

}
