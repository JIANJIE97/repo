package fun.jianjie.miniorder.service;

import fun.jianjie.miniorder.domain.Order;
import fun.jianjie.miniorder.vo.*;

import java.util.List;

public interface OrderService {

    public SummaryOrderVo findSummaryOrder(Integer uid,int page,int pageSize) throws Exception;

    public  OrderVo findOrderByOid(Integer uid, Integer oid) throws Exception;

    public List<OrderProductVo> findOrderProduct(Integer oid);

    public Object place(Integer uid, CartVo cartVo) throws Exception;
}
