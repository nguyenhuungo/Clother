package com.fpoly.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoaDonDTO extends BaseDTO<HoaDonDTO> {

    private Long khuyenMaiId;

    private Long nguoiDungId;

    private Long khachHangId;

    private Long giaoDichId;

    private String nguoiNhan;

    private String sdtNguoiNhan;

    private String diaChiGiaoHang;

    private String thoiGianGiaoHang;

    private String ghiChu;

    private BigDecimal tongTienHoaDon;

    private BigDecimal tienShip;

    private BigDecimal tongTienDonHang;

    private String loaiDonHang;


}
