package com.fpoly.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class GioHangChiTietDTO extends BaseDTO<GioHangChiTietDTO> {

    private Long sanPhamChiTietId;

    private Long gioHangId;

    private int soLuong;

    private int donGia;

    private BigDecimal thanhTien;

    private int trangThai;

    private Boolean daXoa;
}
