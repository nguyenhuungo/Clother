package com.fpoly.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChatLieuDTO extends BaseDTO<BaseDTO> {
	private String tenChatLieu;
	
	private Boolean daXoa;
}
