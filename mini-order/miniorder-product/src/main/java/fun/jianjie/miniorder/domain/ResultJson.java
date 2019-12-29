package fun.jianjie.miniorder.domain;

import java.io.Serializable;

public class ResultJson implements Serializable{
    public ResultJson() {
    }

    public ResultJson(Integer code, String msg, Object data) {

        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultJson{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    private Integer code;
    private String msg;
    private Object data;
}
