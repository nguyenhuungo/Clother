package com.fpoly.service;

import java.util.List;
import java.util.Optional;

import com.fpoly.entity.KichCo;


public interface KichCoService {

	List<KichCo> selectAllKichCoExist();

	Optional<KichCo> findById(Long id);

	<S extends KichCo> S save(S entity);

}
