package fun.jianjie.miniorder.exception;

public class MyException2 extends BaseException {
    private static final Integer code = 404;
    private static final String msg = "找不到资源";
    private static final String url = "错误路径";

    public MyException2() {
        super(code, msg, url);
    }

    public MyException2(String message) {
        super(code,message,url);
    }
}
