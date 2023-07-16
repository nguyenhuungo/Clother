package com.fpoly.entity;

import lombok.*;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tra_hang")
@EntityListeners(AuditingEntityListener.class)
public class TraHang extends BaseEntity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "khach_hang_id", insertable = false, updatable = false)
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "hoa_don_chi_tiet_id", insertable = false, updatable = false)
    private HoaDonChiTiet hoaDonChiTiet;
    
    @Column(name = "ngay_tra", columnDefinition = "nvarchar(50) not null")
    private String ngayTra;

    @Column(name = "tong_tien", columnDefinition = "int not null")
    private BigDecimal tongTien;

    @Column(name = "ghi_chu", columnDefinition = "nvarchar(500) not null")
    private String ghiChu;

    @ManyToOne
    @JoinColumn(name = "ly_do_tra_hang_id", insertable = false, updatable = false)
    private LyDoTraHang lyDoTraHang;

    @Override
    public String toString() {
        return "TraHang{" +
                "khachHang=" + khachHang.getId() +
                ", hoaDonChiTiet=" + hoaDonChiTiet.getId() +
                ", ngayTra='" + ngayTra + '\'' +
                ", tongTien=" + tongTien +
                ", ghiChu='" + ghiChu + '\'' +
                ", lyDoTraHang=" + lyDoTraHang.getId() +
                '}';
    }
}
