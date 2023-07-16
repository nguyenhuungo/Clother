package com.fpoly.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fpoly.convertor.DiaChiConvertor;
import com.fpoly.convertor.KhachHangConvertor;
import com.fpoly.convertor.NguoiDungConvertor;
import com.fpoly.dto.DiaChiDTO;
import com.fpoly.dto.KhachHangDTO;
import com.fpoly.entity.DiaChi;
import com.fpoly.entity.KhachHang;
import com.fpoly.entity.NguoiDung;
import com.fpoly.repository.KhachHangRepository;
import com.fpoly.repository.NguoiDungRepository;
import com.fpoly.service.KhachHangService;

@Service
public class KhachHangServiceImpl implements KhachHangService{

	@Autowired
	private KhachHangRepository khachHangRepository ;
	
	@Autowired
	private NguoiDungRepository nguoiDungRepository ;
	
	@Autowired 
	private KhachHangConvertor khachHangConvertor ;
	
	@Autowired
	private DiaChiConvertor diaChiConvertor ;
	
	@Autowired
	private NguoiDungConvertor nguoiDungConvertor ;
	
	@Override
	public List<KhachHangDTO> findAllByTrangThaiCoPhanTrang(Integer trangThai,Pageable pageable) {
		List<KhachHangDTO> listKhachHangDTO = new ArrayList<KhachHangDTO>() ;
		List<KhachHang> listKhachHang = new ArrayList<KhachHang>();
		KhachHangDTO dto = null ;
		DiaChiDTO diaChiDTO = null;
		if(trangThai != null) {
			 if(trangThai != 2) {
				 listKhachHang = khachHangRepository.findAllByTrangThaiCoPhanTrang(trangThai, pageable).getContent();
					for (KhachHang khachHang : listKhachHang) {
						dto = new KhachHangDTO();
						dto = khachHangConvertor.toDTO(khachHang);
						List<DiaChiDTO> listDiaChiDTO = new ArrayList<DiaChiDTO>();
						for (DiaChi diaChi : khachHang.getListDiaChi()) {
							diaChiDTO = new DiaChiDTO();
							diaChiDTO = diaChiConvertor.toDTO(diaChi);
							listDiaChiDTO.add(diaChiDTO);
						}
						dto.setListDiaChi(listDiaChiDTO);
						listKhachHangDTO.add(dto);
					}
			 }
		}
		return listKhachHangDTO ;
	}
	
	@Override
	public List<KhachHangDTO> findAllByInputVaTrangThaiCoPhanTrang(String input, Integer trangThai,
			Pageable pageable) {
		List<KhachHangDTO> listKhachHangDTO = new ArrayList<KhachHangDTO>() ;
		List<KhachHang> listKhachHang = new ArrayList<KhachHang>();
		KhachHangDTO dto = null ;
		DiaChiDTO diaChiDTO = null;
		if(trangThai != null) {
			 if(trangThai != 2) {
				 listKhachHang = khachHangRepository.
						 findAllByTrangThaiVaSoDienThoaiCoPhanTrang(trangThai,input, pageable).getContent();
					for (KhachHang khachHang : listKhachHang) {
						dto = new KhachHangDTO();
						dto = khachHangConvertor.toDTO(khachHang);
						List<DiaChiDTO> listDiaChiDTO = new ArrayList<DiaChiDTO>();
						for (DiaChi diaChi : khachHang.getListDiaChi()) {
							diaChiDTO = new DiaChiDTO();
							diaChiDTO = diaChiConvertor.toDTO(diaChi);
							listDiaChiDTO.add(diaChiDTO);
						}
						dto.setListDiaChi(listDiaChiDTO);
						listKhachHangDTO.add(dto);
					}
			 }
		}
		return listKhachHangDTO ;
	}
	
	
	
	
	

