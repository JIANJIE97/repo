package fun.jianjie.miniorder.dao;

import fun.jianjie.miniorder.domain.Category;
import fun.jianjie.miniorder.domain.ImageDomain;
import fun.jianjie.miniorder.vo.CategoryVo;
import fun.jianjie.miniorder.vo.ImageVo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Map;

@Mapper
public interface CategoryDao {
    /**
     * 查询所有分类信息
     * @return
     */
    @Select("select * from category")
    @Results({
            @Result(id = true,property = "id", column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "img" ,column = "topic_img_id",
                one = @One(
                        select = "fun.jianjie.miniorder.dao.CategoryDao.findImageById"
                )
            )

    })
    public List<CategoryVo> findAll();

    /**
     * 附属SQL根据id查询图片路径
     * @param id
     * @return
     */
    @Select("select * from image where id = #{id}")
    @Results({
            @Result(property = "url",column = "url")
    })
    public ImageVo findImageById(Integer id);
}
