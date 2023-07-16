package com.fpoly.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fpoly.dto.DiaChiDTO;
import com.fpoly.dto.KhachHangDTO;
import com.fpoly.service.DiaChiService;
import com.fpoly.service.KhachHangService;

@Controller(value="KhachHangControllerOfAdmin")
@RequestMapping("/admin/khach-hang")
public class KhachHangController {
	@Autowired
	private KhachHangService khachHangService ;

	@Autowired
	private DiaChiService diaChiService ;


	@RequestMapping("/danh-sach")
	public String layTatCa(Model model , HttpServletRequest request ) {
		List<KhachHangDTO> listkhachHangDTO = khachHangService.findAll();
		String message = request.getParameter("message");
		model.addAttribute("message",message);
		model.addAttribute("listKhachHangDTO",listkhachHangDTO);
		return  "redirect:/admin/khach-hang/danh-sach/1";
	}

	@SuppressWarnings("static-access")
	@RequestMapping("/danh-sach/{pageNumber}")
	public String layDanhSach(
			@PathVariable(name="pageNumber") Integer page ,
			@RequestParam(name="input",required=false,defaultValue="") String input,
			@RequestParam(name="trangThai",required=false,defaultValue="2" ) Integer trangThai,
			@RequestParam(name="limit",required=false ,defaultValue="5") Integer limit ,
			Model model , HttpServletRequest request ) {
		KhachHangDTO dto = new KhachHangDTO();
		dto.setPage(page);
		dto.setLimit(limit);

		dto.setTrangThai(trangThai);
		dto.setInput(input);
		Pageable pageable = PageRequest.of(page-1,dto.getLimit(),Sort.by(Sort.DEFAULT_DIRECTION.DESC,"id"));
		dto.setListKhachHangDTO(khachHangService.findAll(pageable));
		dto.setTotalItems((int) khachHangService.countAll());
		dto.setTotalPages((int) Math.ceil((double)dto.getTotalItems()/dto.getLimit()));

		String message = request.getParameter("message");
		model.addAttribute("message",message);


		model.addAttribute("input",input);
		model.addAttribute("trangThai",trangThai);
		model.addAttribute("limit",limit);
		model.addAttribute("khachHangDTO",dto);
		return  "/admin/khach-hang/danhSach";
	}


	@PostMapping("/danh-sach")
	public String locDanhSach(
			@RequestParam(name="trangThai",required=false ,defaultValue="2") Integer trangThai ,
			@RequestParam(name="limit",required=false ,defaultValue="5") Integer limit ,
			Model model , HttpServletRequest request ) {
		    String message = request.getParameter("message");
			KhachHangDTO dto = new KhachHangDTO();
			dto.setPage(1);
			dto.setLimit(limit);
			dto.setTrangThai(trangThai);
			Pageable pageable = PageRequest.of(1-1,dto.getLimit(),Sort.by(Sort.DEFAULT_DIRECTION.DESC,"id"));
			if(trangThai == 2) {
				return  "redirect:/admin/khach-hang/danh-sach/1?trangThai="+trangThai+"&limit="+dto.getLimit();
			}else {

					dto.setListKhachHangDTO(khachHangService.findAllByTrangThaiCoPhanTrang(trangThai, pageable));
					dto.setTotalItems((int) khachHangService.countByTrangThai(trangThai));
					dto.setTotalPages((int) Math.ceil((double)dto.getTotalItems()/5));
					model.addAttribute("message",message);

					model.addAttribute("khachHangDTO",dto);
					return  "redirect:/admin/khach-hang/danh-sach/chuyen-doi-trang-thai/1?trangThai="+trangThai+"&limit="+dto.getLimit();
			}
	}

