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

    public List<Integral_price> getAll() {
        return priceDao.findAll();
    }

}
