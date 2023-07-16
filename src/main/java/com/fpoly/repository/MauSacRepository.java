package com.fpoly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fpoly.entity.MauSac;

@Repository
public interface MauSacRepository extends JpaRepository<MauSac,Long> {
	@Query(value = "SELECT * FROM mau_sac c WHERE c.da_xoa = false ORDER BY c.id", nativeQuery = true)
	List<MauSac> selectAllMauSacExist();

	@Query(value = "SELECT DISTINCT m.* FROM `mau_sac` m LEFT JOIN san_pham_chi_tiet s ON m.id = s.mau_sac_id WHERE s.san_pham_id = :spId", nativeQuery = true)
	List<MauSac> getAllMauSacExistBySPId(@Param("spId") Long spId);
	
}
