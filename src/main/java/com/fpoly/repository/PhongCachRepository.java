package com.fpoly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fpoly.entity.PhongCach;

@Repository
public interface PhongCachRepository extends JpaRepository<PhongCach, Long> {
	@Query(value = "SELECT * FROM phong_cach c WHERE c.da_xoa = false", nativeQuery = true)
	List<PhongCach> selectAllPhongCachExist();

}
