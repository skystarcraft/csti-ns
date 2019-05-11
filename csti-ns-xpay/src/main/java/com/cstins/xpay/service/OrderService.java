package com.cstins.xpay.service;

import com.cstins.xpay.dao.OrderDao;
import com.cstins.xpay.entity.OrderPay;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @program: csti-ns
 * @description: 订单相关服务
 * @author: 杨云龙
 **/

@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    private static final Logger logger = LogManager.getLogger(OrderService.class);

    /**
     * 创建订单
     * @param uid
     * @param date
     * @param imoney
     * @return
     */
    public boolean createOrder(Integer oid, Integer uid, Date date, Integer imoney) {
        OrderPay orderPay = new OrderPay(oid, uid, new java.sql.Date(date.getTime()), imoney);
        orderDao.save(orderPay);
        return true;
    }

    /**
     * 查询单个用户的订单情况
     * @return
     */
    public List<OrderPay> getOrdersByUid(Integer uid) {
        return orderDao.findAllByUidEquals(uid);
    }

    /**
     * 获取当前订单id的最大值，该结果将用在支付接口中的订单号(id + 1)
     * @return
     */
    public Integer getOrderId() {

         return (int)((Math.random()*9+1)*10000000);
//        return orderDao.getMaxOrder_id() + 1;
    }

}
