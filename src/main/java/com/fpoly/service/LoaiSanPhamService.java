package com.fpoly.service;

import java.util.List;
import java.util.Optional;

import com.fpoly.entity.LoaiSanPham;


public interface LoaiSanPhamService {

	List<LoaiSanPham> selectAllLoaiHangExist();

	Optional<LoaiSanPham> findById(Long id);

	<S extends LoaiSanPham> S save(S entity);
}
