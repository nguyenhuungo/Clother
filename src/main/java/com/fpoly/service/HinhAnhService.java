package com.fpoly.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fpoly.entity.HinhAnh;

public interface HinhAnhService {

	void delete(HinhAnh entity);

	Optional<HinhAnh> findById(Long id);

	<S extends HinhAnh> S save(S entity);

	List<HinhAnh> getLstHinhAnhMauSacBySanPhamId( Long sanPhamId);

	List<Long> getDistinctMauSacInHinhAnhBySanPhamId(Long sanPhamId);

	List<HinhAnh> getHinhAnhChinhExist();

	Optional<HinhAnh> getHinhAnhByName(String tenAnh);

	Optional<HinhAnh> getHinhAnhChinhBySanPhamId(Long sanPhamId);
}
