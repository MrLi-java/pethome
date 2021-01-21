package top.lmqstudy.pet.query;

import io.swagger.models.auth.In;
import lombok.Data;
import top.lmqstudy.basic.query.BaseQuery;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/20/21:16
 * @Description:
 */
@Data
public class PetQuery extends BaseQuery {
    private Long shop_id;
    private Integer state;
}
