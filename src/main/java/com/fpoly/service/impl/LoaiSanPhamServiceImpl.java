package com.fpoly.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.fpoly.entity.LoaiSanPham;
import com.fpoly.repository.LoaiSanPhamRepository;
import com.fpoly.service.LoaiSanPhamService;

import groovy.util.logging.Slf4j;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Slf4j
public class LoaiSanPhamServiceImpl implements LoaiSanPhamService{
	private LoaiSanPhamRepository loaiSanPhamRepository;
	private static Logger logger = LoggerFactory.getLogger(ChatLieuServiceImpl.class);
	
	@Override
	public List<LoaiSanPham> selectAllLoaiHangExist() {
		return loaiSanPhamRepository.selectAllLoaiSanPhamExist();
	}		
	
	@Override
	@Transactional
	public <S extends LoaiSanPham> S save(S entity) {
		entity.setDaXoa(false);
		return loaiSanPhamRepository.save(entity);
	}
	
	@Override
	public Optional<LoaiSanPham> findById(Long id) {
		return loaiSanPhamRepository.findById(id);
	}
}
