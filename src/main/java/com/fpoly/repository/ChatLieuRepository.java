package com.fpoly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fpoly.entity.ChatLieu;

@Repository
public interface ChatLieuRepository extends JpaRepository<ChatLieu,Long> {
	@Query(value = "SELECT * FROM chat_lieu c WHERE c.da_xoa = false", nativeQuery = true)
	List<ChatLieu> selectAllChatLieuExist();

}
