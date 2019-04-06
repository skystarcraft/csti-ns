package com.cstins.res.dao;

import com.cstins.res.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ResourceDao extends JpaRepository<Resource, Integer> {

    @Modifying
    @Transactional
    @Query(value = "update resource set rdownload = rdownload + 1 where rid = ?1", nativeQuery = true)
    void addDownload(Integer rid);

    List<Resource> findAllByRuidEquals(Integer ruid);
}
