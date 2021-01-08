package top.lmqstudy.org.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.lmqstudy.basic.domain.BaseDomain;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department extends BaseDomain {

  private String sn;
  private String name;
  private String dirPath;
  private Integer state;
  private Long manager_id;
  private Employee manager ;
  private Long parent_id;
  private Department parent;

  private List<Department> children = new ArrayList<>();




}
