package com.fpoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fpoly.entity.GioHang;

@Repository
public interface GioHangRepository extends JpaRepository<GioHang,Long> {
	
	@Query("SELECT g FROM GioHang g WHERE g.khachHang.email=?1 ")
	GioHang findGioHangByEmail(String email);
	
}