	@RequestMapping("/danh-sach/chuyen-doi-trang-thai/{pageNumber}")
	public String capNhatTrangThai(
									@RequestParam(name="trangThai",required=false) Integer trangThai ,
									@RequestParam(name="limit",required=false ,defaultValue="5") Integer limit ,
									@PathVariable(name="pageNumber") Integer currentPage ,
			Model model , HttpServletRequest request) {
		String message = request.getParameter("message");
		KhachHangDTO dto = new KhachHangDTO();
		dto.setLimit(limit);
		dto.setPage(currentPage);
		dto.setTrangThai(trangThai);
		Pageable pageable = PageRequest.of(currentPage-1,dto.getLimit(),Sort.by(Sort.DEFAULT_DIRECTION.DESC,"id"));
			if(trangThai == 2) {
				return  "redirect:/admin/khach-hang/danh-sach/1?trangThai="+trangThai+"&limit="+dto.getLimit();
			}else  {

				dto.setListKhachHangDTO(khachHangService.findAllByTrangThaiCoPhanTrang(trangThai,pageable));
				dto.setTotalItems((int) khachHangService.countByTrangThai(trangThai));
				dto.setTotalPages((int) Math.ceil((double)dto.getTotalItems()/dto.getLimit()));
			}
		model.addAttribute("khachHangDTO",dto);
		model.addAttribute("trangThai",trangThai);
		model.addAttribute("message",message);
		return  "/admin/khach-hang/danhSach";
	}


