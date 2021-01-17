package top.lmqstudy.product.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Product {

  private Long id;
  //商品名称
  private String name;
  //图片路径，多个的话就以逗号隔开
  private String resources;
  //成本价
  private BigDecimal costprice;
  //零售价
  private BigDecimal saleprice;
  //下架时间
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date offsaletime;
  //上架时间
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date onsaletime;
  //状态：0表示上架 -1表示下架
  private Integer state = -1;
  //创建时间
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date createtime = new Date();
  //销量
  private Integer salecount;
  //所属商家id
  private Long shop_id;

  private ProductDetail productDetail;

}
