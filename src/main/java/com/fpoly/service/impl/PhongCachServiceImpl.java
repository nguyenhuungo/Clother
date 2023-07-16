package com.fpoly.service.impl;

import org.springframework.stereotype.Service;

import com.fpoly.entity.PhongCach;
import com.fpoly.repository.PhongCachRepository;
import com.fpoly.service.PhongCachService;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import groovy.util.logging.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class PhongCachServiceImpl implements PhongCachService{
	private PhongCachRepository phongCachRepository;
	private static Logger logger = LoggerFactory.getLogger(PhongCachServiceImpl.class);
	
	@Override
	public List<PhongCach> selectAllPhongCachExist() {
		return phongCachRepository.selectAllPhongCachExist();
	}
	@Override
	public <S extends PhongCach> S save(S entity) {
		entity.setDaXoa(false);
		return phongCachRepository.save(entity);
	}
	
	@Override
	public Optional<PhongCach> findById(Long id) {
		return phongCachRepository.findById(id);
	}
}
