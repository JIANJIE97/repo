package fun.jianjie.miniorder.vo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(value = {"handler"})
public class DetailImageVo implements Serializable{
    private Integer id;
    private Integer order;
    private ImageVo img_url;

    @Override
    public String toString() {
        return "DetailImageVo{" +
                "id=" + id +
                ", order=" + order +
                ", img_url=" + img_url +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public ImageVo getImg_url() {
        return img_url;
    }

    public void setImg_url(ImageVo img_url) {
        this.img_url = img_url;
    }
}
