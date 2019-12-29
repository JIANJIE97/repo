package fun.jianjie.miniorder.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(value = {"handler"})
public class SummaryOrderVo implements Serializable{
    private List<OrderVo> data;
    private Integer current_page;

    public List<OrderVo> getData() {
        return data;
    }

    public void setData(List<OrderVo> data) {
        this.data = data;
    }

    public Integer getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(Integer current_page) {
        this.current_page = current_page;
    }

    @Override
    public String toString() {
        return "SummaryOrderVo{" +
                "data=" + data +
                ", current_page=" + current_page +
                '}';
    }
}
