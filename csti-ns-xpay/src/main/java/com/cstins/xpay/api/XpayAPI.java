package com.cstins.xpay.api;

import com.cstins.xpay.entity.Integral_price;
import com.cstins.xpay.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: csti-ns
 * @description:
 * @author: 杨云龙
 **/

@RestController
@RequestMapping("/xpay")
public class XpayAPI {

    @Autowired
    private PriceService priceService;

    @GetMapping("/prices")
    public List<Integral_price> getAll() {
        return priceService.getAll();
    }

}
