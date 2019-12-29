package fun.jianjie.miniorder.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fun.jianjie.miniorder.domain.Category;
import fun.jianjie.miniorder.service.CategoryService;
import fun.jianjie.miniorder.vo.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("all")
    public ResponseEntity<?> getAllInCategory() throws JsonProcessingException {
        List<CategoryVo> all = categoryService.findAll();
        return ResponseEntity.ok(all);
    }
}
