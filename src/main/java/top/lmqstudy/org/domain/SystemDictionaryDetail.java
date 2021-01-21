package top.lmqstudy.org.domain;

import lombok.Data;

/**
 * 数据字典明细
 * @author Administrator
 * @version 1.0
 * @date 2021/1/20 10:46
 */
@Data
public class SystemDictionaryDetail {
    private Long id;
    private String name;
    private Long types_id;
}