	@SuppressWarnings("deprecation")
	@Override
	@Transactional
	public void capNhatTrangThaiThanhDangHoatDongTheoMa(long[] ids) {
		for (long id : ids) {
			KhachHang khachHangEntity = khachHangRepository.getOne(id);
			NguoiDung nguoiDungEntity = nguoiDungRepository.findByEmail(khachHangEntity.getEmail());
			khachHangRepository.capNhatTrangThaiThanhHoatDongTheoMa(id);
			nguoiDungRepository.capNhatTrangThaiThanhHoatDongTheoMa(nguoiDungEntity.getId());
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	@Transactional
	public void capNhatTrangThaiThanhKhongHoatDongTheoMa(long[] ids) {
		for (long id : ids) {
			KhachHang khachHangEntity = khachHangRepository.getOne(id);
			NguoiDung nguoiDungEntity = nguoiDungRepository.findByEmail(khachHangEntity.getEmail());
			khachHangRepository.capNhatTrangThaiThanhKhongHoatDongTheoMa(id);
			nguoiDungRepository.capNhatTrangThaiThanhKhongHoatDongTheoMa(nguoiDungEntity.getId());
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public KhachHangDTO findById(Long id) {
		KhachHangDTO dto = new KhachHangDTO();
		KhachHang entity = khachHangRepository.getOne(id);
		if(entity != null) {
			dto = khachHangConvertor.toDTO(entity);
			List<DiaChiDTO> listDiaChiDTO = new ArrayList<DiaChiDTO>();
			for (DiaChi diaChi : entity.getListDiaChi()) {
				DiaChiDTO diaChiDTO = new DiaChiDTO();
				diaChiDTO = diaChiConvertor.toDTO(diaChi);
				listDiaChiDTO.add(diaChiDTO);
			}
			dto.setListDiaChi(listDiaChiDTO);
		}
		return dto;
	}

	@SuppressWarnings("deprecation")
	@Override
	@Transactional
	public KhachHangDTO save(KhachHangDTO dto) {
		KhachHangDTO khachHangDTO = new KhachHangDTO();
		KhachHang khachHangEntity = null ;
		NguoiDung nguoiDungEntity = null ;
		List<KhachHang> listKhachHang = khachHangRepository.findAll();
					try {
						//Cập nhật
						if(dto.getId() != null){
								 khachHangEntity = khachHangRepository.getOne(dto.getId());
								 nguoiDungEntity = new NguoiDung();
								NguoiDung  oldNguoiDungEntity = nguoiDungRepository.findByEmail(khachHangEntity.getEmail());
								if(!dto.getEmail().equalsIgnoreCase(khachHangEntity.getEmail())) {
									for (KhachHang khachHang : listKhachHang) {
										if(khachHang.getEmail().equalsIgnoreCase(dto.getEmail())) {
											return null ;
										}
									}
								}
									dto.setSoLanMua(khachHangEntity.getSoLanMua());
									khachHangEntity = khachHangConvertor.toEntity(dto);
									nguoiDungEntity = nguoiDungConvertor
											.toEntityByKhachHangDTOAndOldNguoiDungEntity(dto, oldNguoiDungEntity);
									
						}else {
							//Thêm mới
							khachHangEntity = new KhachHang();
							nguoiDungEntity = new NguoiDung();
							for (KhachHang khachHang : listKhachHang) {
								if(khachHang.getEmail().equalsIgnoreCase(dto.getEmail())) {
									return null ;
								}
							}
							dto.setSoLanMua(0);
							khachHangEntity = khachHangConvertor.toEntity(dto);
							nguoiDungEntity = nguoiDungConvertor.toEntityByKhachHangDTO(dto);
						}
						khachHangEntity = khachHangRepository.save(khachHangEntity);
						nguoiDungEntity = nguoiDungRepository.save(nguoiDungEntity);
						khachHangDTO = khachHangConvertor.toDTO(khachHangEntity);
						return khachHangDTO;
					}catch(Exception e) {
						e.printStackTrace();
					}
					return khachHangDTO ;
		}

	@Override
	public List<KhachHangDTO> findAll(Pageable pageable) {
		List<KhachHangDTO> listKhachHangDTO = new ArrayList<KhachHangDTO>() ;
		List<KhachHang> listKhachHang = new ArrayList<KhachHang>();
		KhachHangDTO dto = null ;
		DiaChiDTO diaChiDTO = null;
		
 		listKhachHang = khachHangRepository.findAll(pageable).getContent();
		 for (KhachHang khachHang : listKhachHang) {
			    dto = new KhachHangDTO();
			    dto = khachHangConvertor.toDTO(khachHang);
				List<DiaChiDTO> listDiaChiDTO = new ArrayList<DiaChiDTO>();
				for (DiaChi diaChi : khachHang.getListDiaChi()) {
					diaChiDTO = new DiaChiDTO();
					diaChiDTO  = diaChiConvertor.toDTO(diaChi);
					listDiaChiDTO.add(diaChiDTO);
				}
				dto.setListDiaChi(listDiaChiDTO);
				listKhachHangDTO.add(dto);
		 }
		 
		 return listKhachHangDTO ;
	}



	@Override
	public List<KhachHangDTO> findAll() {
		List<KhachHangDTO> listKhachHangDTO = new ArrayList<KhachHangDTO>() ;
		List<KhachHang> listKhachHang = new ArrayList<KhachHang>();
		KhachHangDTO dto = null ;
		DiaChiDTO diaChiDTO = null;
		
		listKhachHang = khachHangRepository.findAll();
		 for (KhachHang khachHang : listKhachHang) {
			    dto = new KhachHangDTO();
			    dto = khachHangConvertor.toDTO(khachHang);
				List<DiaChiDTO> listDiaChiDTO = new ArrayList<DiaChiDTO>();
				for (DiaChi diaChi : khachHang.getListDiaChi()) {
					diaChiDTO = new DiaChiDTO();
					diaChiDTO  = diaChiConvertor.toDTO(diaChi);
					listDiaChiDTO.add(diaChiDTO);
				}
				dto.setListDiaChi(listDiaChiDTO);
				listKhachHangDTO.add(dto);
		 }
		 return listKhachHangDTO ;
	}



	@Override
	public List<KhachHangDTO> findAllByInputCoPhanTrang(String soDienThoai,Pageable pageable) {
		List<KhachHangDTO> listKhachHangDTO = new ArrayList<KhachHangDTO>() ;
		List<KhachHang> listKhachHang = new ArrayList<KhachHang>();
		KhachHangDTO dto = null ;
		DiaChiDTO diaChiDTO = null;
		if(soDienThoai != null) {
			 listKhachHang = khachHangRepository.findAllBySoDienThoaiCoPhanTrang(soDienThoai, pageable).getContent();
			for (KhachHang khachHang : listKhachHang) {
				dto = new KhachHangDTO();
				dto = khachHangConvertor.toDTO(khachHang);
				List<DiaChiDTO> listDiaChiDTO = new ArrayList<DiaChiDTO>();
					for (DiaChi diaChi : khachHang.getListDiaChi()) {
						diaChiDTO = new DiaChiDTO();
						diaChiDTO = diaChiConvertor.toDTO(diaChi);
						listDiaChiDTO.add(diaChiDTO);
					}
				dto.setListDiaChi(listDiaChiDTO);
				listKhachHangDTO.add(dto);
				}
		}
		return listKhachHangDTO ;
	}



	@Override
	public int countAll() {
		return (int) khachHangRepository.count();
	}



	@Override
	public int countByTrangThai(Integer trangThai) {
		return khachHangRepository.countByTrangThai(trangThai);
	}



	@Override
	public int countByInput(String input) {
		return khachHangRepository.countByInput(input);
	}

	@Override
	public int countByInputVaTrangThai(String input, Integer trangThai) {
		return khachHangRepository.countByInputVaTrangThai(input,trangThai);
	}

	@SuppressWarnings("deprecation")
	@Override
	@Transactional
	public void capNhatTrangThaiTheoId(Long id) {
		KhachHang entity = khachHangRepository.getOne(id);
		NguoiDung nguoiDungEntity = nguoiDungRepository.findByEmail(entity.getEmail());
		if(entity != null) {
			if(entity.getTrangThai() == 1) {
				nguoiDungRepository.capNhatTrangThaiThanhKhongHoatDongTheoMa(nguoiDungEntity.getId());
				khachHangRepository.capNhatTrangThaiThanhKhongHoatDongTheoMa(entity.getId());
			}
			if(entity.getTrangThai() == 0) {
				nguoiDungRepository.capNhatTrangThaiThanhHoatDongTheoMa(nguoiDungEntity.getId());
				khachHangRepository.capNhatTrangThaiThanhHoatDongTheoMa(entity.getId());
			}
		}
	}

	@Override
	public void updateUserStatus(Long id, int trangThai) {
		KhachHang user = khachHangRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Khách hàng không tồn tại"));
        user.setTrangThai(trangThai);
        khachHangRepository.save(user);
	}

	
//	@Autowired
//	private KhachHangRepository khachHangRepository ;
//	
//	@Override
//	public List<KhachHangDTO> findAllByTrangThaiCoPhanTrang(Integer trangThai,Pageable pageable) {
//		List<KhachHangDTO> listKhachHangDTO = new ArrayList<KhachHangDTO>() ;
//		List<KhachHang> listKhachHang = new ArrayList<KhachHang>();
//		KhachHangDTO dto = null ;
//		DiaChiDTO diaChiDTO = null;
//		if(trangThai != null) {
//			 if(trangThai != 2) {
//				 listKhachHang = khachHangRepository.findAllByTrangThaiCoPhanTrang(trangThai, pageable).getContent();
//					for (KhachHang khachHang : listKhachHang) {
//						dto = new KhachHangDTO();
//						dto.setId(khachHang.getId());
//						dto.setEmail(khachHang.getEmail());
//						dto.setMatKhau(khachHang.getMatKhau());
//						dto.setHoTen(khachHang.getHoTen());
//						dto.setSoDienThoai(khachHang.getSoDienThoai());
//						dto.setTrangThai(khachHang.getTrangThai());
//						List<DiaChiDTO> listDiaChiDTO = new ArrayList<DiaChiDTO>();
//						for (DiaChi diaChi : khachHang.getListDiaChi()) {
//							diaChiDTO = new DiaChiDTO();
//							diaChiDTO.setId(diaChi.getId());
//							diaChiDTO.setDiaChi(diaChi.getDiaChi());
//							listDiaChiDTO.add(diaChiDTO);
//						}
//						dto.setListDiaChi(listDiaChiDTO);
//						listKhachHangDTO.add(dto);
//					}
//			 }
//		}
//		return listKhachHangDTO ;
//	}
//	
//	@Override
//	public List<KhachHangDTO> findAllBySoDienThoaiVaTrangThaiCoPhanTrang(String soDienThoai, Integer trangThai,
//			Pageable pageable) {
//		List<KhachHangDTO> listKhachHangDTO = new ArrayList<KhachHangDTO>() ;
//		List<KhachHang> listKhachHang = new ArrayList<KhachHang>();
//		KhachHangDTO dto = null ;
//		DiaChiDTO diaChiDTO = null;
//		if(trangThai != null) {
//			 if(trangThai != 2) {
//				 listKhachHang = khachHangRepository.findAllByTrangThaiVaSoDienThoaiCoPhanTrang(trangThai,soDienThoai, pageable).getContent();
//					for (KhachHang khachHang : listKhachHang) {
//						dto = new KhachHangDTO();
//						dto.setId(khachHang.getId());
//						dto.setEmail(khachHang.getEmail());
//						dto.setMatKhau(khachHang.getMatKhau());
//						dto.setHoTen(khachHang.getHoTen());
//						dto.setSoDienThoai(khachHang.getSoDienThoai());
//						dto.setTrangThai(khachHang.getTrangThai());
//						List<DiaChiDTO> listDiaChiDTO = new ArrayList<DiaChiDTO>();
//						for (DiaChi diaChi : khachHang.getListDiaChi()) {
//							diaChiDTO = new DiaChiDTO();
//							diaChiDTO.setId(diaChi.getId());
//							diaChiDTO.setDiaChi(diaChi.getDiaChi());
//							listDiaChiDTO.add(diaChiDTO);
//						}
//						dto.setListDiaChi(listDiaChiDTO);
//						listKhachHangDTO.add(dto);
//					}
//			 }
//		}
//		return listKhachHangDTO ;
//	}
//	
//	
//	
//	@Override
//	public List<KhachHangDTO> locDanhSachTheoTrangThai(Integer trangThai) {
//		List<KhachHangDTO> listKhachHangDTO = new ArrayList<KhachHangDTO>() ;
//		List<KhachHang> listKhachHang = new ArrayList<KhachHang>();
//		KhachHangDTO dto = null ;
//		DiaChiDTO diaChiDTO = null;
//		if(trangThai != null) {
//			if(trangThai != 2) {
//			 listKhachHang = khachHangRepository.findAllByTrangThaiKhongCoPhanTrang(trangThai);
//			for (KhachHang khachHang : listKhachHang) {
//				dto = new KhachHangDTO();
//				dto.setId(khachHang.getId());
//				dto.setEmail(khachHang.getEmail());
//				dto.setMatKhau(khachHang.getMatKhau());
//				dto.setHoTen(khachHang.getHoTen());
//				dto.setSoDienThoai(khachHang.getSoDienThoai());
//				dto.setTrangThai(khachHang.getTrangThai());
//				List<DiaChiDTO> listDiaChiDTO = new ArrayList<DiaChiDTO>();
//					for (DiaChi diaChi : khachHang.getListDiaChi()) {
//						diaChiDTO = new DiaChiDTO();
//						diaChiDTO.setId(diaChi.getId());
//						diaChiDTO.setDiaChi(diaChi.getDiaChi());
//						listDiaChiDTO.add(diaChiDTO);
//					}
//				dto.setListDiaChi(listDiaChiDTO);
//				listKhachHangDTO.add(dto);
//				}
//		    }
//		}
//		return listKhachHangDTO ;
//	}
//	
//	
//
//	@Override
//	@Transactional
//	public void capNhatTrangThaiThanhDangHoatDongTheoMa(long[] ids) {
//		for (long id : ids) {
//			khachHangRepository.capNhatTrangThaiThanhHoatDongTheoMa(id);
//		}
//	}
//
//	@Override
//	@Transactional
//	public void capNhatTrangThaiThanhKhongHoatDongTheoMa(long[] ids) {
//		for (long id : ids) {
//			khachHangRepository.capNhatTrangThaiThanhKhongHoatDongTheoMa(id);
//		}
//	}
//
//	@Override
//	public KhachHangDTO findById(Long id) {
//		KhachHangDTO dto = new KhachHangDTO();
//		KhachHang entity = khachHangRepository.getOne(id);
//		if(entity != null) {
//			dto.setId(entity.getId());
//			dto.setEmail(entity.getEmail());
//			dto.setHoTen(entity.getHoTen());
//			dto.setMatKhau(entity.getMatKhau());
//			dto.setSoDienThoai(entity.getSoDienThoai());
//			List<DiaChiDTO> listDiaChidTO = new ArrayList<DiaChiDTO>();
//			for (DiaChi diaChi : entity.getListDiaChi()) {
//				DiaChiDTO dto1 = new DiaChiDTO();
//				dto1.setId(diaChi.getId());
//				dto1.setDiaChi(diaChi.getDiaChi());
//				listDiaChidTO.add(dto1);
//			}
//			dto.setListDiaChi(listDiaChidTO);
//		}
//		return dto;
//	}
//
//	@Override
//	public KhachHangDTO save(KhachHangDTO dto) {
//		KhachHangDTO khachHangDTO = new KhachHangDTO();
//		KhachHang khachHangEntity = new KhachHang();
////		List<KhachHang> list = khachHangRepository.findAll();
////		for (KhachHang khachHang : list) {
////			if (!khachHang.getEmail().equals(dto.getEmail())) {
//					if(dto.getId() != null){
//						KhachHang khachHangTonTai = khachHangRepository.findByEmail(dto.getEmail());
//						khachHangTonTai.setId(dto.getId());
//						khachHangTonTai.setEmail(dto.getEmail());
//						khachHangTonTai.setHoTen(dto.getHoTen());
//						khachHangTonTai.setMatKhau(dto.getMatKhau());
//						khachHangTonTai.setSoDienThoai(dto.getSoDienThoai());
//						khachHangTonTai.setTrangThai(dto.getTrangThai());
//						khachHangEntity = khachHangTonTai ;
//					}else {
//						KhachHang khachHangMoi = new KhachHang();
//						khachHangMoi.setEmail(dto.getEmail());
//						khachHangMoi.setHoTen(dto.getHoTen());
//						khachHangMoi.setMatKhau(dto.getMatKhau());
//						khachHangMoi.setSoDienThoai(dto.getSoDienThoai());
//						khachHangMoi.setTrangThai(1);
//						khachHangEntity = khachHangMoi ;
//					}
//					khachHangEntity = khachHangRepository.save(khachHangEntity);
//					khachHangDTO.setId(khachHangEntity.getId());
//					khachHangDTO.setEmail(khachHangEntity.getEmail());
//					khachHangDTO.setHoTen(khachHangEntity.getHoTen());
//					khachHangDTO.setMatKhau(khachHangEntity.getMatKhau());
//					khachHangDTO.setSoDienThoai(khachHangEntity.getSoDienThoai());
////				}else {
////					 new SQLIntegrityConstraintViolationException();
////				}
//					return khachHangDTO;
//				
//		}
//
//	@Override
//	public List<KhachHangDTO> findAll(Pageable pageable) {
//		List<KhachHangDTO> listKhachHangDTO = new ArrayList<KhachHangDTO>() ;
//		List<KhachHang> listKhachHang = new ArrayList<KhachHang>();
//		KhachHangDTO dto = null ;
//		DiaChiDTO diaChiDTO = null;
//		
// 		listKhachHang = khachHangRepository.findAll(pageable).getContent();
//		 for (KhachHang khachHang : listKhachHang) {
//			    dto = new KhachHangDTO();
//			    dto.setId(khachHang.getId());
//				dto.setEmail(khachHang.getEmail());
//				dto.setMatKhau(khachHang.getMatKhau());
//				dto.setHoTen(khachHang.getHoTen());
//				dto.setSoDienThoai(khachHang.getSoDienThoai());
//				dto.setTrangThai(khachHang.getTrangThai());
//				List<DiaChiDTO> listDiaChiDTO = new ArrayList<DiaChiDTO>();
//				for (DiaChi diaChi : khachHang.getListDiaChi()) {
//					diaChiDTO = new DiaChiDTO();
//					diaChiDTO.setId(diaChi.getId());
//					diaChiDTO.setDiaChi(diaChi.getDiaChi());
//					listDiaChiDTO.add(diaChiDTO);
//				}
//				dto.setListDiaChi(listDiaChiDTO);
//				listKhachHangDTO.add(dto);
//		 }
//		 
//		 return listKhachHangDTO ;
//	}
//
//
//
//	@Override
//	public List<KhachHangDTO> findAll() {
//		List<KhachHangDTO> listKhachHangDTO = new ArrayList<KhachHangDTO>() ;
//		List<KhachHang> listKhachHang = new ArrayList<KhachHang>();
//		KhachHangDTO dto = null ;
//		DiaChiDTO diaChiDTO = null;
//		
//		listKhachHang = khachHangRepository.findAll();
//		 for (KhachHang khachHang : listKhachHang) {
//			    dto = new KhachHangDTO();
//			    dto.setId(khachHang.getId());
//				dto.setEmail(khachHang.getEmail());
//				dto.setMatKhau(khachHang.getMatKhau());
//				dto.setHoTen(khachHang.getHoTen());
//				dto.setSoDienThoai(khachHang.getSoDienThoai());
//				dto.setTrangThai(khachHang.getTrangThai());
//				List<DiaChiDTO> listDiaChiDTO = new ArrayList<DiaChiDTO>();
//				for (DiaChi diaChi : khachHang.getListDiaChi()) {
//					diaChiDTO = new DiaChiDTO();
//					diaChiDTO.setId(diaChi.getId());
//					diaChiDTO.setDiaChi(diaChi.getDiaChi());
//					listDiaChiDTO.add(diaChiDTO);
//				}
//				dto.setListDiaChi(listDiaChiDTO);
//				listKhachHangDTO.add(dto);
//		 }
//		 return listKhachHangDTO ;
//	}
//
//
//
//	@Override
//	public List<KhachHangDTO> findAllBySoDienThoaiCoPhanTrang(String soDienThoai,Pageable pageable) {
//		List<KhachHangDTO> listKhachHangDTO = new ArrayList<KhachHangDTO>() ;
//		List<KhachHang> listKhachHang = new ArrayList<KhachHang>();
//		KhachHangDTO dto = null ;
//		DiaChiDTO diaChiDTO = null;
//		if(soDienThoai != null) {
//			 listKhachHang = khachHangRepository.findAllBySoDienThoaiCoPhanTrang(soDienThoai, pageable).getContent();
//			for (KhachHang khachHang : listKhachHang) {
//				dto = new KhachHangDTO();
//				dto.setId(khachHang.getId());
//				dto.setEmail(khachHang.getEmail());
//				dto.setMatKhau(khachHang.getMatKhau());
//				dto.setHoTen(khachHang.getHoTen());
//				dto.setSoDienThoai(khachHang.getSoDienThoai());
//				dto.setTrangThai(khachHang.getTrangThai());
//				List<DiaChiDTO> listDiaChiDTO = new ArrayList<DiaChiDTO>();
//					for (DiaChi diaChi : khachHang.getListDiaChi()) {
//						diaChiDTO = new DiaChiDTO();
//						diaChiDTO.setId(diaChi.getId());
//						diaChiDTO.setDiaChi(diaChi.getDiaChi());
//						listDiaChiDTO.add(diaChiDTO);
//					}
//				dto.setListDiaChi(listDiaChiDTO);
//				listKhachHangDTO.add(dto);
//				}
//		}
//		return listKhachHangDTO ;
//	}
//
//
//
//	@Override
//	public int countAll() {
//		return (int) khachHangRepository.count();
//	}
//
//
//
//	@Override
//	public int countByTrangThai(Integer trangThai) {
//		return khachHangRepository.countByTrangThai(trangThai);
//	}
//
//
//
//	@Override
//	public int countBySoDienThoai(String soDienThoai) {
//		return khachHangRepository.countBySoDienThoai(soDienThoai);
//	}
//
//	@Override
//	public int countBySoDienThoaiVaTrangThai(String soDienThoai, Integer trangThai) {
//		return khachHangRepository.countBySoDienThoaiVaTrangThai(soDienThoai,trangThai);
//	}
}
