package com.cstins.front.service;

import com.cstins.front.dao.FrontDao;
import com.cstins.front.dao.NaxinDao;
import com.cstins.front.entity.Apply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @program: csti-ns
 * @description:
 * @author: 杨云龙
 **/

@Service
@Transactional
public class NaxinService {

    @Autowired
    private NaxinDao naxinDao;

    @Autowired
    private FrontService frontService;

    public boolean naxin(Integer uid) {
        try {
            Date start = frontService.findFront().getStart();
            Date end = frontService.findFront().getEnd();
            Date now = new Date();
            if (now.compareTo(start) >= 0 && now.compareTo(end) <= 0) {
                Apply apply = new Apply(uid);
                naxinDao.save(apply);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
