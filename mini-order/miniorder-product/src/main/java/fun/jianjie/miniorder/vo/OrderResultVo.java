package fun.jianjie.miniorder.vo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(value = {"handler"})
public class OrderResultVo implements Serializable{

    private String order_no;
    private Integer order_id;
    private String create_time;
    private Boolean pass;


    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public Boolean getPass() {
        return pass;
    }

    public void setPass(Boolean pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "OrderResultVo{" +
                "order_no='" + order_no + '\'' +
                ", order_id=" + order_id +
                ", create_time='" + create_time + '\'' +
                ", pass=" + pass +
                '}';
    }
}
