package fun.jianjie.miniorder.dao;


import com.github.pagehelper.Page;
import fun.jianjie.miniorder.domain.Order;
import fun.jianjie.miniorder.vo.OrderProductVo;
import fun.jianjie.miniorder.vo.OrderVo;
import fun.jianjie.miniorder.vo.SummaryOrderVo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface OrderDao {


    /**
     * 根据uid查询历史订单
     * @param uid
     * @return
     */
    @Select("select * from `order` where user_id = #{uid} order by create_time desc")
    public Page<OrderVo> findSummaryOrder(Integer uid);

    /**
     * 根据uid和oid查询历史订单详情信息
     * @param uid
     * @param oid
     * @return
     */
    @Select("select * from `order` where user_id = #{uid} and id =#{oid}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "snap_items",column = "id",
                    many = @Many(
                            select = "fun.jianjie.miniorder.dao.OrderDao.findOrderProduct",
                            fetchType = FetchType.LAZY
                    )),
            @Result(property = "snap_address",column = "user_id",
                    one = @One(select = "fun.jianjie.miniorder.dao.AddressDao.findAddressByUid"))
    })
    public OrderVo findOrderByOid(@Param("uid") Integer uid, @Param("oid") Integer oid);


    /**
     *   private Integer id;
         private Boolean haveStock;
         private Integer count;
         private BigDecimal price;
         private String name;
         private BigDecimal totalPrice;
         private String main_img_url;
     *
     *
     * 根据订单id获取订单商品信息列表
     * @param oid
     * @return
     */
    @Select("SELECT p.id,op.count,p.price,p.name,p.main_img_url FROM order_product op,product p WHERE op.product_id = p.id AND op.order_id = #{oid}")
    /*@Results({
            @Result(property = "id",column = ""),
            @Result(property = "count",column = ""),
            @Result(property = "price",column = ""),
            @Result(property = "name",column = ""),
            @Result(property = "main_img_url",column = "")
    })*/
    public List<OrderProductVo> findOrderProduct(Integer oid);

    /**
     * 增加一条订单记录
     * @param order
     * @return
     */

    @Insert("insert into `order` (order_no,user_id,create_time,total_price,snap_img,snap_name,total_count,snap_items,snap_address) values(#{order_no},#{user_id},#{create_time},#{total_price},#{snap_img},#{snap_name},#{total_count},#{snap_items},#{snap_address})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    public int saveOrder(Order order);
}
