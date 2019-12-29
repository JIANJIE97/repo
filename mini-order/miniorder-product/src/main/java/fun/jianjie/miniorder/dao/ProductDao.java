package fun.jianjie.miniorder.dao;

import fun.jianjie.miniorder.domain.Product;
import fun.jianjie.miniorder.domain.ResultJson;
import fun.jianjie.miniorder.vo.DetailImageVo;
import fun.jianjie.miniorder.vo.ImageVo;
import fun.jianjie.miniorder.vo.ProductVo;
import fun.jianjie.miniorder.vo.PropertiesVo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
public interface ProductDao extends tk.mybatis.mapper.common.Mapper<Product>{
    /**
     * 根据分类id查询商品
     * @param category_id
     * @return
     */
    @Select("SELECT * FROM product WHERE category_id = #{category_id}")
    public List<Product> findProductByTypeId(Integer category_id);

    /**
     * 根据商品id查询对应商品
     * @param id
     * @return
     */
    @Select("select * from product where id = #{id}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "imgs",column = "id",
                    many = @Many(
                            select = "fun.jianjie.miniorder.dao.ProductDao.findImageByProductId",
                            fetchType = FetchType.LAZY
                    )),
            @Result(property = "properties",column = "id",
                    many = @Many(
                            select = "fun.jianjie.miniorder.dao.ProductDao.findPropertyProductId",
                            fetchType = FetchType.LAZY
                    )
            )
    })
    public ProductVo findProductById(Integer id);

    /**
     * 附属查询SQL
     * 根据商品id查询对应的详细图片信息
     * @param p_id
     * @return
     */
    @Select("select * from product_image where product_id = #{p_id}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "order",column = "order"),
            @Result(property = "img_url",column = "img_id",
                one = @One(
                        select = "fun.jianjie.miniorder.dao.ProductDao.findImageById"
                )
            )
    })
    public List<DetailImageVo> findImageByProductId(Integer p_id);

    /**
     * 附属查询
     * 根据商品id查询商品信息
     * @param pid
     * @return
     */
    @Select("select * from product_property where product_id = #{p_id}")
    public List<PropertiesVo> findPropertyProductId(Integer pid);
    /**
     * 附属查询SQL
     * 根据图片id查询图片路径
     * @param id
     * @return
     */
    @Select("select * from image where id = #{id}")
    @Results({
            @Result(property = "url",column = "url")
    })
    public ImageVo findImageById(Integer id);

    /**
     * 根据商品id查询库存量
     * @param id
     * @return
     */
    @Select("select stock from product where id = #{id}")
    public Integer findStockById(Integer id);

}
