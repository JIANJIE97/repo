package fun.jianjie.miniorder.service.impl;

import fun.jianjie.miniorder.dao.ProductDao;
import fun.jianjie.miniorder.domain.Product;
import fun.jianjie.miniorder.service.ProductService;
import fun.jianjie.miniorder.vo.DetailImageVo;
import fun.jianjie.miniorder.vo.ImageVo;
import fun.jianjie.miniorder.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {
    private static final String IMG_URL_PREFIX = "http://localhost:8888/img";
    @Resource
    private ProductDao productDao;

    /**
     * 根据分类id查看商品
     * @param category_id
     * @return
     */
    @Override
    public List<Product> findProductByTypeId(Integer category_id) {
        List<Product> productByTypeId = productDao.findProductByTypeId(category_id);
        for (Product product : productByTypeId) {
            String main_img_url = product.getMain_img_url();
            product.setMain_img_url("http://127.0.0.1:8888/img"+main_img_url);
        }
        return productByTypeId;
    }

    /**
     * 根据id查看商品
     * @param id
     * @return
     */
    @Override
    public ProductVo findProductById(Integer id) {
        //为主图片添加路径前缀
        ProductVo productVo= productDao.findProductById(id);
        String URL = productVo.getMain_img_url();
        productVo.setMain_img_url(IMG_URL_PREFIX+URL);

        //为详细图片添加路径前缀
        List<DetailImageVo> imgs = productVo.getImgs();
        for (DetailImageVo img : imgs) {
            ImageVo img_url_obj = img.getImg_url();
            String url = img_url_obj.getUrl();
            img_url_obj.setUrl(IMG_URL_PREFIX+url);
        }
        return productVo;
    }


    /**
     * 根据商品id获取商品库存量
     * @param id
     * @return
     */
    @Override
    public Integer findStockById(Integer id) {
        return productDao.findStockById(id);
    }
}
