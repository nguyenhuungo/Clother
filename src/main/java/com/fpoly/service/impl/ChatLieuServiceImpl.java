package com.fpoly.service.impl;

import org.springframework.stereotype.Service;

import com.fpoly.entity.ChatLieu;
import com.fpoly.repository.ChatLieuRepository;
import com.fpoly.service.ChatLieuService;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import groovy.util.logging.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class ChatLieuServiceImpl implements ChatLieuService{
	private ChatLieuRepository chatLieuRepository;
	private static Logger logger = LoggerFactory.getLogger(ChatLieuServiceImpl.class);
	
	@Override
	public List<ChatLieu> selectAllChatLieuExist() {
		logger.info("Select all chat lieu exist");
		return chatLieuRepository.selectAllChatLieuExist();
	}
	
	@Override
	public <S extends ChatLieu> S save(S entity) {
		entity.setDaXoa(false);
		return chatLieuRepository.save(entity);
	}

	@Override
	public Optional<ChatLieu> findById(Long id) {
		return chatLieuRepository.findById(id);
	}
}
