package com.fpoly.repository;

import java.util.List;

import com.fpoly.dto.search.SPAndSPCTSearchDto;
import com.fpoly.entity.SanPhamChiTiet;

public interface SanPhamChiTietSearchRepository {

	List<SanPhamChiTiet> searchProductDetailExist(SPAndSPCTSearchDto data);

}
