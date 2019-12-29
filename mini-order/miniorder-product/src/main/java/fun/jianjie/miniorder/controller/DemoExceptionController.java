package fun.jianjie.miniorder.controller;


import fun.jianjie.miniorder.exception.BaseException;
import fun.jianjie.miniorder.exception.MyException1;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DemoExceptionController {
    @RequestMapping("ex1")
    public void throwException() throws Exception {
        int i = 1/0;
    }

    @RequestMapping("ex2")
    public Object throwException1() throws Exception {
        throw new MyException1();
    }
    @RequestMapping("ex3")
    public Object throwException2() throws Exception {
        throw new MyException1("这是MyException2");
    }
    @RequestMapping("ex4")
    public Object throwNull() throws Exception {
        throw new NullPointerException();
    }
    @RequestMapping("ex5")
    public void throwException5() throws Exception {
        throw new Exception("微信内部异常");
    }
}
