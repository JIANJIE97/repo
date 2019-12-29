package fun.jianjie.miniorder.service;

import fun.jianjie.miniorder.domain.User;

public interface UserService {
    public User findUserByOpenid(String openid);
    public boolean saveUser(User user);

    public User findUserById(Integer id);
}
