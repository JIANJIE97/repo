package fun.jianjie.miniorder.service.impl;

import fun.jianjie.miniorder.dao.AddressDao;
import fun.jianjie.miniorder.domain.UserAddress;
import fun.jianjie.miniorder.service.AddressService;
import fun.jianjie.miniorder.vo.AddressVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class AddressServiceImpl implements AddressService {

    @Resource
    private AddressDao addressDao;


    /**
     * 根据用户id查询用户地址
     * @param uid
     * @return
     */
    @Override
    public AddressVo findAddressByUid(Integer uid) {
        return addressDao.findAddressByUid(uid);
    }

    /**
     * 根据地址信息保存到地址表中
     * @param address
     */
    @Override
    @Transactional
    public boolean saveAddress(UserAddress address) {
        Integer count = addressDao.saveAddress(address);

        return count > 0 ? true:false;
    }

    /**
     * 根据地址信息更新地址表信息
     * @param address
     */
    @Override
    @Transactional
    public boolean updateAddress(UserAddress address) {
        Integer count = addressDao.updateAddress(address);
        return count > 0 ? true:false;
    }
}
