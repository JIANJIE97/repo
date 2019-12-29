package fun.jianjie.miniorder.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;


@JsonIgnoreProperties(value = {"handler"})
public class OrderProduct implements Serializable {
    private Integer order_id;
    private Integer product_id;
    private Integer count;//当前商品的数量
    private Long delete_time;
    private Long update_time;

    @Override
    public String toString() {
        return "OrderProduct{" +
                "order_id=" + order_id +
                ", product_id=" + product_id +
                ", count=" + count +
                ", delete_time=" + delete_time +
                ", update_time=" + update_time +
                '}';
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
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

    public Long getDelete_time() {
        return delete_time;
    }

    public void setDelete_time(Long delete_time) {
        this.delete_time = delete_time;
    }

    public Long getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Long update_time) {
        this.update_time = update_time;
    }
}
