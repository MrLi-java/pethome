package top.lmqstudy.product.query;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import top.lmqstudy.basic.query.BaseQuery;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/01/17/16:55
 * @Description:
 */
@Data
@ToString(callSuper = true)
public class ProductQuery extends BaseQuery {
    private Long shopId;
    private Integer state;

}
