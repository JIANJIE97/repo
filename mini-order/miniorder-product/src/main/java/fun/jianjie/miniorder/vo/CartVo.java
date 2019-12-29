package fun.jianjie.miniorder.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;


@JsonIgnoreProperties(value = {"handler"})
public class CartVo implements Serializable {
    private List<CartProductVo> products;

    public List<CartProductVo> getProducts() {
        return products;
    }

    public void setProducts(List<CartProductVo> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "CartVo{" +
                "products=" + products +
                '}';
    }
}
