package fun.jianjie.miniorder.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@JsonIgnoreProperties(value = {"handler"})
public class OrderVo implements Serializable{
    private Integer id;
    private String order_no;
    private String create_time;
    private BigDecimal total_price;
    private Integer status;
    private String snap_img;
    private String snap_name;
    private Integer total_count;
    private Integer select_status;
    private List<OrderProductVo> snap_items;
    private AddressVo snap_address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public BigDecimal getTotal_price() {
        return total_price;
    }

    public void setTotal_price(BigDecimal total_price) {
        this.total_price = total_price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSnap_img() {
        return snap_img;
    }

    public void setSnap_img(String snap_img) {
        this.snap_img = snap_img;
    }

    public String getSnap_name() {
        return snap_name;
    }

    public void setSnap_name(String snap_name) {
        this.snap_name = snap_name;
    }

    public Integer getTotal_count() {
        return total_count;
    }

    public void setTotal_count(Integer total_count) {
        this.total_count = total_count;
    }

    public Integer getSelect_status() {
        return select_status;
    }

    public void setSelect_status(Integer select_status) {
        this.select_status = select_status;
    }

    public List<OrderProductVo> getSnap_items() {
        return snap_items;
    }

    public void setSnap_items(List<OrderProductVo> snap_items) {
        this.snap_items = snap_items;
    }

    public AddressVo getSnap_address() {
        return snap_address;
    }

    public void setSnap_address(AddressVo snap_address) {
        this.snap_address = snap_address;
    }

    @Override
    public String toString() {
        return "OrderVo{" +
                "id=" + id +
                ", order_no='" + order_no + '\'' +
                ", create_time='" + create_time + '\'' +
                ", total_price=" + total_price +
                ", status=" + status +
                ", snap_img='" + snap_img + '\'' +
                ", snap_name='" + snap_name + '\'' +
                ", total_count=" + total_count +
                ", select_status=" + select_status +
                ", snap_items=" + snap_items +
                ", snap_address=" + snap_address +
                '}';
    }
}
