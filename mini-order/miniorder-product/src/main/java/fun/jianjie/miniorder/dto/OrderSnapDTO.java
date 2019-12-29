package fun.jianjie.miniorder.dto;

import fun.jianjie.miniorder.vo.AddressVo;

import java.math.BigDecimal;
import java.util.List;

public class OrderSnapDTO {
    private BigDecimal orderPrice;
    private Integer totalCount;
    private List<OrderProductDTO> orderProductList; //订单商品列表
    private AddressVo snapAddress;
    private String snapName;
    private String snapImg;

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

    public List<OrderProductDTO> getOrderProductList() {
        return orderProductList;
    }

    public void setOrderProductList(List<OrderProductDTO> orderProductList) {
        this.orderProductList = orderProductList;
    }

    public AddressVo getSnapAddress() {
        return snapAddress;
    }

    public void setSnapAddress(AddressVo snapAddress) {
        this.snapAddress = snapAddress;
    }

    public String getSnapName() {
        return snapName;
    }

    public void setSnapName(String snapName) {
        this.snapName = snapName;
    }

    public String getSnapImg() {
        return snapImg;
    }

    public void setSnapImg(String snapImg) {
        this.snapImg = snapImg;
    }

    @Override
    public String toString() {
        return "OrderSnapDTO{" +
                "orderPrice=" + orderPrice +
                ", totalCount=" + totalCount +
                ", orderProductList=" + orderProductList +
                ", snapAddress=" + snapAddress +
                ", snapName='" + snapName + '\'' +
                ", snapImg='" + snapImg + '\'' +
                '}';
    }
}
