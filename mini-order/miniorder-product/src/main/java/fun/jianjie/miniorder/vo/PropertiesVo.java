package fun.jianjie.miniorder.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;


@JsonIgnoreProperties(value = {"handler"})
public class PropertiesVo implements Serializable{
    private Integer id;
    private String name;
    private String detail;
    private Integer delete_time;
    private Integer update_time;
    private Integer product_id;//外键


    @Override
    public String toString() {
        return "PropertiesVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", detail='" + detail + '\'' +
                ", delete_time=" + delete_time +
                ", update_time=" + update_time +
                ", product_id=" + product_id +
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getDelete_time() {
        return delete_time;
    }

    public void setDelete_time(Integer delete_time) {
        this.delete_time = delete_time;
    }

    public Integer getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Integer update_time) {
        this.update_time = update_time;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }
}
