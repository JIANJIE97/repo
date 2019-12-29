package fun.jianjie.miniorder.vo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(value = {"handler"})
public class CartProductVo implements Serializable {
    private Integer product_id;
    private Integer count;

    @Override
    public String toString() {
        return "CartProductVo{" +
                "product_id=" + product_id +
                ", count=" + count +
                '}';
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
