package fun.jianjie.miniorder.service;

import fun.jianjie.miniorder.vo.CategoryVo;

import java.util.List;
import java.util.Map;

public interface CategoryService {


    /**
     * 查询所有分类的信息包含图片链接
     * @return
     */
    public List<CategoryVo> findAll();
}
