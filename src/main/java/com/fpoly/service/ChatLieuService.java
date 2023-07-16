package com.fpoly.service;

import java.util.List;
import java.util.Optional;

import com.fpoly.entity.ChatLieu;


public interface ChatLieuService {

	List<ChatLieu> selectAllChatLieuExist();

	Optional<ChatLieu> findById(Long id);

	<S extends ChatLieu> S save(S entity);

}
