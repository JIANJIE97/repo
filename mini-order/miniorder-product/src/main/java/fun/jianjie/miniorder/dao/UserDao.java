package fun.jianjie.miniorder.dao;


import fun.jianjie.miniorder.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao extends tk.mybatis.mapper.common.Mapper<User> {
    @Select("SELECT * FROM user WHERE openid = #{openid}")
    public User findUserByOpenid(String openid);

    @Insert("insert into user (openid) values(#{openid})")
    public boolean saveUser(User user);


    @Select("select * from user where id = #{id}")
    public User findUserById(Integer id);
}
