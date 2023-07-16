package com.fpoly.service;

import java.util.List;
import java.util.Optional;

import com.fpoly.entity.PhongCach;

public interface PhongCachService {

	List<PhongCach> selectAllPhongCachExist();

	Optional<PhongCach> findById(Long id);

	<S extends PhongCach> S save(S entity);

}
