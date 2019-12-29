package fun.jianjie.miniorder.dao;


import fun.jianjie.miniorder.domain.UserAddress;
import fun.jianjie.miniorder.vo.AddressVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AddressDao{

    @Select("select * from user_address where user_id = #{uid}")
    public AddressVo findAddressByUid(Integer uid);

    @Insert("insert into user_address (name,mobile,province,city,country,detail,user_id) values(#{name},#{mobile},#{province},#{city},#{country},#{detail},#{user_id})")
    public Integer saveAddress(UserAddress address);


    @Update("update user_address set name=#{name},mobile =#{mobile},province=#{province},city=#{city},country=#{country},detail=#{detail} where user_id = #{user_id}")
    public Integer updateAddress(UserAddress address);
}
