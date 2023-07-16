package com.fpoly.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fpoly.entity.HinhAnh;

@Repository
public interface HinhAnhRepository extends JpaRepository<HinhAnh, Long> {
	@Query(value ="SELECT h.* FROM `hinh_anh` h LEFT JOIN mau_sac m ON h.mau_sac_id = m.id LEFT JOIN san_pham s ON h.san_pham_id = s.id WHERE h.san_pham_id = :sanPhamId ORDER BY h.mau_sac_id DESC", nativeQuery = true)
	List<HinhAnh> getLstHinhAnhMauSacBySanPhamId(@Param("sanPhamId") Long sanPhamId);
	
	@Query(value ="SELECT DISTINCT(h.mau_sac_id) FROM `hinh_anh` h LEFT JOIN mau_sac m ON h.mau_sac_id = m.id LEFT JOIN san_pham s ON h.san_pham_id = s.id WHERE h.san_pham_id = :sanPhamId ORDER BY h.mau_sac_id DESC", nativeQuery = true)
	List<Long> getDistinctMauSacInHinhAnhBySanPhamId(@Param("sanPhamId") Long sanPhamId);
	
	@Query(value="select h.* FROM hinh_anh h LEFT JOIN san_pham s ON h.san_pham_id = s.id WHERE h.la_anh_chinh = true AND h.co_hien_thi = true AND s.da_xoa = false ORDER BY s.id DESC", nativeQuery = true)
	List<HinhAnh> getHinhAnhChinhExist();
	
	@Query(value="select h.* FROM hinh_anh h WHERE h.ten_anh= :tenAnh AND h.da_xoa = false LIMIT 1", nativeQuery = true)
	Optional<HinhAnh> getHinhAnhByName(@Param("tenAnh") String tenAnh);
	
	@Query(value="select h.* FROM hinh_anh h WHERE h.san_pham_id=:sanPhamId AND h.la_anh_chinh = true AND h.da_xoa = false LIMIT 1", nativeQuery = true)
	Optional<HinhAnh> getHinhAnhChinhBySanPhamId(@Param("sanPhamId") Long sanPhamId);
}
