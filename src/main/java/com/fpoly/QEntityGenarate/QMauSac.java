package com.fpoly.QEntityGenarate;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;

import com.fpoly.entity.MauSac;
import com.fpoly.entity.SanPhamChiTiet;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMauSac is a Querydsl query type for MauSac
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMauSac extends EntityPathBase<MauSac> {

    private static final long serialVersionUID = -1990321258L;

    public static final QMauSac mauSac = new QMauSac("mauSac");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final BooleanPath daXoa = createBoolean("daXoa");

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

    public final SetPath<SanPhamChiTiet, QSanPhamChiTiet> sanPhamChiTiets = this.<SanPhamChiTiet, QSanPhamChiTiet>createSet("sanPhamChiTiets", SanPhamChiTiet.class, QSanPhamChiTiet.class, PathInits.DIRECT2);

    public final StringPath tenMauSac = createString("tenMauSac");

    public QMauSac(String variable) {
        super(MauSac.class, forVariable(variable));
    }

    public QMauSac(Path<? extends MauSac> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMauSac(PathMetadata metadata) {
        super(MauSac.class, metadata);
    }

}

