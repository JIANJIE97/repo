package fun.jianjie.miniorder.service;

import fun.jianjie.miniorder.domain.Product;
import fun.jianjie.miniorder.vo.ProductVo;

import java.util.List;

public interface ProductService {
    public List<Product> findProductByTypeId(Integer id);

    public ProductVo findProductById(Integer id);

    public Integer findStockById(Integer id);
}
