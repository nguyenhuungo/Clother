package com.fpoly.QEntityGenarate;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;

import com.fpoly.entity.HinhAnh;
import com.fpoly.entity.LyDoTraHang;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHinhAnh is a Querydsl query type for HinhAnh
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QHinhAnh extends EntityPathBase<HinhAnh> {

    private static final long serialVersionUID = -1489804098L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHinhAnh hinhAnh = new QHinhAnh("hinhAnh");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final BooleanPath coHienThi = createBoolean("coHienThi");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final BooleanPath isAnhChinh = createBoolean("isAnhChinh");

    public final SetPath<LyDoTraHang, QLyDoTraHang> lyDoTraHang = this.<LyDoTraHang, QLyDoTraHang>createSet("lyDoTraHang", LyDoTraHang.class, QLyDoTraHang.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.util.Date> ngayCapNhat = _super.ngayCapNhat;

    //inherited
    public final DateTimePath<java.util.Date> ngayTao = _super.ngayTao;

    //inherited
    public final StringPath nguoiCapNhat = _super.nguoiCapNhat;

    //inherited
    public final StringPath nguoiTao = _super.nguoiTao;

    public final QSanPhamChiTiet sanPhamChiTiet;

    public final StringPath tenAnh = createString("tenAnh");

    public QHinhAnh(String variable) {
        this(HinhAnh.class, forVariable(variable), INITS);
    }

    public QHinhAnh(Path<? extends HinhAnh> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHinhAnh(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHinhAnh(PathMetadata metadata, PathInits inits) {
        this(HinhAnh.class, metadata, inits);
    }

    public QHinhAnh(Class<? extends HinhAnh> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.sanPhamChiTiet = inits.isInitialized("sanPhamChiTiet") ? new QSanPhamChiTiet(forProperty("sanPhamChiTiet"), inits.get("sanPhamChiTiet")) : null;
    }

}

