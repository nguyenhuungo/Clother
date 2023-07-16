package com.fpoly.entity;

import lombok.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hoa_don")
@EntityListeners(AuditingEntityListener.class)
public class HoaDon extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -6627502088104297623L;

    @Column(name = "ma_don", columnDefinition = "nvarchar(50) not null")
    private String maDon;

    @OneToOne
    @JoinColumn(name = "khuyen_mai_id", insertable = false, updatable = false)
    private KhuyenMai khuyenMai;

    @ManyToOne
    @JoinColumn(name = "nguoi_dung_id", insertable = false, updatable = false)
    private NguoiDung nguoiDung;

    @ManyToOne
    @JoinColumn(name = "khach_hang_id", insertable = false, updatable = false)
    private KhachHang khachHang;

    @OneToOne
    @JoinColumn(name = "trang_thai_id")
    private TrangThai trangThai;

    @Column(name = "nguoi_nhan", columnDefinition = "nvarchar(50) null")
    private String nguoiNhan;

    @Column(name = "so_dien_thoai_nguoi_nhan", columnDefinition = "nvarchar(50) null")
    private String sdtNguoiNhan;

    @Column(name = "dia_chi_giao_hang", columnDefinition = "nvarchar(50) null")
    private String diaChiGiaoHang;

    @Column(name = "thoi_gian_giao_hang", columnDefinition = "Datetime null")
    private Date thoiGianGiaoHang;

    @Column(name = "ghi_chu", columnDefinition = "nvarchar(50) null")
    private String ghiChu;

    @Column(name = "tong_tien_hoa_don", columnDefinition = "int null")
    private BigDecimal tongTienHoaDon;

    @Column(name = "tien_ship", columnDefinition = "int  null")
    private BigDecimal tienShip;

    @Column(name = "tong_tien_don_hang", columnDefinition = "int null")
    private BigDecimal tongTienDonHang;

    @Column(name = "loai_hoa_don", columnDefinition = "int default(0) not null")
    private int loaiHoaDon;

    public String loaiHoaDon2() {
        String loaiHoaDon2;
        if(loaiHoaDon == 0)
        {
            loaiHoaDon2 = "Đơn đặt hàng";
        }else
        {
            loaiHoaDon2 = "Đơn tại quầy";
        }
        return loaiHoaDon2;
    }

    @OneToMany(mappedBy = "hoaDon")
    private List<HoaDonChiTiet> hoaDonChiTiets;

    @Override
    public String toString() {
        return "HoaDon{" +
                "khuyenMai=" + khuyenMai.getId() +
                ", nguoiDung=" + nguoiDung.getId() +
                ", khachHang=" + khachHang.getId() +
//                ", giaoDich=" + giaoDiches.size() +
                ", nguoiNhan='" + nguoiNhan + '\'' +
                ", sdtNguoiNhan='" + sdtNguoiNhan + '\'' +
                ", diaChiGiaoHang='" + diaChiGiaoHang + '\'' +
                ", thoiGianGiaoHang='" + thoiGianGiaoHang + '\'' +
                ", ghiChu='" + ghiChu + '\'' +
                ", tongTienHoaDon=" + tongTienHoaDon +
                ", tienShip=" + tienShip +
                ", tongTienDonHang=" + tongTienDonHang +
                ", loaiDonHang='" + loaiHoaDon + '\'' +
                ", hoaDonChiTiets=" + hoaDonChiTiets.size() +
                '}';
    }
}
