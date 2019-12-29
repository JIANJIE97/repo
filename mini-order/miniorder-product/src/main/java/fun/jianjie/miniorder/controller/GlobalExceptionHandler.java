package fun.jianjie.miniorder.controller;


import fun.jianjie.miniorder.domain.ResultJson;
import fun.jianjie.miniorder.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@ControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 自定义异常捕获处理
     * @param req
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public ResponseEntity<?> baseErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        BaseException baseException = null;
        if(e instanceof BaseException){
            baseException = (BaseException) e;
        }
        ResultJson resultJson = new ResultJson(
                Integer.parseInt(String.valueOf(HttpStatus.valueOf(baseException.getCode()))),
                baseException.getMsg(),
                req.getRequestURL().toString());
        return new ResponseEntity<ResultJson>(resultJson, HttpStatus.valueOf(baseException.getCode()));
    }


    /**
     * 运行期异常处理
     * @param req
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity<?> ErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ResultJson resultJson = new ResultJson(
                500,
                e.getMessage(),
                req.getRequestURL().toString());
        return new ResponseEntity<ResultJson>(resultJson, HttpStatus.valueOf(500));
    }
}
