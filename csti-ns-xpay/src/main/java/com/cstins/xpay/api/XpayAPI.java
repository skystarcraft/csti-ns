package com.cstins.xpay.api;

import com.cstins.xpay.entity.Integral_price;
import com.cstins.xpay.service.AliPayService;
import com.cstins.xpay.service.OrderService;
import com.cstins.xpay.service.PriceService;
import com.cstins.xpay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @program: csti-ns
 * @description:
 * @author: 杨云龙
 **/

@Controller
@RequestMapping("/xpay")
public class XpayAPI {

    @Autowired
    private PriceService priceService;

    @Autowired
    private UserService userService;

    @Autowired
    private AliPayService payService;

    @Autowired
    private OrderService orderService;

    private Integer uid;

    @GetMapping("/prices")
    @ResponseBody
    public List<Integral_price> getAll() {
        return priceService.getAll();
    }

    /**
     * 支付接口回调-通知
     */
    @GetMapping("/notify")
    public void AliPayNotify() {
    }

    /**
     * 支付接口回调函数
     * @param amount     支付的钱数
     * @return
     */
    @GetMapping("/receive")
    @ResponseBody
    public boolean receiveData(@RequestParam("total_amount") String amount, @RequestParam("out_trade_no") String oid) {
        if (amount != null) {
            try {
                Integer money = new Double(Double.parseDouble(amount)).intValue();
                Integer score = priceService.findByMoney(money);
                Integer order_id = Integer.parseInt(oid);
                userService.addUintegral(score, uid);
                orderService.createOrder(order_id, uid, new Date(), money);
                uid = null;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    /**
     * 调用支付宝支付接口
     * @param uid           用户id
     * @param money         订单金钱
     * @return
     */
    @GetMapping("/pay/{uid}/{money}")
    @ResponseBody
    public String pay(@PathVariable("uid") Integer uid,  @PathVariable("money") String money) {
        this.uid = uid;
        String rnumber = orderService.getOrderId() + "";       //订单号
        return payService.pay(rnumber, money);
    }
}
