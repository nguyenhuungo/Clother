package com.fpoly.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;

import com.fpoly.dto.DiaChiDTO;
import com.fpoly.entity.DiaChi;
import com.fpoly.repository.DiaChiRepository;
import com.fpoly.repository.KhachHangRepository;
import com.fpoly.service.DiaChiService;


@Service
public class DiaChiServiceImpl implements DiaChiService {

	@Autowired
	private DiaChiRepository diaChiRepository;

	@Autowired
	private KhachHangRepository khachHangRepository ;
	@Override
	@Transactional
	public DiaChiDTO save(DiaChiDTO result) {
		DiaChiDTO diaChiDTO = new DiaChiDTO();
		DiaChi diaChiEntity = new DiaChi();
		try {
			if(result.getKhachHangId() != null) {
				diaChiEntity.setDiaChi(result.getCity()+"-"+result.getDistrict()+"-"+result.getWard()+"-"+result.getSoNha());
				diaChiEntity.setKhachHang(khachHangRepository.getOne(result.getKhachHangId()));
				diaChiEntity.setHoTen(result.getHoTen());
				diaChiEntity.setSoDienThoai(result.getSoDienThoai());
				diaChiEntity = diaChiRepository.save(diaChiEntity);
				if(diaChiEntity.getId() != null) {
					diaChiDTO.setId(diaChiEntity.getId());
					diaChiDTO.setDiaChi(diaChiEntity.getDiaChi());
					diaChiDTO.setCity(result.getCity());
					diaChiDTO.setDistrict(result.getDistrict());
					diaChiDTO.setWard(result.getWard());
					diaChiDTO.setSoNha(result.getSoNha());
					diaChiDTO.setHoTen(result.getHoTen());
					diaChiDTO.setSoDienThoai(result.getSoDienThoai());
					diaChiDTO.setKhachHangId(result.getKhachHangId());
					return diaChiDTO;
				}
			}
			return null ;
		}catch( UnexpectedRollbackException e) {
			return null ;
		}

	}
	@Override
	public void delete(long[] ids) {
		for (long id : ids) {
			diaChiRepository.deleteById(id);
		}
	}
	@Override
	public int countByMaKhachHang(Long id ) {
		return diaChiRepository.countByMaKhachHang(id);
	}
	@SuppressWarnings("null")
	@Override
	public List<DiaChiDTO> findAllDiaChiByMaKhachHang(Long id, Pageable pageale) {
		List<DiaChiDTO> listDiaChiDTO = new ArrayList<DiaChiDTO>();
		List<DiaChi> listDiaChiEntity = new ArrayList<DiaChi>();
		DiaChiDTO diaChiDTO = null;
		listDiaChiEntity = diaChiRepository.findAllByMaKhachHang(id,pageale).getContent();
		for (DiaChi diaChi : listDiaChiEntity) {
			diaChiDTO = new DiaChiDTO();
			diaChiDTO.setId(diaChi.getId());
			diaChiDTO.setDiaChi(diaChi.getDiaChi());
			diaChiDTO.setSoDienThoai(diaChi.getSoDienThoai());
			diaChiDTO.setHoTen(diaChi.getHoTen());
			listDiaChiDTO.add(diaChiDTO);
		}
		return listDiaChiDTO;
	}
	@Override
	public DiaChiDTO findById(Long id) {
		DiaChiDTO dto = null ;
		DiaChi entity = diaChiRepository.getOne(id);
		if(entity.getId() != null) {
			dto = new DiaChiDTO() ;
			dto.setId(entity.getId());
			dto.setDiaChi(entity.getDiaChi());
			dto.setHoTen(entity.getHoTen());
			dto.setSoDienThoai(entity.getSoDienThoai());
		}
		return  dto ;
	}
	@Override
	@Transactional
	public void update(DiaChiDTO diaChiDTO) {
		if(diaChiDTO.getId() != null) {

			DiaChi entity = new DiaChi();

			entity.setId(diaChiDTO.getId());
			entity.setDiaChi(diaChiDTO.getCity()+"-"+diaChiDTO.getDistrict()+"-"+diaChiDTO.getWard()+"-"+diaChiDTO.getSoNha());
			entity.setSoDienThoai(diaChiDTO.getSoDienThoai());
			entity.setHoTen(diaChiDTO.getHoTen());
			entity.setKhachHang(khachHangRepository.getOne(diaChiDTO.getKhachHangId()));
			diaChiRepository.save(entity);
		}
	}

}