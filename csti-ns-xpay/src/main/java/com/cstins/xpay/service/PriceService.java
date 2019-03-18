package com.cstins.xpay.service;

import com.cstins.xpay.dao.PriceDao;
import com.cstins.xpay.entity.Integral_price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: csti-ns
 * @description:
 * @author: 杨云龙
 **/

@Service
public class PriceService {

    @Autowired
    private PriceDao priceDao;

    /**
     * 获取价格列表
     * @return
     */
    public List<Integral_price> getAll() {
        return priceDao.findAll();
    }

    /**
     * 根据人民币查询积分
     * @param rmb
     * @return
     */
    public Integer findByMoney(Integer rmb) {
        return priceDao.findByRmbEquals(rmb).getRadd();
    }

}
