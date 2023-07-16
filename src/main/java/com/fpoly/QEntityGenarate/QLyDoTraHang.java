package com.fpoly.QEntityGenarate;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;

import com.fpoly.entity.LyDoTraHang;
import com.fpoly.entity.TraHang;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLyDoTraHang is a Querydsl query type for LyDoTraHang
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLyDoTraHang extends EntityPathBase<LyDoTraHang> {

    private static final long serialVersionUID = 1897402107L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLyDoTraHang lyDoTraHang = new QLyDoTraHang("lyDoTraHang");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QHinhAnh hinhAnhTraHang;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath lyDo = createString("lyDo");

    //inherited
    public final DateTimePath<java.util.Date> ngayCapNhat = _super.ngayCapNhat;

    //inherited
    public final DateTimePath<java.util.Date> ngayTao = _super.ngayTao;

    //inherited
    public final StringPath nguoiCapNhat = _super.nguoiCapNhat;

    //inherited
    public final StringPath nguoiTao = _super.nguoiTao;

    public final SetPath<TraHang, QTraHang> traHang = this.<TraHang, QTraHang>createSet("traHang", TraHang.class, QTraHang.class, PathInits.DIRECT2);

    public QLyDoTraHang(String variable) {
        this(LyDoTraHang.class, forVariable(variable), INITS);
    }

    public QLyDoTraHang(Path<? extends LyDoTraHang> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLyDoTraHang(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLyDoTraHang(PathMetadata metadata, PathInits inits) {
        this(LyDoTraHang.class, metadata, inits);
    }

    public QLyDoTraHang(Class<? extends LyDoTraHang> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hinhAnhTraHang = inits.isInitialized("hinhAnhTraHang") ? new QHinhAnh(forProperty("hinhAnhTraHang"), inits.get("hinhAnhTraHang")) : null;
    }

}