	@RequestMapping("danh-sach/tim-kiem/{pageNumber}")
	public String timKiemKhachHangTheoInput(
			@RequestParam(name="input",required=false) String input
			,@RequestParam(name="trangThai",required=false) Integer trangThai
			,@PathVariable(name="pageNumber") Integer currentPage ,
			@RequestParam(name="limit",required=false ,defaultValue="5") Integer limit ,
			Model model
			) {

		KhachHangDTO dto = new KhachHangDTO();
		dto.setPage(currentPage);
		dto.setTrangThai(trangThai);
		dto.setInput(input);
		dto.setLimit(limit);
		Pageable pageable = PageRequest.of(currentPage-1,dto.getLimit(),Sort.by(Sort.DEFAULT_DIRECTION.DESC,"id"));
		if(trangThai == null && input != null) {
			dto.setListKhachHangDTO(khachHangService.findAllByInputCoPhanTrang(input,pageable));
			dto.setTotalItems((int) khachHangService.countByInput(input));
			dto.setTotalPages((int) Math.ceil((double)dto.getTotalItems()/dto.getLimit()));
		}else if(trangThai != null && input != null) {
			if(trangThai == 2) {
				dto.setListKhachHangDTO(khachHangService.findAllByInputCoPhanTrang(input, pageable));
				dto.setTotalItems((int) khachHangService.countByInput(input));
				dto.setTotalPages((int) Math.ceil((double)dto.getTotalItems()/dto.getLimit()));
			}else {
				dto.setListKhachHangDTO(khachHangService.findAllByInputVaTrangThaiCoPhanTrang(input, trangThai, pageable));
				dto.setTotalItems((int) khachHangService.countByInputVaTrangThai(input,trangThai));
				dto.setTotalPages((int) Math.ceil((double)dto.getTotalItems()/dto.getLimit()));
			}
		}

		model.addAttribute("khachHangDTO",dto);
		model.addAttribute("input",input);
		model.addAttribute("trangThai",trangThai);
		return "/admin/khach-hang/danhSach" ;
	}
	@RequestMapping("danh-sach/chinh-sua")
	public String chinhSuaKhachHangForm(
			@RequestParam(value="page",required=false,defaultValue="1") Integer page,
			@RequestParam(value="id",required=false) Long id ,
			@RequestParam(value="email",required=false) String email ,
			@RequestParam(value="diaChiId",required=false) Long diaChiId ,
			Model model ,
			HttpServletRequest request) {
		String message = request.getParameter("message");
		KhachHangDTO khachHangDTO = new KhachHangDTO();
		DiaChiDTO diaChiDTO = null ;


		if(id != null  ) {
			diaChiDTO = new DiaChiDTO();
			diaChiDTO.setId(diaChiId);
			diaChiDTO.setPage(page);
			diaChiDTO.setKhachHangId(id);
			diaChiDTO.setLimit(5);
			Pageable pageable = PageRequest.of(page-1,diaChiDTO.getLimit(),Sort.by(Sort.DEFAULT_DIRECTION.DESC,"id"));
			khachHangDTO = khachHangService.findById(id);
 			diaChiDTO.setListDiaChi(diaChiService.findAllDiaChiByMaKhachHang(khachHangDTO.getId(),pageable));
			diaChiDTO.setTotalItems((int) diaChiService.countByMaKhachHang(khachHangDTO.getId()));
			diaChiDTO.setTotalPages((int) Math.ceil((double)diaChiDTO.getTotalItems()/diaChiDTO.getLimit()));
			if(diaChiId == null) {
				diaChiDTO.setDiaChi(null);
			}
			else if(diaChiId != null) {
				diaChiDTO.setHoTen(diaChiService.findById(diaChiId).getHoTen());
				diaChiDTO.setSoDienThoai(diaChiService.findById(diaChiId).getSoDienThoai());
				diaChiDTO.setDiaChi(diaChiService.findById(diaChiId).getDiaChi());
			}
		}
		model.addAttribute("message",message);
		model.addAttribute("model",khachHangDTO);
		model.addAttribute("diaChiDTO",diaChiDTO);
		model.addAttribute("email",email);
		model.addAttribute("diaChiId",diaChiId);
		return "/admin/khach-hang/chinhSua" ;
	}
	@PostMapping("dia-chi/cap-nhat")
	public String capNhatDiaChi(@Valid @ModelAttribute("diaChiDTO") DiaChiDTO diaChiDTO , BindingResult bindingResult ,
			Model model ,HttpServletRequest request) {
			if(bindingResult.hasErrors()) {
				diaChiDTO.setDiaChi(diaChiService.findById(diaChiDTO.getId()).getDiaChi());
				model.addAttribute("model",khachHangService.findById(diaChiDTO.getKhachHangId()));
				model.addAttribute("diaChiDTO",diaChiDTO);
				model.addAttribute("diaChiId",diaChiDTO.getId());
				return "/admin/khach-hang/chinhSua" ;
			}else {
				diaChiService.update(diaChiDTO);
				return "redirect:/admin/khach-hang/danh-sach/chinh-sua?id="+diaChiDTO.getKhachHangId()
				+"&page="+diaChiDTO.getPage()+"&message=update_address_success";
			}
			}
}
//package com.fpoly.controller.admin;
//
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.fpoly.dto.DiaChiDTO;
//import com.fpoly.dto.KhachHangDTO;
//import com.fpoly.service.DiaChiService;
//import com.fpoly.service.KhachHangService;
//
//@Controller(value="KhachHangControllerOfAdmin")
//@RequestMapping("/admin/khach-hang")
//public class KhachHangController {
//	@Autowired
//	private KhachHangService khachHangService ;
//
//	@Autowired
//	private DiaChiService diaChiService ;
//
//
//	@RequestMapping("/danh-sach")
//	public String layTatCa(Model model , HttpServletRequest request ) {
//		List<KhachHangDTO> listkhachHangDTO = khachHangService.findAll();
//		String message = request.getParameter("message");
//		model.addAttribute("message",message);
//		model.addAttribute("listKhachHangDTO",listkhachHangDTO);
//		return  "redirect:/admin/khach-hang/danh-sach/1";
//	}
//
//
//
//
//	@RequestMapping("/danh-sach/{pageNumber}")
//	public String layDanhSach(
//			@PathVariable(name="pageNumber") Integer page ,
//			@RequestParam(name="soDienThoai",required=false) String soDienThoai,
//			@RequestParam(name="trangThai",required=false ) Integer trangThai,
//			@RequestParam(name="limit",required=false ,defaultValue="10") Integer limit ,
//			Model model , HttpServletRequest request ) {
//		KhachHangDTO dto = new KhachHangDTO();
//		dto.setPage(page);
//		dto.setLimit(limit);
//		Pageable pageable = PageRequest.of(page-1,dto.getLimit());
//		dto.setListKhachHangDTO(khachHangService.findAll(pageable));
//		dto.setTotalItems((int) khachHangService.countAll());
//		dto.setTotalPages((int) Math.ceil((double)dto.getTotalItems()/dto.getLimit()));
//
//		String message = request.getParameter("message");
//		model.addAttribute("message",message);
//
//
//		model.addAttribute("soDienThoai",soDienThoai);
//		model.addAttribute("trangThai",trangThai);
//		model.addAttribute("limit",limit);
//		model.addAttribute("khachHangDTO",dto);
//		return  "/admin/khach-hang/danhSach";
//	}
//
//
//	@PostMapping("/danh-sach")
//	public String locDanhSach(
//			@RequestParam(name="trangThai",required=false ,defaultValue="2") Integer trangThai ,
//			@RequestParam(name="soDienThoai",required=false ) String soDienThoai ,
//			@RequestParam(name="limit",required=false ,defaultValue="10") Integer limit ,
//			Model model , HttpServletRequest request ) {
//		    String message = request.getParameter("message");
//			KhachHangDTO dto = new KhachHangDTO();
//			dto.setPage(1);
//			dto.setLimit(limit);
//			Pageable pageable = PageRequest.of(1-1,dto.getLimit());
//			if(trangThai == 2) {
//				return  "redirect:/admin/khach-hang/danh-sach/1?trangThai="+trangThai+"&soDienThoai="+soDienThoai+"&limit="+dto.getLimit();
//			}else {
//				if(soDienThoai != null && !soDienThoai.equals("")) {
//					dto.setListKhachHangDTO(khachHangService.findAllBySoDienThoaiVaTrangThaiCoPhanTrang(soDienThoai, trangThai, pageable));
//					dto.setTotalItems((int) khachHangService.countBySoDienThoaiVaTrangThai(soDienThoai,trangThai));
//					dto.setTotalPages((int) Math.ceil((double)dto.getTotalItems()/limit));
//
//					model.addAttribute("message",message);
//
//					model.addAttribute("soDienThoai",soDienThoai);
//					model.addAttribute("trangThai",trangThai);
//
//					model.addAttribute("khachHangDTO",dto);
//					return  "redirect:/admin/khach-hang/danh-sach/chuyen-doi-trang-thai/1?trangThai="
//					+trangThai+"&soDienThoai="+soDienThoai+"&limit="+dto.getLimit();
//				}else {
//					dto.setListKhachHangDTO(khachHangService.findAllByTrangThaiCoPhanTrang(trangThai, pageable));
//					dto.setTotalItems((int) khachHangService.countByTrangThai(trangThai));
//					dto.setTotalPages((int) Math.ceil((double)dto.getTotalItems()/5));
//					model.addAttribute("message",message);
//
//					model.addAttribute("soDienThoai",soDienThoai);
//					model.addAttribute("trangThai",trangThai);
//
//					model.addAttribute("khachHangDTO",dto);
//					return  "redirect:/admin/khach-hang/danh-sach/chuyen-doi-trang-thai/1?trangThai="+trangThai;
//				}
//
//			}
//	}
//
//
//
//
//
//
//	@RequestMapping("/danh-sach/chuyen-doi-trang-thai/{pageNumber}")
//	public String capNhatTrangThai(
//									@RequestParam(name="trangThai",required=false) Integer trangThai ,
//									@RequestParam(name="soDienThoai",required=false) String soDienThoai ,
//									@RequestParam(name="limit",required=false ,defaultValue="10") Integer limit ,
//									@PathVariable(name="pageNumber") Integer currentPage ,
//			Model model , HttpServletRequest request) {
//		String message = request.getParameter("message");
//		KhachHangDTO dto = new KhachHangDTO();
//		dto.setLimit(limit);
//		dto.setPage(currentPage);
//		Pageable pageable = PageRequest.of(currentPage-1,dto.getLimit());
//		if(trangThai != null && soDienThoai == null) {
//
//			dto.setListKhachHangDTO(khachHangService.findAllByTrangThaiCoPhanTrang(trangThai,pageable));
//			dto.setTotalItems((int) khachHangService.countByTrangThai(trangThai));
//			dto.setTotalPages((int) Math.ceil((double)dto.getTotalItems()/dto.getLimit()));
//
//
//
//		}else if(trangThai != null && soDienThoai != null) {
//
//
//			dto.setListKhachHangDTO(khachHangService.findAllBySoDienThoaiVaTrangThaiCoPhanTrang(soDienThoai, trangThai, pageable));
//			dto.setTotalItems((int) khachHangService.countBySoDienThoaiVaTrangThai(soDienThoai,trangThai));
//			dto.setTotalPages((int) Math.ceil((double)dto.getTotalItems()/dto.getLimit()));
//
//		}
//		model.addAttribute("khachHangDTO",dto);
//		model.addAttribute("soDienThoai",soDienThoai);
//		model.addAttribute("trangThai",trangThai);
//		model.addAttribute("message",message);
//		return  "/admin/khach-hang/danhSach";
//	}
//
//
//	@RequestMapping("danh-sach/tim-kiem/{pageNumber}")
//	public String timKiemKhachHangTheoSoDienThoai(
//			@RequestParam(name="soDienThoai",required=false) String soDienThoai
//			,@RequestParam(name="trangThai",required=false) Integer trangThai
//			,@PathVariable(name="pageNumber") Integer currentPage ,
//			@RequestParam(name="limit",required=false ,defaultValue="10") Integer limit ,
//			Model model
//			) {
//
//		KhachHangDTO dto = new KhachHangDTO();
//		dto.setPage(currentPage);
//		dto.setLimit(limit);
//		Pageable pageable = PageRequest.of(currentPage-1,dto.getLimit());
//
//		if(trangThai == null && soDienThoai != null) {
//			dto.setListKhachHangDTO(khachHangService.findAllBySoDienThoaiCoPhanTrang(soDienThoai,pageable));
//			dto.setTotalItems((int) khachHangService.countBySoDienThoai(soDienThoai));
//			dto.setTotalPages((int) Math.ceil((double)dto.getTotalItems()/dto.getLimit()));
//		}else if(trangThai != null && soDienThoai != null) {
//			if(trangThai == 2) {
//				dto.setListKhachHangDTO(khachHangService.findAllBySoDienThoaiCoPhanTrang(soDienThoai, pageable));
//				dto.setTotalItems((int) khachHangService.countBySoDienThoai(soDienThoai));
//				dto.setTotalPages((int) Math.ceil((double)dto.getTotalItems()/dto.getLimit()));
//			}else {
//				dto.setListKhachHangDTO(khachHangService.findAllBySoDienThoaiVaTrangThaiCoPhanTrang(soDienThoai, trangThai, pageable));
//				dto.setTotalItems((int) khachHangService.countBySoDienThoaiVaTrangThai(soDienThoai,trangThai));
//				dto.setTotalPages((int) Math.ceil((double)dto.getTotalItems()/dto.getLimit()));
//			}
//		}
//
//		model.addAttribute("khachHangDTO",dto);
//		model.addAttribute("soDienThoai",soDienThoai);
//		model.addAttribute("trangThai",trangThai);
//		return "/admin/khach-hang/danhSach" ;
//	}
//
//
//	@RequestMapping("danh-sach/chinh-sua")
//	public ModelAndView chinhSuaKhachHangForm(
//			@RequestParam(value="page",required=false,defaultValue="1") Integer page,
//			@RequestParam(value="id",required=false) Long id ,
//			@RequestParam(value="email",required=false) String email ,
//			HttpServletRequest request) {
//		ModelAndView mav = new ModelAndView("/admin/khach-hang/chinhSua");
//		String message = request.getParameter("message");
//		KhachHangDTO model = new KhachHangDTO();
//		DiaChiDTO diaChiDTO = new DiaChiDTO();
//		diaChiDTO.setPage(page);
//
//		Pageable pageable = PageRequest.of(page-1,6);
//		if(id != null) {
//			model = khachHangService.findById(id);
// 			diaChiDTO.setListDiaChi(diaChiService.findAllDiaChiByMaKhachHang(model.getId(),pageable));
//			diaChiDTO.setTotalItems((int) diaChiService.countByMaKhachHang(model.getId()));
//			diaChiDTO.setTotalPages((int) Math.ceil((double)diaChiDTO.getTotalItems()/6));
//		}
//		mav.addObject("message",message);
//		mav.addObject("model",model);
//		mav.addObject("diaChiDTO",diaChiDTO);
//		mav.addObject("email",email);
//		return mav ;
//	}
//
//
//
//
//
//
//
//
//}
