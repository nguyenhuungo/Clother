//package com.fpoly.QEntityGenarate;
//
//import static com.querydsl.core.types.PathMetadataFactory.*;
//
//import com.fpoly.dto.TrangThaiHoaDonDTO;
//import com.querydsl.core.types.dsl.*;
//
//import com.querydsl.core.types.PathMetadata;
//import javax.annotation.Generated;
//
//import com.querydsl.core.types.Path;
//import com.querydsl.core.types.dsl.PathInits;
//
//
///**
// * QTrangThaiHoaDon is a Querydsl query type for TrangThaiHoaDon
// */
//@Generated("com.querydsl.codegen.EntitySerializer")
//public class QTrangThaiHoaDon extends EntityPathBase<TrangThaiHoaDonDTO> {
//
//    private static final long serialVersionUID = -1655373697L;
//
//    private static final PathInits INITS = PathInits.DIRECT2;
//
//    public static final QTrangThaiHoaDon trangThaiHoaDon = new QTrangThaiHoaDon("trangThaiHoaDon");
//
//    public final QBaseEntity _super = new QBaseEntity();
//
//    public final QHoaDon hoaDon;
//
//    //inherited
//    public final NumberPath<Long> id = _super.id;
//
//    //inherited
//    public final DateTimePath<java.util.Date> ngayCapNhat = _super.ngayCapNhat;
//
//    //inherited
//    public final DateTimePath<java.util.Date> ngayTao = _super.ngayTao;
//
//    //inherited
//    public final StringPath nguoiCapNhat = _super.nguoiCapNhat;
//
//    //inherited
//    public final StringPath nguoiTao = _super.nguoiTao;
//
//    public final NumberPath<Integer> trangThai = createNumber("trangThai", Integer.class);
//
//    public QTrangThaiHoaDon(String variable) {
//        this(QTrangThaiHoaDon.class, forVariable(variable), INITS);
//    }
//
//    public QTrangThaiHoaDon(Path<? extends QTrangThaiHoaDon> path) {
//        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
//    }
//
//    public QTrangThaiHoaDon(PathMetadata metadata) {
//        this(metadata, PathInits.getFor(metadata, INITS));
//    }
//
//    public QTrangThaiHoaDon(PathMetadata metadata, PathInits inits) {
//        this(QTrangThaiHoaDon.class, metadata, inits);
//    }
//
//    public QTrangThaiHoaDon(Class<? extends QTrangThaiHoaDon> type, PathMetadata metadata, PathInits inits) {
//        super(trangThaiHoaDon.getType(), metadata, inits);
//        this.hoaDon = inits.isInitialized("hoaDon") ? new QHoaDon(forProperty("hoaDon"), inits.get("hoaDon")) : null;
//    }
//
//}
//
