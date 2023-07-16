package com.fpoly.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamDTO {
	private Long id;
	
	private Long kieuDangId;
	
	private Long chatLieuId;
	
	private Long loaiHangId;
	
	private Long phongCachId;
	
	private String tenSanPham;
	
	private BigDecimal giaBan;
	private BigDecimal gia;
	private String moTa;

	private Boolean daXoa;
}
