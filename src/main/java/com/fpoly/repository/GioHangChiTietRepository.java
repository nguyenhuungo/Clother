package com.fpoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fpoly.entity.GioHangChiTiet;


@Repository
public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet,Long> {
	@Modifying
	@Query("UPDATE GioHangChiTiet ghct set ghct.soLuong =?1 WHERE ghct.id =?2")
	public void updateSoLuongByGioHangChiTietId(Integer soLuong , Long id);
	
}
