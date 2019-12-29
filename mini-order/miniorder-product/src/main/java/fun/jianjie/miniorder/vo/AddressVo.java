package fun.jianjie.miniorder.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(value = {"handler"})
public class AddressVo implements Serializable {
    private String name;
    private String mobile;
    private String province;
    private String city;
    private String country;
    private String detail;
    private Integer update_time;

    @Override
    public String toString() {
        return "AddressVo{" +
                "name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", detail='" + detail + '\'' +
                ", update_time=" + update_time +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Integer update_time) {
        this.update_time = update_time;
    }
}
