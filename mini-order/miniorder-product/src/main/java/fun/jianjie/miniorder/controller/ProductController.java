package fun.jianjie.miniorder.controller;


import fun.jianjie.miniorder.domain.Product;
import fun.jianjie.miniorder.domain.ResultJson;
import fun.jianjie.miniorder.service.ProductService;
import fun.jianjie.miniorder.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("by_category")
    public ResponseEntity<?> getAllInCategory(@RequestParam("id") Integer id) throws Exception {
        if(id == null || id<0){
            throw new Exception("参数错误,参数id不能为空也不能小于0");
        }
        List<Product> productsByTypeId = productService.findProductByTypeId(id);
        if(CollectionUtils.isEmpty(productsByTypeId)){
            throw new Exception("查询结果为空");
        }
        return ResponseEntity.ok(productsByTypeId);
    }
    @GetMapping("{id}")
    public ResponseEntity<?> findProductById(@PathVariable(value = "id",required = true) Integer id) throws Exception {
        if(id == null || id < 0){
            throw new Exception("参数错误,参数id不能为空也不能小于0");
        }
        ProductVo productVo = productService.findProductById(id);
        return ResponseEntity.ok(productVo);
    }
}
