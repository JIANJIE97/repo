package fun.jianjie.miniorder.service.impl;

import fun.jianjie.miniorder.dao.UserDao;
import fun.jianjie.miniorder.domain.User;
import fun.jianjie.miniorder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;


    /**
     * 根据openid查询用户信息
     * @param openid
     * @return
     */
    @Override
    public User findUserByOpenid(String openid) {
        return userDao.findUserByOpenid(openid);
    }


    /**
     * 把用户信息添加到用户表的一条记录中
     * @param user
     * @return
     */
    @Override
    @Transactional
    public boolean saveUser(User user) {
        return userDao.saveUser(user);
    }


    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @Override
    public User findUserById(Integer id) {
        return userDao.findUserById(id);
    }
}
