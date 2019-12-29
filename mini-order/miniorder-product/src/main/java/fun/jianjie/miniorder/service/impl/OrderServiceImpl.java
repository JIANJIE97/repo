package fun.jianjie.miniorder.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import fun.jianjie.miniorder.dao.OrderDao;
import fun.jianjie.miniorder.dao.ProductDao;
import fun.jianjie.miniorder.domain.Order;
import fun.jianjie.miniorder.domain.OrderProduct;
import fun.jianjie.miniorder.dto.OrderProductDTO;
import fun.jianjie.miniorder.dto.OrderSnapDTO;
import fun.jianjie.miniorder.dto.OrderStatusDTO;
import fun.jianjie.miniorder.dto.ProductDTO;
import fun.jianjie.miniorder.service.AddressService;
import fun.jianjie.miniorder.service.OrderProductService;
import fun.jianjie.miniorder.service.OrderService;
import fun.jianjie.miniorder.service.ProductService;
import fun.jianjie.miniorder.utils.DateAndTimeStampUtil;
import fun.jianjie.miniorder.utils.OrderIDUtil;
import fun.jianjie.miniorder.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {
    private static final String IMG_URL_PREFIX = "http://localhost:8888/img";

    @Resource
    private OrderDao orderDao;
    @Resource
    private ProductDao productDao;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private OrderProductService orderProductService;

    private Integer uid;
    private CartVo cartVo;
    private List<ProductDTO> products;
    private List<OrderProductDTO> orderProducts;


    /**
     * 分页查询历史订单列表
     * @param uid
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public SummaryOrderVo findSummaryOrder(Integer uid,int page,int pageSize) throws Exception {
        //返回一个List类型的OrderVo数据
        Page<OrderVo> orderVoPage = orderDao.findSummaryOrder(uid);
        if (orderVoPage == null) {
            throw new RuntimeException("分页查询历史订单数据为空");
        }
        //System.out.println(orderVoPage);
        //把page类型转换为PageInfo类型，因为PageInfo包含当前页和其他参数数据
        PageInfo<OrderVo> pageInfo = new PageInfo<>(orderVoPage);

        //把pageinfo转换为自定义类型
        SummaryOrderVo summaryOrderVo = new SummaryOrderVo();
        if(page > pageInfo.getPageNum()){
            summaryOrderVo.setData(null);
            summaryOrderVo.setCurrent_page(page);
        }else{
            List<OrderVo> result = pageInfo.getList();
            for (OrderVo orderVo : result) {
                orderVo.setSnap_img(IMG_URL_PREFIX + orderVo.getSnap_img());
            }
            summaryOrderVo.setData(result);
            summaryOrderVo.setCurrent_page(pageInfo.getPageNum());
        }
        return summaryOrderVo;
    }

    /**
     * 根据用户id和订单id查询历史订单详细信息
     * @param uid
     * @param oid
     * @return
     */
    @Override
    public OrderVo findOrderByOid(Integer uid, Integer oid) throws Exception{
        //System.out.println("uid:"+uid+"oid:"+oid);
        OrderVo orderVo = orderDao.findOrderByOid(uid, oid);
        if(orderVo == null){
            throw new RuntimeException("没有对应订单表信息");
        }

        //把时间戳格式的时间转化为日期格式的字符串
        orderVo.setCreate_time(DateAndTimeStampUtil.timeStampToString(Long.parseLong(orderVo.getCreate_time())));




        orderVo.setSnap_img(IMG_URL_PREFIX+orderVo.getSnap_img());
        //System.out.println("orderVo前:"+orderVo);

        //存在三个字段需要业务处理(haveStock、totalPrice、Main_img_url)
        List<OrderProductVo> orderProducts = orderService.findOrderProduct(oid);
        //System.out.println("orderProducts:"+orderProducts);
        orderVo.setSnap_items(orderProducts);
        //System.out.println("orderVo后:"+orderVo);
        return orderVo;
    }


    /**
     * 根据订单id查询订单商品信息(把对应的订单商品都取出来赋值haveStock、totalPrice、还有商品图片路径)
     * @param oid
     * @return
     */
    @Override
    public List<OrderProductVo> findOrderProduct(Integer oid) {
        List<OrderProductVo> orderProducts = orderDao.findOrderProduct(oid);

        BigDecimal count = BigDecimal.valueOf(0);
        BigDecimal price = BigDecimal.valueOf(0);
        Integer stock = 0;
        for (OrderProductVo orderProduct : orderProducts) {
            stock = productService.findStockById(orderProduct.getId());
            //haveStock
            if (orderProduct.getCount()>stock) {
                orderProduct.setHaveStock(false);
            }else {
                orderProduct.setHaveStock(true);
            }

            count = BigDecimal.valueOf(orderProduct.getCount());
            price = orderProduct.getPrice();
            //totalPrice
            orderProduct.setTotalPrice(count.multiply(price));
            //Main_img_url
            orderProduct.setMain_img_url(IMG_URL_PREFIX+orderProduct.getMain_img_url());
        }
        //System.out.println(orderProduct);
        return orderProducts;
    }


    /**
     * 根据用户id和购物车商品生成订单
     * @param uid
     * @param cartVo
     * @return
     * 生成订单的主方法
     *
     * 购物车列表
     * [
     *  CartProductDTO:{
     *    product_id:1
     *    count:1
     *  },
     *  CartProductDTO:{
     *    product_id:2
     *    count:1
     *  }
     * ]
     *
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public Object place(Integer uid, CartVo cartVo) throws Exception {
        //初始化成员变量
        this.uid = uid;
        this.cartVo = cartVo;
        //根据购物车列表获取真实商品列表
        this.products = getProductsByCartProduct();
        //根据购物车列表和真实商品列表获取订单商品列表
        this.orderProducts = getOrderProductByCartProductAndProduct();
        System.out.println("购物车数据："+this.cartVo.getProducts());
        System.out.println("真实商品数据："+this.products);
        System.out.println("订单商品数据："+this.orderProducts);

        /**
         private Boolean pass;
         private BigDecimal orderPrice;
         private Integer totalCount;
         private List<OrderProductDTO> orderProductList;
         */
        //1、查询订单状态是否通过(库存量是否充足),返回订单状态列表(pass+orderPrice+totalCount+orderItem)
        OrderStatusDTO orderStatusDTO = getOrderStatus();
        System.out.println("订单状态："+orderStatusDTO);
        OrderResultVo orderResultVo = new OrderResultVo();
        if(!orderStatusDTO.getPass()){
            orderStatusDTO.setOrder_id(-1);
            return orderStatusDTO;
        }
        //2、生成订单快照(准备快照信息)
        OrderSnapDTO orderSnapDTO = snapOrder(orderStatusDTO);
        System.out.println("订单快照信息:"+orderSnapDTO);
        //3、创建订单(先插入订单表记录(包含快照信息)+再插入订单项表记录)
        orderResultVo= createOrder(orderSnapDTO);


        return orderResultVo;
    }

    /**
     * 创建订单(先插入订单表记录(包含快照信息)+再插入订单项表记录)一对多的关系
     * @param orderSnapDTO
     * @return
     * order_no,
     * order_id,
     * create_time,
     * pass;
     *
     */
    private OrderResultVo createOrder(OrderSnapDTO orderSnapDTO) throws Exception {
        OrderResultVo orderResultVo = new OrderResultVo();
        Order order = new Order();
        OrderProduct orderProduct = new OrderProduct();
        order.setUser_id(this.uid);
        order.setOrder_no(OrderIDUtil.doInvoke());
        order.setTotal_price(orderSnapDTO.getOrderPrice());
        order.setTotal_count(orderSnapDTO.getTotalCount());
        order.setSnap_img(orderSnapDTO.getSnapImg());
        order.setSnap_name(orderSnapDTO.getSnapName());
        order.setSnap_address(orderSnapDTO.getSnapAddress().toString());
        order.setSnap_items(orderSnapDTO.getOrderProductList().toString());
        order.setCreate_time(new Date().getTime());


        System.out.println("订单表："+order);
        //插入主订单表数据
        int num = orderDao.saveOrder(order);
        int oid = order.getId();
        System.out.println(oid);
        OrderVo orderByOid = orderService.findOrderByOid(this.uid, oid);
        if(orderByOid == null){
            throw new RuntimeException("生成订单项记录失败，没有对应的订单记录");
        }

        if(num<=0){
            throw new RuntimeException("生成订单失败");
        }

        long create_time = order.getCreate_time();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(create_time);


        //遍历订单商品插入订单项数据
        for (OrderProductDTO product : this.orderProducts) {
            orderProduct.setOrder_id(oid);
            orderProduct.setCount(product.getCount());
            orderProduct.setProduct_id(product.getId());
            orderProductService.saveOrderProducts(orderProduct);
        }

        orderResultVo.setOrder_no(order.getOrder_no());
        orderResultVo.setOrder_id(oid);
        orderResultVo.setCreate_time(simpleDateFormat.format(date));
        orderResultVo.setPass(true);
        return orderResultVo;
    }

    /**
     * 根据购物车商品列表获取真实商品列表
     * @return
     *
     * 真实商品列表
     * [
     *  ProductDTO:{
     *    id:1,
     *    price:0.01,
     *    stock:999,
     *    name:"aaa",
     *    main_img_url:"/a/b/c",
     *  },
     *  ProductDTO:{
     *    id:1,
     *    price:0.01,
     *    stock:999,
     *    name:"aaa",
     *    main_img_url:"/a/b/c",
     *  }
     * ]
     */
    private List<ProductDTO> getProductsByCartProduct() {
        List<CartProductVo> cartProductVos = this.cartVo.getProducts();

        List<ProductDTO> productDTOS = new ArrayList<>();
        ProductDTO productDTO = null;
        for (CartProductVo cartProductVo : cartProductVos) {
            productDTO = new ProductDTO();
            Integer id = cartProductVo.getProduct_id();
            //根据商品id查询数据库
            ProductVo productVo = productDao.findProductById(id);

            productDTO.setId(productVo.getId());
            productDTO.setPrice(productVo.getPrice());
            productDTO.setStock(productVo.getStock());
            productDTO.setName(productVo.getName());
            productDTO.setMain_img_url(IMG_URL_PREFIX+productVo.getMain_img_url());
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }

    /**
     * 根据购物车列表和真实商品列表获取订单商品列表
     * @return
     *
     * 订单商品列表
     * [
     *  OrderProductDTO:{
     *     id:1,
     *     haveStock:true,
     *     count:1,
     *     price:0.01,
     *     name:"aaa"
     *     totalPrice:0.01,
     *     main_img_url:"aaaaa"
     *  },
     *  OrderProductDTO:{
     *     id:1,
     *     haveStock:true,
     *     count:1,
     *     price:0.01,
     *     name:"aaa"
     *     totalPrice:0.01,
     *     main_img_url:"aaaaa"
     *  }
     * ]
     */
    public List<OrderProductDTO> getOrderProductByCartProductAndProduct(){
        List<CartProductVo> cartVoProducts = this.cartVo.getProducts();
        List<ProductDTO> products = this.products;

        List<OrderProductDTO> orderProducts = new ArrayList<>();
        OrderProductDTO orderProductDTO = null;

        System.out.println();
        for (int i = 0; i < products.size(); i++) {
            orderProductDTO = new OrderProductDTO();
            orderProductDTO.setId(products.get(i).getId());
            if (cartVoProducts.get(i).getCount() <= products.get(i).getStock()) {
                orderProductDTO.setHaveStock(true);
            } else {
                orderProductDTO.setHaveStock(false);
            }
            orderProductDTO.setCount(cartVoProducts.get(i).getCount());
            orderProductDTO.setPrice(products.get(i).getPrice());
            orderProductDTO.setName(products.get(i).getName());
            orderProductDTO.setTotalPrice(products.get(i).getPrice().multiply(BigDecimal.valueOf(cartVoProducts.get(i).getCount())));
            orderProductDTO.setMain_img_url(products.get(i).getMain_img_url());
            orderProducts.add(orderProductDTO);
        }
        return orderProducts;
    }


    /**
     * 查询订单状态是否通过(库存量是否充足)
     * @return
     * [
     *  OrderStatusDTO:{
     *      pass:true,
     *      orderPrice:0.02,
     *      totalCount:2,
     *      orderItem:[
     *          订单商品详情列表
     *      ]
     *  }
     * ]
     *
     */
    private OrderStatusDTO getOrderStatus() {
        OrderStatusDTO orderStatusDTO = new OrderStatusDTO();
        //初始化订单状态
        orderStatusDTO.setPass(true);
        orderStatusDTO.setOrderPrice(BigDecimal.valueOf(0));
        orderStatusDTO.setTotalCount(0);


        //System.out.println(orderStatusDTO);
        BigDecimal totalPrice = new BigDecimal("0");
        Integer count = 0;
        for (OrderProductDTO orderProduct : this.orderProducts) {
            if(!orderProduct.getHaveStock()){
                //库存不足
                orderStatusDTO.setPass(false);
            }
            totalPrice = totalPrice.add(orderProduct.getTotalPrice());
            count+=orderProduct.getCount();
        }
        /*System.out.println("订单总价格："+totalPrice);
        System.out.println("订单商品数量"+count);*/
        orderStatusDTO.setOrderPrice(totalPrice);
        orderStatusDTO.setTotalCount(count);
        orderStatusDTO.setpStatusArray(this.orderProducts);


        return orderStatusDTO;
    }


    /**
     * 生成订单快照(准备快照信息)
     * @param orderStatusDTO
     * @return
     *
     *
     * orderPrice:0,//订单总价格
     * totalCount:0,//商品总数量
     * orderProductList:[],//订单商品列表
     * snapAddress:AddressVo,//商品地址
     * snapName:"",//商品名称
     * snapImg:"",//商品图片地址
     */
    private OrderSnapDTO snapOrder(OrderStatusDTO orderStatusDTO) {
        OrderSnapDTO orderSnapDTO = new OrderSnapDTO();
        orderSnapDTO.setOrderPrice(orderStatusDTO.getOrderPrice());
        orderSnapDTO.setTotalCount(orderStatusDTO.getTotalCount());
        orderSnapDTO.setSnapAddress(addressService.findAddressByUid(this.uid));

        List<OrderProductDTO> orderProducts = orderStatusDTO.getpStatusArray();
        for (OrderProductDTO orderProduct : orderProducts) {
            orderProduct.setMain_img_url(orderProduct.getMain_img_url().substring(IMG_URL_PREFIX.length()));
        }
        System.out.println("订单商品快照："+orderProducts);
        orderSnapDTO.setOrderProductList(orderProducts);


        if(orderStatusDTO.getpStatusArray().size()>1){
            orderSnapDTO.setSnapName(orderStatusDTO.getpStatusArray().get(0).getName()+"等");
        }else{
            orderSnapDTO.setSnapName(orderStatusDTO.getpStatusArray().get(0).getName());
        }
        orderSnapDTO.setSnapImg(orderStatusDTO.getpStatusArray().get(0).getMain_img_url());
        return orderSnapDTO;
    }
}
