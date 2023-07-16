package com.fpoly.convertor;

import org.springframework.stereotype.Component;

import com.fpoly.dto.KhachHangDTO;
import com.fpoly.entity.NguoiDung;

@Component
public class NguoiDungConvertor {
	
	public NguoiDung toEntityByKhachHangDTO(KhachHangDTO khachHangDTO  ){
		NguoiDung entity = new NguoiDung();
		entity.setEmail(khachHangDTO.getEmail());
		entity.setSoDienThoai(khachHangDTO.getSoDienThoai());
		entity.setTenNguoiDung(khachHangDTO.getHoTen());
		if(khachHangDTO.getTrangThai() == 0) {
			entity.setTrangThai(2);
		}
		if(khachHangDTO.getTrangThai() == 1) {
			entity.setTrangThai(1);
		}
		entity.setDaXoa(false);
		return entity ;
	}
	
	
	public NguoiDung toEntityByKhachHangDTOAndOldNguoiDungEntity(KhachHangDTO khachHangDTO , NguoiDung oldEntity ){
		NguoiDung entity = new NguoiDung();
		if(khachHangDTO.getId() != null) {
			entity.setId(oldEntity.getId());
			entity.setDaXoa(oldEntity.getDaXoa());
		}
		entity.setEmail(khachHangDTO.getEmail());
		entity.setSoDienThoai(khachHangDTO.getSoDienThoai());
		entity.setTenNguoiDung(khachHangDTO.getHoTen());
		if(khachHangDTO.getTrangThai() == 0) {
			entity.setTrangThai(2);
		}
		if(khachHangDTO.getTrangThai() == 1) {
			entity.setTrangThai(1);
		}
		
		return entity ;
	}
}
