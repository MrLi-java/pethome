package top.lmqstudy.org.domain;


import lombok.Data;
import top.lmqstudy.basic.domain.BaseDomain;

import java.util.Date;

@Data
public class Shop  extends BaseDomain {


  private String name;
  private String tel;
  private Date registerTime;
  private Integer state;
  private String address;
  private String logo;
  private Long adminId;


}
