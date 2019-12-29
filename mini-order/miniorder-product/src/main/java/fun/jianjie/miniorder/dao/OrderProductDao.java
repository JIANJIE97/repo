package fun.jianjie.miniorder.dao;


import fun.jianjie.miniorder.domain.OrderProduct;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderProductDao {
    @Insert("insert into order_product (order_id,product_id,count) values (#{order_id},#{product_id},#{count})")
    public Integer saveOrderProducts(OrderProduct orderProduct);
}
