package fun.jianjie.miniorder.service.impl;

import fun.jianjie.miniorder.dao.CategoryDao;
import fun.jianjie.miniorder.service.CategoryService;
import fun.jianjie.miniorder.vo.CategoryVo;
import fun.jianjie.miniorder.vo.ImageVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static final String IMG_URL_PREFIX = "http://localhost:8888/img";

    @Resource
    private CategoryDao categoryDao;

    /**
     * 查询所有分类的信息包含图片链接http://localhost:8888/img/category/rice.png
      * @return
     */
    @Override
    public List<CategoryVo> findAll() {
        List<CategoryVo> categoryVos= categoryDao.findAll();
        for (CategoryVo categoryVo : categoryVos) {
            ImageVo imageVo = categoryVo.getImg();
            StringBuilder stringBuilder = new StringBuilder();
            String URL = stringBuilder.append(IMG_URL_PREFIX).append(imageVo.getUrl()).toString();
            imageVo.setUrl(URL);
        }
        return categoryVos;
    }
}
