package com.fpoly.convertor;

import org.springframework.stereotype.Component;

import com.fpoly.dto.DiaChiDTO;
import com.fpoly.entity.DiaChi;

@Component
public class DiaChiConvertor {
	
		public DiaChi toEntity(DiaChiDTO dto) {
			DiaChi entity = new DiaChi();
			if(dto.getId() != null) {
				entity.setId(dto.getId());
			}
			entity.setDiaChi(dto.getDiaChi());
			entity.setHoTen(dto.getHoTen());
			entity.setSoDienThoai(dto.getSoDienThoai());
			
			return entity ;
		}
		
		public DiaChiDTO toDTO(DiaChi entity) {
			DiaChiDTO dto = new DiaChiDTO();
			entity.setId(dto.getId());
			entity.setDiaChi(dto.getDiaChi());
			entity.setHoTen(dto.getHoTen());
			entity.setSoDienThoai(dto.getSoDienThoai());
			return dto ;
		}
	
}
