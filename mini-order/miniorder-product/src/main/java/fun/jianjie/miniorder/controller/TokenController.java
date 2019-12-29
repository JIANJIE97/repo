package fun.jianjie.miniorder.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import fun.jianjie.miniorder.domain.ResultJson;
import fun.jianjie.miniorder.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("token")
public class TokenController {
    @Autowired
    private TokenService tokenService;

    @PostMapping("user")
    public ResponseEntity<?> getToken(@RequestBody(required = true) String parameter) throws Exception {
        //System.out.println("访问微信接口获取token值");

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,Object> parameters = new HashMap<>();
        try {
            parameters = objectMapper.readValue(parameter,Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(parameters);
        if(!parameters.containsKey("code") ||
                parameters.get("code") == null ||
                "".equals(parameters.get("code").toString())){
            throw new Exception("参数格式错误,请携带code信息");
        }
        String token =tokenService.getUserInfo(parameters.get("code").toString());
        if(token == null || "".equals(token)){
            throw new Exception("token已过期或无效token");
        }
        Map<String,Object> tokens = new HashMap<>();
        tokens.put("token",token);
        return ResponseEntity.ok(tokens);
    }

    @PostMapping("verify")
    public ResponseEntity<?> verifyToken(@RequestBody(required = true) String parameter) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,Object> parameters = new HashMap<>();
        try {
            parameters = objectMapper.readValue(parameter,Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!parameters.containsKey("token") ||
                parameters.get("token") == null ||
                "".equals(parameters.get("token").toString())){
            throw new Exception("请携带token信息");
        }
        boolean flag = tokenService.verifyToken(parameters.get("token").toString());
        Map<String,Boolean> isValid = new HashMap<>();
        isValid.put("isValid",flag);
        return ResponseEntity.ok(isValid);
    }
}
