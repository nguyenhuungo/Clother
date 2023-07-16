package com.fpoly.QEntityGenarate;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;

import com.fpoly.entity.HoaDon;
//import com.fpoly.entity.TrangThaiHoaDon;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHoaDon is a Querydsl query type for HoaDon
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QHoaDon extends EntityPathBase<HoaDon> {

    private static final long serialVersionUID = -2121147509L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHoaDon hoaDon = new QHoaDon("hoaDon");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath diaChiGiaoHang = createString("diaChiGiaoHang");

    public final StringPath ghiChu = createString("ghiChu");

    public final QGiaoDich giaoDich;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QKhachHang khachHang;

    public final QKhuyenMai khuyenMai;

    public final StringPath loaiDonHang = createString("loaiDonHang");

    //inherited
    public final DateTimePath<java.util.Date> ngayCapNhat = _super.ngayCapNhat;

    //inherited
    public final DateTimePath<java.util.Date> ngayTao = _super.ngayTao;

    //inherited
    public final StringPath nguoiCapNhat = _super.nguoiCapNhat;

    public final QNguoiDung nguoiDung;

    public final StringPath nguoiNhan = createString("nguoiNhan");

    //inherited
    public final StringPath nguoiTao = _super.nguoiTao;

    public final StringPath sdtNguoiNhan = createString("sdtNguoiNhan");

    public final StringPath thoiGianGiaoHang = createString("thoiGianGiaoHang");

    public final NumberPath<java.math.BigDecimal> tienShip = createNumber("tienShip", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> tongTienDonHang = createNumber("tongTienDonHang", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> tongTienHoaDon = createNumber("tongTienHoaDon", java.math.BigDecimal.class);

    public QHoaDon(String variable) {
        this(HoaDon.class, forVariable(variable), INITS);
    }

    public QHoaDon(Path<? extends HoaDon> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHoaDon(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHoaDon(PathMetadata metadata, PathInits inits) {
        this(HoaDon.class, metadata, inits);
    }

    public QHoaDon(Class<? extends HoaDon> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.giaoDich = inits.isInitialized("giaoDich") ? new QGiaoDich(forProperty("giaoDich"), inits.get("giaoDich")) : null;
        this.khachHang = inits.isInitialized("khachHang") ? new QKhachHang(forProperty("khachHang")) : null;
        this.khuyenMai = inits.isInitialized("khuyenMai") ? new QKhuyenMai(forProperty("khuyenMai")) : null;
        this.nguoiDung = inits.isInitialized("nguoiDung") ? new QNguoiDung(forProperty("nguoiDung")) : null;
    }

}

