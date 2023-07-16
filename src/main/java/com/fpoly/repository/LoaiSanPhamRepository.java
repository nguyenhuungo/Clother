package com.fpoly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fpoly.entity.LoaiSanPham;

@Repository
public interface LoaiSanPhamRepository extends JpaRepository<LoaiSanPham,Long> {
	@Query(value = "SELECT * FROM `loai_san_pham` c WHERE c.da_xoa = false", nativeQuery = true)
	List<LoaiSanPham> selectAllLoaiSanPhamExist();

}
