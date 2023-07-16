package com.fpoly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fpoly.entity.KieuDang;

@Repository
public interface KieuDangRepository extends JpaRepository<KieuDang,Long> {
	@Query(value = "SELECT * FROM kieu_dang c WHERE c.da_xoa = false", nativeQuery = true)
	List<KieuDang> selectAllKieuDangExist();

}
