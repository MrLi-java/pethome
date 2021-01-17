package top.lmqstudy.product.domain;

import lombok.Data;

@Data
public class ProductDetail {

  private Long id;
  private Long product_id;
  private String intro;
  private String orderNotice;


}
