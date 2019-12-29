package fun.jianjie.miniorder.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import fun.jianjie.miniorder.domain.User;
import fun.jianjie.miniorder.enumpackage.ScopeEnum;
import fun.jianjie.miniorder.exception.WeChatException;
import fun.jianjie.miniorder.service.TokenService;
import fun.jianjie.miniorder.service.UserService;
import fun.jianjie.miniorder.utils.Md5Util;
import fun.jianjie.miniorder.utils.RedisUtil;
import fun.jianjie.miniorder.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Service
public class TokenServiceImpl implements TokenService {
    /*GET
    https://api.weixin.qq.com/sns/jscode2session?
    appid=APPID
    &secret=SECRET
    &js_code=JSCODE
    &grant_type=authorization_code
    */
    private String WX_APP_ID = "wx53ee16a091421f41";
    private String WX_APP_SECRET = "fcea20047c797a01c2b63280f8492c77";
    private String WX_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session?";
    private String TOKEN_SALT = "HHsTieBU377mJtKr";
    private long TOKEN_EXPIRE = 7200;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;

    public TokenServiceImpl() {

    }

    /**
     * 初始化访问接口的地址
     */
    @Override
    public void initParameter(String code) {
        WX_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session?";

        StringBuilder tempStr = new StringBuilder(WX_LOGIN_URL).
                append("appid=" + WX_APP_ID).
                append("&secret=" + WX_APP_SECRET).
                append("&js_code=" + code).
                append("&grant_type=authorization_code");
         WX_LOGIN_URL = tempStr.toString();
    }


    /**
     * 访问微信服务器接口获取oppenid并制作令牌
     */
    @Override
    public String getUserInfo(String code) throws Exception {
        String token = null;
        initParameter(code);
        Map<String,Object> resultMap = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        //System.out.println(WX_LOGIN_URL);
        String resultStr= restTemplate.getForObject(WX_LOGIN_URL,String.class);
        try {
            resultMap = objectMapper.readValue(resultStr,Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(resultMap.isEmpty()){
            //获取session_key和openID时异常，微信内部错误
            throw new Exception("获取session_key和openID时异常，微信内部错误");
        }else {
            if (resultMap.containsKey("errcode") || resultMap.containsKey("errmsg")){
                //虽然不为空，但是微信可能认为还有错误，会返回一个错误码给你
                throw new WeChatException(resultMap.get("errmsg").toString());
            }else{
                token =  grantToken(resultMap);
            }
        }
        return token;
    }



    /**
     * 使用openid制作令牌
     */
    public String grantToken(Map<String,Object> resultMap) {
        //1、获取openid
        //2、查看数据库中这个openid是否已经存在
        //3、存在了无需理会，不存在需要插入一条user记录
        //4、生成token令牌、准备缓存数据、写入缓存(key:token,value:map(openid+session_key+uid+scope))
        //5、把令牌返回客户端

        String uid = null;
        String openid = resultMap.get("openid").toString();

        User user = userService.findUserByOpenid(openid);
        if(user != null){
            //获取对应openid的uid
            uid = user.getId().toString();
        }else {
            //数据库中没有对应的用户数据，第一次登陆保存数据到数据库
            User newUser = new User();
            newUser.setOpenid(openid);
            userService.saveUser(newUser);
            uid = userService.findUserByOpenid(openid).getId().toString();
        }
        Map<String,Object> cacheValue = new HashMap<>();
        cacheValue = prepareCacheValue(resultMap,uid);
        return saveToCache(cacheValue);
    }


    /**
     * 准备缓存数据
     * @param resultMap
     * @param uid
     */
    public Map<String, Object> prepareCacheValue(Map<String, Object> resultMap, String uid) {
        resultMap.put("uid",uid);
        resultMap.put("scope", ScopeEnum.USER);
        return resultMap;
    }

    /**
     * 使用写入缓存
     * @param cacheValue
     */
    public String saveToCache(Map<String, Object> cacheValue) {
        /**
         * key:token
         * value:map(openid+session_key+uid+scope)
         */

        try {
            String token = Md5Util.encodeByMd5(UuidUtil.getUuid() + System.currentTimeMillis() + TOKEN_SALT);
            redisTemplate.opsForHash().putAll(token, cacheValue);
            redisTemplate.boundValueOps(token).expire(TOKEN_EXPIRE, TimeUnit.SECONDS);
            System.out.println(redisTemplate.opsForHash().entries(token));
            return token;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * 检测token是否有效
     * @param token
     * @return
     */
    @Override
    public boolean verifyToken(String token) {
        Map entries = redisTemplate.opsForHash().entries(token);
        if(entries == null || entries.size() == 0){
            return false;
        }else {
            return true;
        }
    }



    @Override
    public Object getCurrentUid(String token,String key) throws Exception {
        Map entries = redisTemplate.opsForHash().entries(token);
        if(entries == null || entries.size() == 0){
            throw new Exception("token不存在或者token已过期");
        }
        return entries.get(key);
    }
}
