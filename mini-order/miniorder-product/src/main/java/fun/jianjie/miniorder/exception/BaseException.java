package fun.jianjie.miniorder.exception;

public class BaseException extends Exception{
    private Integer code;
    private String msg;
    private String url;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public BaseException(Integer code, String message, String url) {
        super(message);
        this.code = code;
        this.msg = message;
        this.url = url;
    }


    public BaseException(String message) {
        super(message);
    }
}
