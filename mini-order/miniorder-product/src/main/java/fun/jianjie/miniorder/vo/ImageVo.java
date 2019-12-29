package fun.jianjie.miniorder.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Map;


@JsonIgnoreProperties(value = {"handler"})
public class ImageVo implements Serializable{
    private String url;

    @Override
    public String toString() {
        return "ImageVo{" +
                "url='" + url + '\'' +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
