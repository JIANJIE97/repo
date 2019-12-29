package fun.jianjie.miniorder.dto;

import java.math.BigDecimal;

public class OrderProductDTO {
    private Integer id;
    private Boolean haveStock;
    private Integer count;
    private BigDecimal price;
    private String name;
    private BigDecimal totalPrice;
    private String main_img_url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getHaveStock() {
        return haveStock;
    }

    public void setHaveStock(Boolean haveStock) {
        this.haveStock = haveStock;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getMain_img_url() {
        return main_img_url;
    }

    public void setMain_img_url(String main_img_url) {
        this.main_img_url = main_img_url;
    }

    @Override
    public String toString() {
        return "OrderProductDTO{" +
                "id=" + id +
                ", haveStock=" + haveStock +
                ", count=" + count +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", totalPrice=" + totalPrice +
                ", main_img_url='" + main_img_url + '\'' +
                '}';
    }
}
