package com.fpoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpoly.entity.TraHang;

@Repository
public interface TraHangRepository extends  JpaRepository<TraHang,Long> {

}
