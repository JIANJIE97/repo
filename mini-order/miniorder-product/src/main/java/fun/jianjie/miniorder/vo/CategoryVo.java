package fun.jianjie.miniorder.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fun.jianjie.miniorder.domain.ImageDomain;

import java.io.Serializable;
import java.util.List;


@JsonIgnoreProperties(value = {"handler"})
public class CategoryVo implements Serializable{
    private Integer id;
    private String name;
    private ImageVo img;

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

    public ImageVo getImg() {
        return img;
    }

    public void setImg(ImageVo img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "CategoryVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", img=" + img +
                '}';
    }
}
