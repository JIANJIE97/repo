package fun.jianjie.miniorder.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;


@Table(name = "product")
public class Product implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private BigDecimal price;
    private Integer stock;
    private Integer delete_time;
    private Integer category_id;
    private String main_img_url;
    private Integer from;
    private Integer create_time;
    private Integer update_time;
    private String summary;
    private Integer img_id;

    public Product() {
    }

    public Product(Integer id, String name, BigDecimal price, Integer stock, Integer delete_time, Integer category_id, String main_img_url, Integer from, Integer create_time, Integer update_time, String summary, Integer img_id) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.delete_time = delete_time;
        this.category_id = category_id;
        this.main_img_url = main_img_url;
        this.from = from;
        this.create_time = create_time;
        this.update_time = update_time;
        this.summary = summary;
        this.img_id = img_id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", delete_time=" + delete_time +
                ", category_id=" + category_id +
                ", main_img_url='" + main_img_url + '\'' +
                ", from=" + from +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                ", summary='" + summary + '\'' +
                ", img_id=" + img_id +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getDelete_time() {
        return delete_time;
    }

    public void setDelete_time(Integer delete_time) {
        this.delete_time = delete_time;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public String getMain_img_url() {
        return main_img_url;
    }

    public void setMain_img_url(String main_img_url) {
        this.main_img_url = main_img_url;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Integer create_time) {
        this.create_time = create_time;
    }

    public Integer getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Integer update_time) {
        this.update_time = update_time;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getImg_id() {
        return img_id;
    }

    public void setImg_id(Integer img_id) {
        this.img_id = img_id;
    }
}
