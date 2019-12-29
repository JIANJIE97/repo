package fun.jianjie.miniorder.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import fun.jianjie.miniorder.domain.User;
import fun.jianjie.miniorder.domain.UserAddress;
import fun.jianjie.miniorder.service.AddressService;
import fun.jianjie.miniorder.service.TokenService;
import fun.jianjie.miniorder.service.UserService;
import fun.jianjie.miniorder.vo.AddressVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController

public class AddressController {
    @Resource
    private AddressService addressService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @GetMapping("address")
    public ResponseEntity<?> findAddressByUid(@RequestHeader("token") String token) throws Exception {
        if(token == null){
            throw new Exception("请求头中需要token信息");
        }

        String currentUid = tokenService.getCurrentUid(token,"uid").toString();
        AddressVo addressByUid = addressService.findAddressByUid(Integer.parseInt(currentUid));
        if (addressByUid == null){
            throw new Exception("用户地址不存在");
        }
        return ResponseEntity.ok(addressByUid);
    }


    @PostMapping("address")
    public ResponseEntity createOrUpdateAddress(@RequestHeader("token") String token,@RequestBody(required = true) String dataArray) throws Exception {
        /**
         * {
         * "name": "张三",
         * "mobile": "020-81167888",
         * "province": "广东省",
         * "city": "广州市",
         * "country": "海珠区",
         * "detail": "新港中路397号"
         * }
         */
        if(token ==null){
            throw new Exception("请求头中需要token信息");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        UserAddress address = objectMapper.readValue(dataArray, UserAddress.class);
        Integer uid = Integer.parseInt(tokenService.getCurrentUid(token, "uid").toString());
        address.setUser_id(uid);
        System.out.println(address);


        User user = userService.findUserById(uid);
        if(user == null){
            throw new Exception("用户不存在");
        }
        AddressVo addressVo = addressService.findAddressByUid(uid);
        boolean flag = false;
        if(addressVo == null){
            //添加一条记录
            flag = addressService.saveAddress(address);
        }else{
            //不为空，更新记录
            flag = addressService.updateAddress(address);
        }


        return ResponseEntity.ok(flag);
    }
}
