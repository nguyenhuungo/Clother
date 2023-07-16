package com.fpoly.convertor;

import org.springframework.stereotype.Component;

import com.fpoly.dto.KhachHangDTO;
import com.fpoly.entity.KhachHang;

@Component
public class KhachHangConvertor {
	
		public KhachHang toEntity(KhachHangDTO dto) {
			KhachHang entity = new KhachHang();
			if(dto.getId() != null) {
				entity.setId(dto.getId());
			}
			entity.setEmail(dto.getEmail());
			entity.setHoTen(dto.getHoTen());
			entity.setMatKhau(dto.getMatKhau());
			entity.setSoDienThoai(dto.getSoDienThoai());
			entity.setSoLanMua(dto.getSoLanMua());
			entity.setTrangThai(dto.getTrangThai());
			
			return entity ;
		}
		
		public KhachHangDTO toDTO(KhachHang entity) {
			KhachHangDTO dto = new KhachHangDTO();
			if(entity.getId() != null) {
				dto.setId(dto.getId());
			}
			dto.setId(entity.getId());
			dto.setEmail(entity.getEmail());
			dto.setHoTen(entity.getHoTen());
			dto.setMatKhau(entity.getMatKhau());
			dto.setSoDienThoai(entity.getSoDienThoai());
			dto.setSoLanMua(entity.getSoLanMua());
			dto.setTrangThai(entity.getTrangThai());
			return dto ;
		}
	
}
