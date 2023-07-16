package com.fpoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpoly.entity.LyDoTraHang;

@Repository
public interface LyDoTraHangRepository extends JpaRepository<LyDoTraHang, Long> {

}
