package com.fpoly.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.fpoly.dto.search.SPAndSPCTSearchDto;
import com.fpoly.entity.SanPhamChiTiet;
import com.fpoly.repository.SanPhamChiTietRepository;
import com.fpoly.repository.SanPhamChiTietSearchRepository;
import com.fpoly.service.SanPhamChiTietService;

import groovy.util.logging.Slf4j;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Slf4j
public class SanPhamChiTietServiceImpl implements SanPhamChiTietService{
	private final SanPhamChiTietRepository sanPhamChiTietRepository;
	private final SanPhamChiTietSearchRepository sanPhamChiTietSearchRepository;

	@Override
	public List<SanPhamChiTiet> getLstSanPhamChiTietExist() {
		return sanPhamChiTietRepository.getLstSanPhamChiTietExist();
	}

	@Override
	public Optional<SanPhamChiTiet> findById(Long id) {
		return sanPhamChiTietRepository.findById(id);
	}

	@Override
	public List<SanPhamChiTiet> getLstSanPhamChiTietBySanPhamId(Long id) {
		return sanPhamChiTietRepository.getLstSanPhamChiTietBySanPhamId(id);
	}

	@Override
	public List<SanPhamChiTiet> searchProductDetailExist(SPAndSPCTSearchDto data) {
		return sanPhamChiTietSearchRepository.searchProductDetailExist(data);
	}

	@Override
	public <S extends SanPhamChiTiet> S save(S entity) {
		entity.setDaXoa(false);
		return sanPhamChiTietRepository.save(entity);
	}

	
	@Override
	public void delete(SanPhamChiTiet entity) {
		entity.setDaXoa(false);
		sanPhamChiTietRepository.save(entity);
	}

	@Override
	public List<SanPhamChiTiet> getLstSanPhamChiTietAddImg(Long id) {
		return sanPhamChiTietRepository.getLstSanPhamChiTietAddImg(id);
	}

//	@Override
//	public List<SanPhamChiTietMauSacKichCo> getLstProductDetailsWithColorSize(Long id) {
//		return sanPhamChiTietRepository.getLstProductDetailsWithColorSize(id);
//	}
}
