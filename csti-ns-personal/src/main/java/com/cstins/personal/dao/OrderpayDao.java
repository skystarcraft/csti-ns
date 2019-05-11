package com.cstins.personal.dao;

import com.cstins.personal.entity.Orderpay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderpayDao extends JpaRepository<Orderpay, Integer> {

    List<Orderpay> findAllByUidEqualsOrderByCreatetimeDesc(Integer uid);

}
