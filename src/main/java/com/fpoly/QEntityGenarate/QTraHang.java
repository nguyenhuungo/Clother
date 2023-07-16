package com.fpoly.QEntityGenarate;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;

import com.fpoly.entity.TraHang;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTraHang is a Querydsl query type for TraHang
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTraHang extends EntityPathBase<TraHang> {

    private static final long serialVersionUID = 815039507L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTraHang traHang = new QTraHang("traHang");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath ghiChu = createString("ghiChu");

    public final QHoaDonChiTiet hoaDonChiTiet;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QKhachHang khachHang;

    public final QLyDoTraHang lyDoTraHang;

    //inherited
    public final DateTimePath<java.util.Date> ngayCapNhat = _super.ngayCapNhat;

    //inherited
    public final DateTimePath<java.util.Date> ngayTao = _super.ngayTao;

    public final StringPath ngayTra = createString("ngayTra");

    //inherited
    public final StringPath nguoiCapNhat = _super.nguoiCapNhat;

    //inherited
    public final StringPath nguoiTao = _super.nguoiTao;

    public final NumberPath<java.math.BigDecimal> tongTien = createNumber("tongTien", java.math.BigDecimal.class);

    public QTraHang(String variable) {
        this(TraHang.class, forVariable(variable), INITS);
    }

    public QTraHang(Path<? extends TraHang> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTraHang(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTraHang(PathMetadata metadata, PathInits inits) {
        this(TraHang.class, metadata, inits);
    }

    public QTraHang(Class<? extends TraHang> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hoaDonChiTiet = inits.isInitialized("hoaDonChiTiet") ? new QHoaDonChiTiet(forProperty("hoaDonChiTiet"), inits.get("hoaDonChiTiet")) : null;
        this.khachHang = inits.isInitialized("khachHang") ? new QKhachHang(forProperty("khachHang")) : null;
        this.lyDoTraHang = inits.isInitialized("lyDoTraHang") ? new QLyDoTraHang(forProperty("lyDoTraHang"), inits.get("lyDoTraHang")) : null;
    }

}

