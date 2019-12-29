package fun.jianjie.miniorder.service;


import fun.jianjie.miniorder.domain.UserAddress;
import fun.jianjie.miniorder.vo.AddressVo;

public interface AddressService {
    public AddressVo findAddressByUid(Integer uid);

    public boolean saveAddress(UserAddress address);

    public boolean updateAddress(UserAddress address);

}
