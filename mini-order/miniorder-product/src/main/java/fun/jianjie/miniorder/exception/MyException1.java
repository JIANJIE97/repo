package fun.jianjie.miniorder.exception;

public class MyException1 extends BaseException {
    private static final Integer code = 400;
    private static final String msg = "参数错误";
    private static final String url = "错误路径";


    public MyException1() {
        super(code, msg, url);
    }

    public MyException1(String message) {
        super(code,message,url);
    }
}
