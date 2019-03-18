package com.cstins.xpay.dao;

import com.cstins.xpay.entity.Integral_price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceDao extends JpaRepository<Integral_price, Integer> {

    Integral_price findByRmbEquals(Integer rmb);

}
