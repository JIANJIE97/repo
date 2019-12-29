package fun.jianjie.miniorder.dto;

import java.math.BigDecimal;

public class ProductDTO {
    private Integer id;
    private BigDecimal price;
    private Integer stock;
    private String name;
    private String main_img_url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMain_img_url() {
        return main_img_url;
    }

    public void setMain_img_url(String main_img_url) {
        this.main_img_url = main_img_url;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", price=" + price +
                ", stock=" + stock +
                ", name='" + name + '\'' +
                ", main_img_url='" + main_img_url + '\'' +
                '}';
    }
}
