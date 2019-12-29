package fun.jianjie.miniorder.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;
import java.util.Map;


@Table(name = "category")
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer topic_img_id;
    private Integer delete_time;
    private String description;
    private Integer update_time;

    private List<ImageDomain> img;

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

    public Integer getTopic_img_id() {
        return topic_img_id;
    }

    public void setTopic_img_id(Integer topic_img_id) {
        this.topic_img_id = topic_img_id;
    }

    public Integer getDelete_time() {
        return delete_time;
    }

    public void setDelete_time(Integer delete_time) {
        this.delete_time = delete_time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Integer update_time) {
        this.update_time = update_time;
    }

    public List<ImageDomain> getImg() {
        return img;
    }

    public void setImg(List<ImageDomain> img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", topic_img_id=" + topic_img_id +
                ", delete_time=" + delete_time +
                ", description='" + description + '\'' +
                ", update_time=" + update_time +
                ", img=" + img +
                '}';
    }
}
