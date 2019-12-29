package fun.jianjie.miniorder.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.ibatis.annotations.Options;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Table(name = "order")
public class Order implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String order_no;        //订单号
    private Integer user_id;        //外键，用户id，注意并不是openid
    private Integer delete_time;
    private Long create_time;
    private Integer update_time;
    private BigDecimal total_price;
    private Integer status;         //1:未支付， 2：已支付，3：已发货 , 4: 已支付，但库存不足
    private String snap_img;        //订单快照图片
    private String snap_name;       //订单快照名称
    private Integer total_count;
    private String snap_items;      //订单其他信息快照（json)
    private String snap_address;    //地址快照
    private Integer select_status;  //选择是店食还是外卖，1外卖，2店食
    private String prepay_id;       //订单微信支付的预订单id（用于发送模板消息）

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", order_no='" + order_no + '\'' +
                ", user_id=" + user_id +
                ", delete_time=" + delete_time +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                ", total_price=" + total_price +
                ", status=" + status +
                ", snap_img='" + snap_img + '\'' +
                ", snap_name='" + snap_name + '\'' +
                ", total_count=" + total_count +
                ", snap_items='" + snap_items + '\'' +
                ", snap_address='" + snap_address + '\'' +
                ", select_status=" + select_status +
                ", prepay_id='" + prepay_id + '\'' +
                '}';
    }

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

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getDelete_time() {
        return delete_time;
    }

    public void setDelete_time(Integer delete_time) {
        this.delete_time = delete_time;
    }

    public Long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Long create_time) {
        this.create_time = create_time;
    }

    public Integer getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Integer update_time) {
        this.update_time = update_time;
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

    public String getSnap_items() {
        return snap_items;
    }

    public void setSnap_items(String snap_items) {
        this.snap_items = snap_items;
    }

    public String getSnap_address() {
        return snap_address;
    }

    public void setSnap_address(String snap_address) {
        this.snap_address = snap_address;
    }

    public Integer getSelect_status() {
        return select_status;
    }

    public void setSelect_status(Integer select_status) {
        this.select_status = select_status;
    }

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }
}
