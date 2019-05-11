package com.cstins.personal.service;

import com.cstins.personal.dao.DownloadredordDao;
import com.cstins.personal.dao.OrderpayDao;
import com.cstins.personal.entity.Downloadrecord;
import com.cstins.personal.entity.Orderpay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: csti-ns
 * @description: 记录相关服务
 * @author: 杨云龙
 **/

@Service
public class RecordService {

    @Autowired
    private DownloadredordDao downloadredordDao;

    @Autowired
    private OrderpayDao orderpayDao;

    public List<Orderpay> getOrder(Integer uid) {
        List<Orderpay> result = null;
        try {
            result = orderpayDao.findAllByUidEqualsOrderByCreatetimeDesc(uid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Downloadrecord> getDownloadRecord(Integer uid) {
        List<Downloadrecord> result = null;
        try {
            result = downloadredordDao.findAllByUidEqualsOrderByDowndateDesc(uid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
