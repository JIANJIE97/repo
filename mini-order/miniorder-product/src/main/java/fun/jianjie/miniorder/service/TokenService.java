package fun.jianjie.miniorder.service;

public interface TokenService {

    /**
     * 初始化访问接口的地址
     */
    public void initParameter(String code);

    /**
     * 访问微信服务器接口获取oppenid并制作令牌
     */
    public String getUserInfo(String code) throws Exception;

    /**
     * 登录验证
     * @param string
     * @return
     */
    public boolean verifyToken(String string);

    /**
     * 根据请求头中的token获取当前UID
     * @param token
     * @return
     */
    public Object getCurrentUid(String token,String key) throws Exception;
}
