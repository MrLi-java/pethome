package top.lmqstudy.product.domain;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class Shopcart {

  private Long id;
  private Long product_id;
  private Long user_id;
  private Integer buycount;
  private BigDecimal price;
  private BigDecimal amount;

  private Product product;

}
