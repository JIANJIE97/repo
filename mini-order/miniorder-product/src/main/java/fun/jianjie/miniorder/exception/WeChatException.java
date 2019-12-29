package fun.jianjie.miniorder.exception;

public class WeChatException extends BaseException {
    private static final Integer code = 400;
    private static final String msg = "微信服务接口调用失败";
    private static final String url = "";


    public WeChatException() {
        super(code, msg, url);
    }

    public WeChatException(String message) {
        super(code,message,url);
    }
}
