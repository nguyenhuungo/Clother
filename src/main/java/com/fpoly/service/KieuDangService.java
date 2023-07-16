package com.fpoly.service;

import java.util.List;
import java.util.Optional;

import com.fpoly.entity.KieuDang;


public interface KieuDangService {

	List<KieuDang> selectAllKieuDangExist();

	Optional<KieuDang> findById(Long id);

	<S extends KieuDang> S save(S entity);


}
