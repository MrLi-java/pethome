package top.lmqstudy.basic.query;


import lombok.Data;

@Data
public class BaseQuery {
    private Integer currentPage = 1;
    private Integer pageSize = 10;

    private String keyWord;

    public Integer getStart(){
        return (this.currentPage-1)*this.pageSize;
    }
}
