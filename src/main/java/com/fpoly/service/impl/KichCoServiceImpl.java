package com.fpoly.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fpoly.entity.KichCo;
import com.fpoly.repository.KichCoRepository;
import com.fpoly.service.KichCoService;

import groovy.util.logging.Slf4j;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Slf4j
public class KichCoServiceImpl implements KichCoService{
	private KichCoRepository kichCoRepository;
	private static Logger logger = LoggerFactory.getLogger(KichCoServiceImpl.class);
	
	@Override
	public List<KichCo> selectAllKichCoExist() {
		return kichCoRepository.selectAllKichCoExist();
	}
	
	@Override
	public <S extends KichCo> S save(S entity) {
		entity.setDaXoa(false);
		return kichCoRepository.save(entity);
	}
	
	@Override
	public Optional<KichCo> findById(Long id) {
		return kichCoRepository.findById(id);
	}
}
