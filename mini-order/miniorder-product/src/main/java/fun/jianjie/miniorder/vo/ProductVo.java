package fun.jianjie.miniorder.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@JsonIgnoreProperties(value = {"handler"})
public class ProductVo implements Serializable{
    private Integer id;
    private String name;
    private BigDecimal price;
    private Integer stock;
    private String main_img_url;
    private String summary;
    private Integer img_id;
    private List<DetailImageVo> imgs;
    private List<PropertiesVo> properties;

    @Override
    public String toString() {
        return "ProductVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", main_img_url='" + main_img_url + '\'' +
                ", summary='" + summary + '\'' +
                ", img_id=" + img_id +
                ", imgs=" + imgs +
                ", properties=" + properties +
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

    public String getMain_img_url() {
        return main_img_url;
    }

    public void setMain_img_url(String main_img_url) {
        this.main_img_url = main_img_url;
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

    public List<DetailImageVo> getImgs() {
        return imgs;
    }

    public void setImgs(List<DetailImageVo> imgs) {
        this.imgs = imgs;
    }

    public List<PropertiesVo> getProperties() {
        return properties;
    }

    public void setProperties(List<PropertiesVo> properties) {
        this.properties = properties;
    }
}
