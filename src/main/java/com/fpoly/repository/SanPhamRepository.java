package com.fpoly.repository;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fpoly.entity.SanPham;


@Repository
@Primary
public interface SanPhamRepository extends JpaRepository<SanPham,Long>, SanPhamSearchRepository {
	@Query(value = "SELECT * FROM san_pham s WHERE s.da_xoa = false ORDER BY s.id", nativeQuery = true)
	Page<SanPham> getSanPhamExist(Pageable pageable);
}
