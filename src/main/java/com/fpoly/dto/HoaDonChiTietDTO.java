package com.fpoly.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoaDonChiTietDTO extends BaseDTO<HoaDonChiTietDTO> {

	    private Long hoaDonId;

	    private Long sanPhamChiTietId;

	    private BigDecimal donGia;

	    private int soLuong;

	    private BigDecimal tongTien;

	    private int daXoa;
}
