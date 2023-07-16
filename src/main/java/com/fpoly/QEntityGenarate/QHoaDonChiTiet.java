package com.fpoly.QEntityGenarate;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;

import com.fpoly.entity.HoaDonChiTiet;
import com.fpoly.entity.TraHang;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHoaDonChiTiet is a Querydsl query type for HoaDonChiTiet
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QHoaDonChiTiet extends EntityPathBase<HoaDonChiTiet> {

    private static final long serialVersionUID = 1637748381L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHoaDonChiTiet hoaDonChiTiet = new QHoaDonChiTiet("hoaDonChiTiet");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Integer> daXoa = createNumber("daXoa", Integer.class);

    public final NumberPath<java.math.BigDecimal> donGia = createNumber("donGia", java.math.BigDecimal.class);

    public final QHoaDon hoaDon;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> ngayCapNhat = _super.ngayCapNhat;

    //inherited
    public final DateTimePath<java.util.Date> ngayTao = _super.ngayTao;

    //inherited
    public final StringPath nguoiCapNhat = _super.nguoiCapNhat;

    //inherited
    public final StringPath nguoiTao = _super.nguoiTao;

    public final QSanPhamChiTiet sanPhamChiTiet;

    public final NumberPath<Integer> soLuong = createNumber("soLuong", Integer.class);

    public final NumberPath<java.math.BigDecimal> tongTien = createNumber("tongTien", java.math.BigDecimal.class);

    public final ListPath<TraHang, QTraHang> traHangs = this.<TraHang, QTraHang>createList("traHangs", TraHang.class, QTraHang.class, PathInits.DIRECT2);

    public QHoaDonChiTiet(String variable) {
        this(HoaDonChiTiet.class, forVariable(variable), INITS);
    }

    public QHoaDonChiTiet(Path<? extends HoaDonChiTiet> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHoaDonChiTiet(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHoaDonChiTiet(PathMetadata metadata, PathInits inits) {
        this(HoaDonChiTiet.class, metadata, inits);
    }

    public QHoaDonChiTiet(Class<? extends HoaDonChiTiet> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hoaDon = inits.isInitialized("hoaDon") ? new QHoaDon(forProperty("hoaDon"), inits.get("hoaDon")) : null;
        this.sanPhamChiTiet = inits.isInitialized("sanPhamChiTiet") ? new QSanPhamChiTiet(forProperty("sanPhamChiTiet"), inits.get("sanPhamChiTiet")) : null;
    }

}

