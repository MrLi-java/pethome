package top.lmqstudy.pet.query;

import lombok.Data;
import top.lmqstudy.basic.query.BaseQuery;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/20/19:14
 * @Description:
 */
@Data
public class AdoptQuery extends BaseQuery {
    private Long shop_id;
}
