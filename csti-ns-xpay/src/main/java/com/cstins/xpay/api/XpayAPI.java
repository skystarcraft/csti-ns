package com.cstins.xpay.api;

import com.alibaba.fastjson.JSONObject;
import com.cstins.xpay.entity.Integral_price;
import com.cstins.xpay.service.AliPayService;
import com.cstins.xpay.service.OrderService;
import com.cstins.xpay.service.PriceService;
import com.cstins.xpay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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

    private Boolean result = false;

    @GetMapping("/prices")
    @ResponseBody
    public JSONObject getAll() {
        List<Integral_price> prices = priceService.getAll();
        JSONObject jsonObject = new JSONObject();
        if (prices == null) {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "获取列表失败！");
            jsonObject.put("data", "");
        } else {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "获取成功成功！");
            jsonObject.put("data", prices);
        }
        return jsonObject;
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
    public ModelAndView receiveData(@RequestParam("total_amount") String amount, @RequestParam("out_trade_no") String oid) {
        if (amount != null) {
            try {
                Integer money = new Double(Double.parseDouble(amount)).intValue();
                Integer score = priceService.findByMoney(money);
                Integer order_id = Integer.parseInt(oid);
                userService.addUintegral(score, uid);
                orderService.createOrder(order_id, uid, new Date(), money);
                uid = null;
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
                result = false;
            }
        }
        return new ModelAndView("redirect:http://localhost:8080/#/xpay");
//        return new ModelAndView("redirect:http://134.175.68.126:8080/#/xpay");
    }


    @GetMapping("/result")
    @ResponseBody
    public JSONObject result() {
        JSONObject jsonObject = new JSONObject();
        if (result) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "支付成功！");
            jsonObject.put("data", "");
        } else {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "支付失败！");
            jsonObject.put("data", "");
        }
        return jsonObject;
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
        JSONObject jsonObject = new JSONObject();
        String rnumber = orderService.getOrderId() + "";       //订单号
//        try {
//            String pay = payService.pay(rnumber, money);
//            jsonObject.put("code", 200);
//            jsonObject.put("msg", "支付成功！");
//            jsonObject.put("data", pay);
//        } catch (Exception e) {
//            e.printStackTrace();
//            jsonObject.put("code", 200);
//            jsonObject.put("msg", "支付成功！");
//            jsonObject.put("data", "");
//        }
        return payService.pay(rnumber, money);
    }
}
