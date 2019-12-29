package fun.jianjie.miniorder.dto;

import java.math.BigDecimal;
import java.util.List;

public class OrderStatusDTO {
    private Boolean pass;
    private BigDecimal orderPrice;
    private Integer totalCount;
    private List<OrderProductDTO> pStatusArray;
    private Integer order_id;

    @Override
    public String toString() {
        return "OrderStatusDTO{" +
                "pass=" + pass +
                ", orderPrice=" + orderPrice +
                ", totalCount=" + totalCount +
                ", pStatusArray=" + pStatusArray +
                ", order_id=" + order_id +
                '}';
    }

    public Boolean getPass() {
        return pass;
    }

    public void setPass(Boolean pass) {
        this.pass = pass;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<OrderProductDTO> getpStatusArray() {
        return pStatusArray;
    }

    public void setpStatusArray(List<OrderProductDTO> pStatusArray) {
        this.pStatusArray = pStatusArray;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }
}
