package com.cstins.xpay.dao;

import com.cstins.xpay.entity.OrderPay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDao extends JpaRepository<OrderPay, Integer> {

    /**
     * 获取单个用户的所有订单
     * @param uid
     * @return
     */
    List<OrderPay> findAllByUidEquals(Integer uid);

    @Query(value = "select max(order_id) from `orderpay`", nativeQuery = true)
    Integer getMaxOrder_id();
}
