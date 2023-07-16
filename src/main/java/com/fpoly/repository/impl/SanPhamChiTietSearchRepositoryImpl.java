package com.fpoly.repository.impl;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.fpoly.QEntityGenarate.QChatLieu;
import com.fpoly.QEntityGenarate.QKichCo;
import com.fpoly.QEntityGenarate.QKieuDang;
import com.fpoly.QEntityGenarate.QMauSac;
import com.fpoly.QEntityGenarate.QPhongCach;
import com.fpoly.QEntityGenarate.QSanPham;
import com.fpoly.QEntityGenarate.QSanPhamChiTiet;
import com.fpoly.dto.search.SPAndSPCTSearchDto;
import com.fpoly.entity.SanPhamChiTiet;
import com.fpoly.repository.SanPhamChiTietSearchRepository;
import com.fpoly.service.TypeHelperService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;

import lombok.AllArgsConstructor;


@Repository
@AllArgsConstructor
public class SanPhamChiTietSearchRepositoryImpl implements SanPhamChiTietSearchRepository{
	private final EntityManager entityManager;
	
	@Autowired
	private TypeHelperService typeHelperService;
	
	@Override
	public List<SanPhamChiTiet> searchProductDetailExist(SPAndSPCTSearchDto data){
		SPAndSPCTSearchDto dataSearch = convertSearchNotNull(data);
		
		JPAQuery<SanPhamChiTiet> query = new JPAQuery<SanPhamChiTiet>(entityManager);
		BooleanBuilder where = new BooleanBuilder();
		
		QSanPhamChiTiet qSanPhamChiTiet = QSanPhamChiTiet.sanPhamChiTiet;
		QSanPham qSanPham = QSanPham.sanPham;
		QKieuDang qKieuDang = QKieuDang.kieuDang;
		QPhongCach qPhongCach = QPhongCach.phongCach;
		QChatLieu qChatLieu = QChatLieu.chatLieu;
		QMauSac qMauSac = QMauSac.mauSac;
		QKichCo qKichCo = QKichCo.kichCo;
		
		where.and(qSanPhamChiTiet.daXoa.isFalse());
		where.and(qSanPham.daXoa.isFalse());
		
		
		List<Long> lstKieuDang = dataSearch.getKieuDangIds();
		List<Long> lstChatLieu = dataSearch.getChatLieuIds();
		List<Long> lstLoaiSanPham = dataSearch.getLoaiSanPhamIds();
		List<Long> lstPhongCach = dataSearch.getPhongCachIds();
		List<Long> lstKichCo = dataSearch.getKichCoIds();
		List<Long> lstMauSac = dataSearch.getMauSacIds();
		List<Boolean> lstCoHienThi = dataSearch.getCoHienThi();
		
		if(lstCoHienThi.size() != 2 && lstCoHienThi.size() != 0) {
			where.and(qSanPhamChiTiet.coHienThi.eq(dataSearch.getCoHienThi().get(0)));
		}
		
		if(lstKieuDang.size() != 0) {
			if(lstKieuDang.get(0).equals(-1L)) {
				where.and(qSanPham.kieuDang.id.in(lstKieuDang));
			}
		}
		if(lstChatLieu.size() != 0) {
			where.and(qSanPham.chatLieu.id.in(lstChatLieu));
		}
		if(lstPhongCach.size() != 0) {
			where.and(qSanPham.phongCach.id.in(lstPhongCach));
		}
		if(lstLoaiSanPham.size() != 0) {
			where.and(qSanPham.loaiSanPham.id.in(lstLoaiSanPham));
		}
		if(lstMauSac.size() != 0) {
			where.and(qSanPhamChiTiet.mauSac.id.in(lstMauSac));
		}
		if(lstKichCo.size() != 0) {
			where.and(qSanPhamChiTiet.kichCo.id.in(lstKichCo));
		}
		
		if(!dataSearch.getTenSanPham().equalsIgnoreCase("-1")) {
			if(StringUtils.isNotEmpty(dataSearch.getTenSanPham())) {
				where.and(qSanPham.tenSanPham.containsIgnoreCase(dataSearch.getTenSanPham()));
			}
		}
		
		if(!dataSearch.getNguoiTaoSPCT().equalsIgnoreCase("-1")) {
			if(StringUtils.isNotEmpty(dataSearch.getNguoiTaoSPCT())) {
				where.and(qSanPham.nguoiTao.containsIgnoreCase(dataSearch.getNguoiTaoSPCT()));
			}
		}
		
		if(!dataSearch.getNguoiCapNhatSPCT().equalsIgnoreCase("-1")) {
			if(StringUtils.isNotEmpty(dataSearch.getNguoiCapNhatSPCT())) {
				where.and(qSanPham.nguoiTao.containsIgnoreCase(dataSearch.getNguoiCapNhatSPCT()));
			}
		}
		
		if(dataSearch.getSoLuongMin().intValue() != -1 &&
				dataSearch.getSoLuongMax().intValue() != -1) {
			if(StringUtils.isNotEmpty(dataSearch.getSoLuongMin().toString()) &&
					StringUtils.isNotEmpty(dataSearch.getSoLuongMax().toString())) {
				where.and(qSanPhamChiTiet.soLuong.between(dataSearch.getSoLuongMin(), dataSearch.getSoLuongMax()));
			}
		}
		if(dataSearch.getSoLuongMin().intValue() != -1 &&
				dataSearch.getSoLuongMax().intValue() == -1) {
			where.and(qSanPhamChiTiet.soLuong.goe(dataSearch.getSoLuongMin()));
		}else if(dataSearch.getSoLuongMin().intValue() == -1 &&
				dataSearch.getSoLuongMax().intValue() != -1) {
			where.and(qSanPhamChiTiet.soLuong.loe(dataSearch.getSoLuongMax()));
		}
		
		if(!dataSearch.getGiaMin().toString().equalsIgnoreCase("-1") &&
				!dataSearch.getGiaMax().toString().equalsIgnoreCase("-1") ) {
			if(!dataSearch.getGiaMin().toString().isEmpty() && 
					!dataSearch.getGiaMax().toString().isEmpty()) {
				where.and(qSanPham.gia.between(dataSearch.getGiaMin(), dataSearch.getGiaMax()));
			}
		}
		if(!dataSearch.getGiaMin().toString().equalsIgnoreCase("-1") &&
				dataSearch.getGiaMax().toString().equalsIgnoreCase("-1")) {
			if(!dataSearch.getGiaMin().toString().isEmpty()) {
				where.and(qSanPham.gia.goe(dataSearch.getGiaMin()));
			}
		}else if(dataSearch.getGiaMin().toString().equalsIgnoreCase("-1") &&
				!dataSearch.getGiaMax().toString().equalsIgnoreCase("-1")) {
			if(!dataSearch.getGiaMax().toString().isEmpty()) {
				where.and(qSanPham.gia.loe(dataSearch.getGiaMax()));
			}
		}
		
		//Check validate Date eq epoch
		final Date CheckDate = Date.from(Instant.EPOCH);
//		Date now = Date.from(Instant.now());
		
		if(!dataSearch.getNgayTaoMin().toString().equalsIgnoreCase(""+CheckDate) &&
				!dataSearch.getNgayTaoMax().toString().equalsIgnoreCase(""+CheckDate)) {
			if(!dataSearch.getNgayTaoMin().toString().isEmpty() && 
					!dataSearch.getNgayTaoMax().toString().isEmpty()) {
				where.and(qSanPhamChiTiet.ngayTao.between(dataSearch.getNgayTaoMin(), dataSearch.getNgayTaoMax()));
			}
		}
		//- so sánh ngày
		//- không dùng được -> lí do: kiểu Date có sai số lớn -> Instant is solution
//		if(!dataSearch.getNgayTaoMin().toString().equalsIgnoreCase(""+CheckDate) &&
//				dataSearch.getNgayTaoMax().toString().equalsIgnoreCase(""+CheckDate)) {
//			if(!dataSearch.getNgayTaoMin().toString().isEmpty()) {
//				where.and(qSanPham.ngayTao.between(dataSearch.getNgayTaoMin(), now));
//			}
//		}
		
		
		if(!dataSearch.getNgayCapNhatMin().toString().equalsIgnoreCase(""+CheckDate) &&
				!dataSearch.getNgayCapNhatMax().toString().equalsIgnoreCase(""+CheckDate)) {
			if(!dataSearch.getNgayCapNhatMin().toString().isEmpty() && 
					!dataSearch.getNgayCapNhatMax().toString().isEmpty()) {
				where.and(qSanPhamChiTiet.ngayCapNhat.between(dataSearch.getNgayCapNhatMin(), dataSearch.getNgayCapNhatMax()));
			}
		}
		
//		if(!dataSearch.getNgayCapNhatMin().toString().equalsIgnoreCase(""+CheckDate) &&
//				dataSearch.getNgayCapNhatMax().toString().equalsIgnoreCase(""+CheckDate)) {
//			if(!dataSearch.getNgayCapNhatMin().toString().isEmpty()) {
//				where.and(qSanPham.ngayCapNhat.between(dataSearch.getNgayCapNhatMin(), now));
//			}
//		}
		
		List<SanPhamChiTiet> result = query.select(qSanPhamChiTiet).from(qSanPhamChiTiet)
				.innerJoin(qMauSac).on(qSanPhamChiTiet.mauSac.id.eq(qMauSac.id))
				.innerJoin(qKichCo).on(qSanPhamChiTiet.kichCo.id.eq(qKichCo.id))
				.leftJoin(qSanPham).on(qSanPhamChiTiet.sanPham.id.eq(qSanPham.id))
				.innerJoin(qPhongCach).on(qSanPham.phongCach.id.eq(qPhongCach.id))
				.innerJoin(qKieuDang).on(qSanPham.kieuDang.id.eq(qKieuDang.id))
				.innerJoin(qChatLieu).on(qSanPham.chatLieu.id.eq(qChatLieu.id))
				.where(where)
				.orderBy(qSanPhamChiTiet.sanPham.id.desc())
				.fetch();
		return result;
	}
	
//	convert type of value not null
//	@author cuongdd3
	public SPAndSPCTSearchDto convertSearchNotNull(SPAndSPCTSearchDto dataSearch) {
		List<Long> lstKieuDangId = typeHelperService.convertObjectTypeListLong(dataSearch.getKieuDangIds());
		List<Long> lstChatLieuId = typeHelperService.convertObjectTypeListLong(dataSearch.getChatLieuIds());
		List<Long> lstLoaiSanPhamId = typeHelperService.convertObjectTypeListLong(dataSearch.getLoaiSanPhamIds());
		List<Long> lstPhongCachId = typeHelperService.convertObjectTypeListLong(dataSearch.getPhongCachIds());
		List<Long> lstKichCoId = typeHelperService.convertObjectTypeListLong(dataSearch.getKichCoIds());
		List<Long> lstMauSacId = typeHelperService.convertObjectTypeListLong(dataSearch.getMauSacIds());
		String tenSanPham = typeHelperService.convertObjectTypeString(dataSearch.getTenSanPham());
		BigDecimal giaHienHanhMin = typeHelperService.convertObjectTypeBigDecimal(dataSearch.getGiaMin());
		BigDecimal giaHienHanhMax = typeHelperService.convertObjectTypeBigDecimal(dataSearch.getGiaMax());
		int soLuongMin = typeHelperService.convertObjectTypeListInt(dataSearch.getSoLuongMin());
		int soLuongMax = typeHelperService.convertObjectTypeListInt(dataSearch.getSoLuongMax());
		List<Boolean> coHienThi = typeHelperService.convertObjectTypeListBoolean(dataSearch.getCoHienThi());
		String nguoiTaoSPCT = typeHelperService.convertObjectTypeString(dataSearch.getNguoiTaoSPCT());
		String nguoiCapNhatSPCT = typeHelperService.convertObjectTypeString(dataSearch.getNguoiCapNhatSPCT());
		Date ngayTaoMin = typeHelperService.convertObjectTypeDate(dataSearch.getNgayTaoMin());
		Date ngayTaoMax = typeHelperService.convertObjectTypeDate(dataSearch.getNgayTaoMax());
		Date ngayCapNhatMin = typeHelperService.convertObjectTypeDate(dataSearch.getNgayCapNhatMin());
		Date ngayCapNhatMax = typeHelperService.convertObjectTypeDate(dataSearch.getNgayCapNhatMax());
		SPAndSPCTSearchDto result = new SPAndSPCTSearchDto(lstKieuDangId, lstChatLieuId, lstLoaiSanPhamId, lstPhongCachId, lstKichCoId, lstMauSacId, tenSanPham, giaHienHanhMin, giaHienHanhMax, soLuongMin, soLuongMax, coHienThi, nguoiTaoSPCT, nguoiCapNhatSPCT, ngayTaoMin, ngayTaoMax, ngayCapNhatMin, ngayCapNhatMax);
		return result;
	}
}
