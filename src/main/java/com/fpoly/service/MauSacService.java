package com.fpoly.service;

import java.util.List;
import java.util.Optional;

import com.fpoly.entity.MauSac;



public interface MauSacService {

	List<MauSac> selectAllMauSacExist();

	Optional<MauSac> findById(Long id);

	<S extends MauSac> S save(S entity);

	List<MauSac> getAllMauSacExistBySPId(Long spId);

}
