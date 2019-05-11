package com.cstins.personal.dao;

import com.cstins.personal.entity.Downloadrecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DownloadredordDao extends JpaRepository<Downloadrecord, Integer> {

    List<Downloadrecord> findAllByUidEqualsOrderByDowndateDesc(Integer uid);

}
