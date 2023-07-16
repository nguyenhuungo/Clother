package com.fpoly.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fpoly.constant.OptionContants;
import com.fpoly.constant.pageContants;
import com.fpoly.dto.ChatLieuDTO;
import com.fpoly.dto.HinhAnhDTO;
import com.fpoly.dto.KichCoDTO;
import com.fpoly.dto.KieuDangDTO;
import com.fpoly.dto.LoaiSanPhamDTO;
import com.fpoly.dto.MauSacDTO;
import com.fpoly.dto.PhongCachDTO;
import com.fpoly.dto.SanPhamChiTietDTO;
import com.fpoly.dto.composite.HinhAnhMauSacDTO;
import com.fpoly.dto.composite.SanPhamManageDTO;
import com.fpoly.dto.search.SPAndSPCTSearchDto;
import com.fpoly.entity.ChatLieu;
import com.fpoly.entity.HinhAnh;
import com.fpoly.entity.KichCo;
import com.fpoly.entity.KieuDang;
import com.fpoly.entity.LoaiSanPham;
import com.fpoly.entity.MauSac;
import com.fpoly.entity.PhongCach;
import com.fpoly.entity.SanPham;
import com.fpoly.entity.SanPhamChiTiet;
import com.fpoly.service.ChatLieuService;
import com.fpoly.service.HinhAnhService;
import com.fpoly.service.KichCoService;
import com.fpoly.service.KieuDangService;
import com.fpoly.service.LoaiSanPhamService;
import com.fpoly.service.MauSacService;
import com.fpoly.service.PhongCachService;
import com.fpoly.service.SanPhamChiTietService;
import com.fpoly.service.SanPhamService;
import com.fpoly.service.StorageService;

@Controller
@RequestMapping("admin/product")
public class SanPhamChiTietController {
	@Autowired
	private SanPhamChiTietService sanPhamChiTietService;

	@Autowired
	private SanPhamService sanPhamService;

	@Autowired
	private MauSacService mauSacService;

	@Autowired
	private ChatLieuService chatLieuService;

	@Autowired
	private KichCoService kichCoService;

	@Autowired
	private LoaiSanPhamService loaiSanPhamService;

	@Autowired
	private PhongCachService phongCachService;

	@Autowired
	private KieuDangService kieuDangService;

	@Autowired
	private StorageService storageService;

	@Autowired
	private HinhAnhService hinhAnhService;

//	private ProductDetailsWithColorSizeRepository productDetailsWithColorSizeRepository;

	@GetMapping("/images/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
		Resource file = storageService.loadAsResource(filename);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@ModelAttribute("lstMauSac")
	public List<MauSacDTO> getLstMauSac() {
		return mauSacService.selectAllMauSacExist().stream().map(item -> {
			MauSacDTO dto = new MauSacDTO();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).collect(Collectors.toList());
	}

	@ModelAttribute("lstKieuDang")
	public List<KieuDangDTO> getLstKieuDang() {
		return kieuDangService.selectAllKieuDangExist().stream().map(item -> {
			KieuDangDTO dto = new KieuDangDTO();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).collect(Collectors.toList());
	}

	@ModelAttribute("lstChatLieu")
	public List<ChatLieuDTO> getLstChatLieu() {
		return chatLieuService.selectAllChatLieuExist().stream().map(item -> {
			ChatLieuDTO dto = new ChatLieuDTO();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).collect(Collectors.toList());
	}

	@ModelAttribute("lstKichCo")
	public List<KichCoDTO> getLstKichCo() {
		return kichCoService.selectAllKichCoExist().stream().map(item -> {
			KichCoDTO dto = new KichCoDTO();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).collect(Collectors.toList());
	}

	@ModelAttribute("lstLoaiSanPham")
	public List<LoaiSanPhamDTO> getLstLoaiHang() {
		return loaiSanPhamService.selectAllLoaiHangExist().stream().map(item -> {
			LoaiSanPhamDTO dto = new LoaiSanPhamDTO();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).collect(Collectors.toList());
	}

	@ModelAttribute("lstPhongCach")
	public List<PhongCachDTO> getLstPhongCach() {
		return phongCachService.selectAllPhongCachExist().stream().map(item -> {
			PhongCachDTO dto = new PhongCachDTO();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).collect(Collectors.toList());
	}

	@GetMapping("")
	public String search(ModelMap model, @ModelAttribute(name = "dataSearch") SPAndSPCTSearchDto dataSearch,
			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(10);

		Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
		Page<SanPham> resultPage = null;
		Optional<SPAndSPCTSearchDto> optDataSearch = Optional.of(dataSearch);
		if (optDataSearch.isPresent()) {
			resultPage = sanPhamService.searchProductExist(dataSearch, pageable);
			model.addAttribute("dataSearch", dataSearch);
		}

		int totalPages = resultPage.getTotalPages();
		if (totalPages > 0) {
			int start = Math.max(1, currentPage - 2);
			int end = Math.min(currentPage + 2, totalPages);
			if (totalPages > 5) {
				if (end == totalPages) {
					start = end - 5;
				} else if (start == 1) {
					end = start + 5;
				}
			}
			List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		model.addAttribute("sanPhamPage", resultPage);
		return "admin/product/productManage";
	}

//	@GetMapping("")
//	public String productManage(ModelMap model) {
//		List<SanPham> resultSP = sanPhamService.getSanPhamExist();
//		model.addAttribute("sanPhams", resultSP);
//		model.addAttribute("dataSearch", new SPAndSPCTSearchDto());
//		return "admin/product/productManage";
//	}
	@GetMapping("info/{id}")
	public String infoProductDetai(ModelMap model, @PathVariable("id") Long id) {
		Optional<SanPham> opt = sanPhamService.findById(id);
		if (opt.isPresent()) {
			model.addAttribute("sanPham", opt.get());
		}
		return "admin/product/infoProduct";
	}

	@GetMapping("add")
	public ModelAndView addProductDetail(ModelMap model) {
		model.addAttribute("sanPhamManageDTO", new SanPhamManageDTO());
		return new ModelAndView("admin/product/addProduct");
	}

	@PostMapping("generateProductDetails")
	public String generateProductDetails(ModelMap model,
			@Valid @ModelAttribute("sanPhamManageDTO") SanPhamManageDTO data, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("sanPhamManageDTO", data);
			return "admin/product/addProduct";
		} else {
			SanPham sanPham = new SanPham();
			sanPham.setDaXoa(false);
			sanPham.setGia(data.getGia());
			sanPham.setTenSanPham(data.getTenSanPham());
			sanPham.setMoTa(data.getMoTa());

			ChatLieu chatLieu = new ChatLieu();
			chatLieu.setId(data.getChatLieuId());
			;
			sanPham.setChatLieu(chatLieu);

			LoaiSanPham loaiSanPham = new LoaiSanPham();
			loaiSanPham.setId(data.getLoaiSanPhamId());
			sanPham.setLoaiSanPham(loaiSanPham);

			PhongCach phongCach = new PhongCach();
			phongCach.setId(data.getPhongCachId());
			sanPham.setPhongCach(phongCach);

			KieuDang kieuDang = new KieuDang();
			kieuDang.setId(data.getKieuDangId());
			sanPham.setKieuDang(kieuDang);

			sanPhamService.save(sanPham);

			for (Long kichCoId : data.getKichCoIds()) {
				for (Long mauSacId : data.getMauSacIds()) {
					SanPhamChiTiet spct = new SanPhamChiTiet();
					spct.setCoHienThi(true);
					spct.setDaXoa(false);
					spct.setSanPham(sanPham);
					int soLuong = data.getSoLuong();
					spct.setSoLuong(soLuong);
					String maSPCT = UUID.randomUUID().toString();
					spct.setMaSanPhamChiTiet(maSPCT);

					KichCo kichCo = new KichCo();
					kichCo.setId(kichCoId);
					spct.setKichCo(kichCo);

					MauSac mauSac = new MauSac();
					mauSac.setId(mauSacId);
					spct.setMauSac(mauSac);

					sanPhamChiTietService.save(spct);
				}
			}
			data.setIsEdit(true);
			List<SanPhamChiTiet> dataGen = sanPhamChiTietService.getLstSanPhamChiTietBySanPhamId(sanPham.getId());
			dataGen.forEach(i -> {
				Optional<MauSac> optMS = mauSacService.findById(i.getMauSac().getId());
				Optional<KichCo> optKC = kichCoService.findById(i.getKichCo().getId());
				i.setMauSac(optMS.get());
				i.setKichCo(optKC.get());
			});
			data.setIsEdit(false);
			data.setSanPhamId(sanPham.getId());
			model.addAttribute("dataGen", dataGen);
			model.addAttribute("sanPhamManageDTO", data);
			return "/admin/product/addProduct";
		}
	}

	@GetMapping("changeIsShowFormAddProduct/{id}/{status}")
	public ModelAndView changeIsShowFormAddProduct(ModelMap model, @PathVariable("id") Long id,
			@PathVariable("status") Boolean status, @ModelAttribute("sanPhamManageDTO") SanPhamManageDTO data) {
		Optional<SanPhamChiTiet> opt = sanPhamChiTietService.findById(id);
		if (opt.isPresent()) {
			opt.get().setCoHienThi(status);
			sanPhamChiTietService.save(opt.get());
			model.addAttribute("messageSuccess", "Sửa trạng thái hiển thị của sản phẩm thành công");
		} else
			model.addAttribute("messageDanger", "Sửa trạng thái hiển thị của sản phẩm thất bại");
		List<SanPhamChiTiet> dataGen = sanPhamChiTietService
				.getLstSanPhamChiTietBySanPhamId(opt.get().getSanPham().getId());
		model.addAttribute("dataGen", dataGen);
		model.addAttribute("sanPhamManageDTO", data);
		return new ModelAndView("admin/product/addProduct", model);
	}

	@GetMapping("edit/{id}")
	public String edit(ModelMap model, @PathVariable("id") Long id) {
		Optional<SanPham> optSP = sanPhamService.findById(id);
		if (optSP.isPresent()) {
			List<SanPhamChiTiet> dataGen = sanPhamChiTietService.getLstSanPhamChiTietBySanPhamId(optSP.get().getId());
			SanPhamManageDTO dto = new SanPhamManageDTO();
			BeanUtils.copyProperties(optSP.get(), dto);
			dto.setSanPhamId(optSP.get().getId());
			dto.setIsEdit(true);
			dto.setLoaiSanPhamId(optSP.get().getLoaiSanPham().getId());
			dto.setKieuDangId(optSP.get().getKieuDang().getId());
			dto.setChatLieuId(optSP.get().getChatLieu().getId());
			dto.setPhongCachId(optSP.get().getPhongCach().getId());
			List<Long> lstKC = dataGen.stream().map(i -> i.getKichCo().getId()).collect(Collectors.toList());
			List<Long> lstMS = dataGen.stream().map(i -> i.getMauSac().getId()).collect(Collectors.toList());
			dto.setKichCoIds(lstKC);
			dto.setMauSacIds(lstMS);

			List<HinhAnh> lstHinhAnh = hinhAnhService.getLstHinhAnhMauSacBySanPhamId(id);
			List<List<HinhAnhDTO>> lstHinhAnhDTOs = new ArrayList<>();
			int i = 0;
			int j = 0;
			int countLstHinhAnh = 0;
			do {
				List<HinhAnhDTO> HinhAnhDTOs = new ArrayList<>();
				for (j = i; j < lstHinhAnh.size(); j++) {
					if (lstHinhAnh.get(j).getMauSac().getId().equals(lstHinhAnh.get(i).getMauSac().getId())) {
						HinhAnhDTO haDto = new HinhAnhDTO();
						BeanUtils.copyProperties(lstHinhAnh.get(j), haDto);
						haDto.setCoHienThi(lstHinhAnh.get(j).getCoHienThi());
						haDto.setLaAnhChinh(lstHinhAnh.get(j).getLaAnhChinh());
						haDto.setHinhAnhid(lstHinhAnh.get(j).getId());
						HinhAnhDTOs.add(haDto);
					} else {
						i = j;
						break;
					}
				}
				countLstHinhAnh++;

				lstHinhAnhDTOs.add(HinhAnhDTOs);
				if (j == lstHinhAnh.size())
					break;
			} while (j < lstHinhAnh.size());
			int m = 0;
			List<HinhAnhMauSacDTO> lstHinhAnhMauSacDTO = new ArrayList<>();
			List<Long> lstHinhAnhDistinct = hinhAnhService.getDistinctMauSacInHinhAnhBySanPhamId(id);
			for (Long mauSacId : lstHinhAnhDistinct) {
				HinhAnhMauSacDTO hinhAnhMauSacDTO = new HinhAnhMauSacDTO();
				Optional<MauSac> optMS = mauSacService.findById(mauSacId);
				if (optMS.isPresent()) {
					hinhAnhMauSacDTO.setTenMauSacAddImg(optMS.get().getTenMauSac());
					hinhAnhMauSacDTO.setMauSacAddImagesId(mauSacId);
				}
				if (m < countLstHinhAnh) {
					List<HinhAnhDTO> lstHinhAnhDTO = new ArrayList<HinhAnhDTO>();
					for (HinhAnhDTO item : lstHinhAnhDTOs.get(m)) {
						lstHinhAnhDTO.add(item);
					}
					hinhAnhMauSacDTO.setHinhAnhDTOs(lstHinhAnhDTO);
					m++;
				}
				lstHinhAnhMauSacDTO.add(hinhAnhMauSacDTO);
			}
			dto.setIsCreatedValueImg(true);
			dto.setLstHinhAnhMauSacDTO(lstHinhAnhMauSacDTO);
			model.addAttribute("sanPhamManageDTO", dto);
			model.addAttribute("dataGen", dataGen);
		}
		return "admin/product/addProduct";
	}

	@PostMapping("deleteAllByIdsProductManage")
	public ModelAndView deleteAllByIdProductManage(ModelMap model,
			@ModelAttribute("dataSearch") SPAndSPCTSearchDto dataSearch, HttpServletRequest request,
			HttpServletResponse response, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) throws IOException {
		this.deleteAllByIds(model, request, response);
		this.showViewBeforeSearch(model, dataSearch, page, size);
		return new ModelAndView("admin/product/productManage", model);
	}

	@PostMapping("deleteAllByIdsAddProduct")
	public ModelAndView deleteAllByIdAddProduct(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		this.deleteAllByIds(model, request, response);
		return new ModelAndView("admin/product/addProduct", model);
	}

	@PostMapping("updateQuantityProductDetail")
	public ModelAndView updateQuantityProductDetail(ModelMap model,
			@ModelAttribute("sanPhamManageDTO") SanPhamManageDTO data, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String[] soLuongs = request.getParameterValues("soLuongs");
		String[] ids = request.getParameterValues("soLuongTheoIds");
		if (soLuongs != null && ids != null) {
			for (String item : soLuongs) {
				if (!isNumeric(item)) {
					model.addAttribute("messageDanger", "Số lượng phải là số");
					return new ModelAndView("admin/product/addProduct", model);
				}
			}
			for (String item : ids) {
				if (!isNumeric(item)) {
					model.addAttribute("messageDanger", "Id phải là số");
					return new ModelAndView("admin/product/addProduct", model);
				}
			}
		}
		// add key-id, value-soLuong -> map
		Map<String, String> hm = new HashMap<>();
		for (int i = 0; i < ids.length; i++) {
			hm.put(ids[i], soLuongs[i]);
		}
		Long sPID = null;
		for (Map.Entry<String, String> mapItem : hm.entrySet()) {
			Optional<SanPhamChiTiet> opt = sanPhamChiTietService.findById(Long.parseLong(mapItem.getKey()));
			if (opt.isPresent()) {
				opt.get().setSoLuong(Integer.parseInt(mapItem.getValue()));
				sPID = opt.get().getSanPham().getId();
				sanPhamChiTietService.save(opt.get());
				data.setIsEdit(true);
				model.addAttribute("messageSuccess", "Cập nhật số lượng thành công");
			} else
				model.addAttribute("messageSuccess",
						"Cập nhật số lượng thất bại sản phẩm có tên: " + opt.get().getSanPham().getTenSanPham());
		}
		List<SanPhamChiTiet> dataGen = sanPhamChiTietService.getLstSanPhamChiTietBySanPhamId(sPID);
		model.addAttribute("dataGen", dataGen);
		model.addAttribute("sanPhamManageDTO", data);
		return new ModelAndView("admin/product/addProduct", model);
	}

	@GetMapping("/productDetail/edit/{id}/{pageName}")
	public String editProductDetail(ModelMap model, @PathVariable("id") Long id,
			@PathVariable("pageName") String returnUrlPage) {
		Optional<SanPhamChiTiet> opt = sanPhamChiTietService.findById(id);
		if (opt.isPresent()) {
			SanPhamChiTietDTO dto = new SanPhamChiTietDTO();
			BeanUtils.copyProperties(opt.get(), dto);
			dto.setKichCoId(opt.get().getKichCo().getId());
			dto.setMauSacId(opt.get().getMauSac().getId());
			dto.setSanPhamId(opt.get().getSanPham().getId());
			if (returnUrlPage.equalsIgnoreCase(pageContants.addProduct)) {
				returnUrlPage = "/admin/product/edit/" + opt.get().getSanPham().getId();
			} else if (returnUrlPage.equalsIgnoreCase(pageContants.infoProduct)) {
				returnUrlPage = "/admin/product/info/" + opt.get().getSanPham().getId();
			}
			model.addAttribute("sanPhamChiTietDTO", dto);
			model.addAttribute("returnUrlPage", returnUrlPage);
		}
		return "admin/product/editProductDetail";
	}

	@GetMapping("addImageProductDetail")
	public String addImageProductDetail(ModelMap model, @ModelAttribute("sanPhamManageDTO") SanPhamManageDTO data,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		String[] spctIds = request.getParameterValues("mauSacProductIds");
		long idSanPham = 0;
		data.setIsEdit(true);
		if (spctIds != null) {
			for (String id : spctIds) {
				if (isNumeric(id)) {
					idSanPham = Long.parseLong(id);
					break;
				}
			}
			List<MauSac> lstMauSacAddImg = mauSacService.getAllMauSacExistBySPId(idSanPham);
			List<HinhAnhMauSacDTO> lstHinhAnhMauSacDTO = new ArrayList<HinhAnhMauSacDTO>();
			for (MauSac mauSac : lstMauSacAddImg) {
				List<Long> spctIdsAddImg = new ArrayList<Long>();
				mauSac.getSanPhamChiTiets().stream().forEach(i -> {
					spctIdsAddImg.add(i.getId());
				});
				HinhAnhMauSacDTO dto = new HinhAnhMauSacDTO();
				dto.setMauSacAddImagesId(mauSac.getId());
				dto.setTenMauSacAddImg(mauSac.getTenMauSac());
				lstHinhAnhMauSacDTO.add(dto);
			}
			data.setLstHinhAnhMauSacDTO(lstHinhAnhMauSacDTO);
		}
		data.setIsEdit(false);
		data.setIsCreatedValueImg(false);
		model.addAttribute("sanPhamManageDTO", data);
		List<SanPhamChiTiet> dataGen = sanPhamChiTietService.getLstSanPhamChiTietBySanPhamId(idSanPham);
		model.addAttribute("dataGen", dataGen);
		return "admin/product/addProduct";
	}

	@PostMapping("saveImageProductDetail")
	public ModelAndView saveImageProductDetail(ModelMap model,
			@ModelAttribute("sanPhamManageDTO") SanPhamManageDTO sanPhamManageDTO) {
		for (HinhAnhMauSacDTO item : sanPhamManageDTO.getLstHinhAnhMauSacDTO()) {
			for (MultipartFile imgFile : item.getImgFiles()) {
				if (imgFile.isEmpty()) {
					model.addAttribute("messageDanger", "Hình ảnh cho sản phẩm không được để trống");
					return new ModelAndView("admin/product/addProduct", model);
				}
			}
		}
		sanPhamManageDTO.getLstHinhAnhMauSacDTO().stream().forEach(i -> {
			Optional<MauSac> optMS = mauSacService.findById(i.getMauSacAddImagesId());
			sanPhamManageDTO.setIsEdit(true);
			Optional<SanPham> optSP = sanPhamService.findById(sanPhamManageDTO.getSanPhamId());
			i.getImgFiles().stream().forEach(img -> {
				UUID uuid = UUID.randomUUID();
				String uuString = uuid.toString();
				HinhAnh hinhAnh = new HinhAnh();
				hinhAnh.setTenAnh(storageService.getStoredFileName(img, uuString));
				if (optSP.isPresent()) {
					hinhAnh.setSanPham(optSP.get());
				}
				storageService.store(img, hinhAnh.getTenAnh());
				if (optMS.isPresent()) {
					hinhAnh.setMauSac(optMS.get());
					hinhAnh.setDaXoa(false);
					hinhAnh.setCoHienThi(true);
					hinhAnh.setLaAnhChinh(false);
					hinhAnhService.save(hinhAnh);
				}
			});
		});

		List<HinhAnh> lstHinhAnh = hinhAnhService.getLstHinhAnhMauSacBySanPhamId(sanPhamManageDTO.getSanPhamId());
		List<List<HinhAnhDTO>> lstHinhAnhDTOs = new ArrayList<>();
		int i = 0;
		int j = 0;
		int countLstHinhAnh = 0;
		do {
			List<HinhAnhDTO> HinhAnhDTOs = new ArrayList<>();
			for (j = i; j < lstHinhAnh.size(); j++) {
				if (lstHinhAnh.get(j).getMauSac().getId().equals(lstHinhAnh.get(i).getMauSac().getId())) {
					HinhAnhDTO haDto = new HinhAnhDTO();
					BeanUtils.copyProperties(lstHinhAnh.get(j), haDto);
					haDto.setCoHienThi(lstHinhAnh.get(j).getCoHienThi());
					haDto.setLaAnhChinh(lstHinhAnh.get(j).getLaAnhChinh());
					haDto.setHinhAnhid(lstHinhAnh.get(j).getId());
					HinhAnhDTOs.add(haDto);
				} else {
					i = j;
					break;
				}
			}
			countLstHinhAnh++;

			lstHinhAnhDTOs.add(HinhAnhDTOs);
			if (j == lstHinhAnh.size())
				break;
		} while (j < lstHinhAnh.size());
		int m = 0;
		List<HinhAnhMauSacDTO> lstHinhAnhMauSacDTO = new ArrayList<>();
		List<Long> lstHinhAnhDistinct = hinhAnhService.getDistinctMauSacInHinhAnhBySanPhamId(sanPhamManageDTO.getSanPhamId());
		for (Long mauSacId : lstHinhAnhDistinct) {
			HinhAnhMauSacDTO hinhAnhMauSacDTO = new HinhAnhMauSacDTO();
			Optional<MauSac> optMS = mauSacService.findById(mauSacId);
			if (optMS.isPresent()) {
				hinhAnhMauSacDTO.setTenMauSacAddImg(optMS.get().getTenMauSac());
				hinhAnhMauSacDTO.setMauSacAddImagesId(mauSacId);
			}
			if (m < countLstHinhAnh) {
				List<HinhAnhDTO> lstHinhAnhDTO = new ArrayList<HinhAnhDTO>();
				for (HinhAnhDTO item : lstHinhAnhDTOs.get(m)) {
					lstHinhAnhDTO.add(item);
				}
				hinhAnhMauSacDTO.setHinhAnhDTOs(lstHinhAnhDTO);
				m++;
			}
			lstHinhAnhMauSacDTO.add(hinhAnhMauSacDTO);
		}
		sanPhamManageDTO.setIsCreatedValueImg(true);
		sanPhamManageDTO.setLstHinhAnhMauSacDTO(lstHinhAnhMauSacDTO);
		model.addAttribute("messageSuccess", "Thêm hình ảnh cho sản phẩm thành công");
		sanPhamManageDTO.setIsCreatedValueImg(true);
		List<SanPhamChiTiet> dataGen = sanPhamChiTietService
				.getLstSanPhamChiTietBySanPhamId(sanPhamManageDTO.getSanPhamId());
		model.addAttribute("dataGen", dataGen);
		model.addAttribute("sanPhamManageDTO", sanPhamManageDTO);
		return new ModelAndView("admin/product/addProduct", model);
	}

	@GetMapping("changeCoHienThi/{imgName}/{checked}")
	@ResponseBody
	public ResponseEntity<String> changeCoHienThi(
			@PathVariable("imgName") String imgName,
			@PathVariable("checked") Boolean checked){
		Optional<HinhAnh> opt = hinhAnhService.getHinhAnhByName(imgName);
		if(opt.isPresent()) {
			opt.get().setCoHienThi(checked);
			hinhAnhService.save(opt.get());
			return new ResponseEntity<>(HttpStatus.OK);
		}else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("changeLaAnhChinh/{imgName}/{checked}")
	@ResponseBody
	public ResponseEntity<String> changeLaAnhChinh(ModelMap model,
			@PathVariable("imgName") String imgName,
			@PathVariable("checked") Boolean checked){
		Optional<HinhAnh> opt = hinhAnhService.getHinhAnhByName(imgName);
		if(opt.isPresent()) {
			Optional<HinhAnh> optHAChinh = hinhAnhService.getHinhAnhChinhBySanPhamId(opt.get().getSanPham().getId());
			if(optHAChinh.isPresent()) {
				optHAChinh.get().setLaAnhChinh(!checked);
				opt.get().setLaAnhChinh(checked);
				hinhAnhService.save(opt.get());
				hinhAnhService.save(optHAChinh.get());
				model.addAttribute("messageSuccess", "Cập nhật ảnh chính cho sản phẩm thành công");
				return new ResponseEntity<>(HttpStatus.OK);
			}else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping("saveValueImageProduct")
	public ModelAndView saveProduct(ModelMap model,
			@ModelAttribute("sanPhamManageDTO") SanPhamManageDTO sanPhamManageDTO,
			HttpServletRequest request) {
		if(!sanPhamManageDTO.getLstHinhAnhMauSacDTO().isEmpty()) {
			sanPhamManageDTO.getLstHinhAnhMauSacDTO().stream().forEach(i1 -> {
				if(!i1.getHinhAnhDTOs().isEmpty()) {
					i1.getHinhAnhDTOs().stream().forEach(i2 -> {
						Optional<HinhAnh> opt = hinhAnhService.findById(i2.getHinhAnhid());
						if (opt.isPresent()) {
//							opt.get().setCoHienThi(i2.getCoHienThi());
//							opt.get().setLaAnhChinh(i2.getLaAnhChinh());
							hinhAnhService.save(opt.get());
						}
					});
				}
			});
		}
		List<HinhAnh> lstHinhAnh = hinhAnhService.getLstHinhAnhMauSacBySanPhamId(sanPhamManageDTO.getSanPhamId());
		List<List<HinhAnhDTO>> lstHinhAnhDTOs = new ArrayList<>();
		int i = 0;
		int j = 0;
		int countLstHinhAnh = 0;
		do {
			List<HinhAnhDTO> HinhAnhDTOs = new ArrayList<>();
			for (j = i; j < lstHinhAnh.size(); j++) {
				if (lstHinhAnh.get(j).getMauSac().getId().equals(lstHinhAnh.get(i).getMauSac().getId())) {
					HinhAnhDTO haDto = new HinhAnhDTO();
					BeanUtils.copyProperties(lstHinhAnh.get(j), haDto);
					haDto.setHinhAnhid(lstHinhAnh.get(j).getId());
					haDto.setCoHienThi(lstHinhAnh.get(j).getCoHienThi());
					haDto.setLaAnhChinh(lstHinhAnh.get(j).getLaAnhChinh());
					HinhAnhDTOs.add(haDto);
				} else {
					i = j;
					break;
				}
			}
			countLstHinhAnh++;

			lstHinhAnhDTOs.add(HinhAnhDTOs);
			if (j == lstHinhAnh.size())
				break;
		} while (j < lstHinhAnh.size());
		int m = 0;
		List<HinhAnhMauSacDTO> lstHinhAnhMauSacDTO = new ArrayList<>();
		List<Long> lstHinhAnhDistinct = hinhAnhService.getDistinctMauSacInHinhAnhBySanPhamId(sanPhamManageDTO.getSanPhamId());
		for (Long mauSacId : lstHinhAnhDistinct) {
			HinhAnhMauSacDTO hinhAnhMauSacDTO = new HinhAnhMauSacDTO();
			Optional<MauSac> optMS = mauSacService.findById(mauSacId);
			if (optMS.isPresent()) {
				hinhAnhMauSacDTO.setTenMauSacAddImg(optMS.get().getTenMauSac());
				hinhAnhMauSacDTO.setMauSacAddImagesId(mauSacId);
			}
			if (m < countLstHinhAnh) {
//				Resource[] imgfiles = applicationContext.getre;
				List<HinhAnhDTO> lstHinhAnhDTO = new ArrayList<HinhAnhDTO>();
				for (HinhAnhDTO item : lstHinhAnhDTOs.get(m)) {
					lstHinhAnhDTO.add(item);
				}
				hinhAnhMauSacDTO.setHinhAnhDTOs(lstHinhAnhDTO);
				m++;
			}
			lstHinhAnhMauSacDTO.add(hinhAnhMauSacDTO);
		}
		sanPhamManageDTO.setIsCreatedValueImg(true);
		sanPhamManageDTO.setLstHinhAnhMauSacDTO(lstHinhAnhMauSacDTO);
		List<SanPhamChiTiet> dataGen = sanPhamChiTietService.getLstSanPhamChiTietBySanPhamId(sanPhamManageDTO.getSanPhamId());
		model.addAttribute("sanPhamManageDTO", sanPhamManageDTO);
		model.addAttribute("dataGen", dataGen);
		return new ModelAndView("admin/product/addProduct");
	}

	@PostMapping("saveOptionValue")
	public String saveOptionValue(ModelMap model, @ModelAttribute("sanPhamManageDTO") SanPhamManageDTO sanPhamManageDTO,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		String[] value = request.getParameterValues("thuocTinhInput");
		String[] option = request.getParameterValues("fieldthuocTinhInput");
		if (value != null && option != null) {
			if (!option[0].isEmpty() && !value[0].isEmpty()) {
				if (option[0].equalsIgnoreCase(OptionContants.chatLieu)) {
					ChatLieu entity = new ChatLieu();
					entity.setTenChatLieu(value[0].toString());
					chatLieuService.save(entity);
					List<ChatLieu> loadData = chatLieuService.selectAllChatLieuExist();
					model.addAttribute("lstChatLieu", loadData);
					model.addAttribute("messageSuccess", "Thêm mới chất liệu thành công");
				} else if (option[0].equalsIgnoreCase(OptionContants.loaiSanPham)) {
					LoaiSanPham entity = new LoaiSanPham();
					entity.setTenLoaiSanPham(value[0].toString());
					loaiSanPhamService.save(entity);
					List<LoaiSanPham> loadData = loaiSanPhamService.selectAllLoaiHangExist();
					model.addAttribute("lstLoaiSanPham", loadData);
					model.addAttribute("messageSuccess", "Thêm mới loại sản phẩm thành công");
				} else if (option[0].equalsIgnoreCase(OptionContants.kichCo)) {
					KichCo entity = new KichCo();
					entity.setTenKichCo(value[0].toString());
					kichCoService.save(entity);
					List<KichCo> loadData = kichCoService.selectAllKichCoExist();
					model.addAttribute("lstKichCo", loadData);
					model.addAttribute("messageSuccess", "Thêm mới kích cỡ thành công");
				} else if (option[0].equalsIgnoreCase(OptionContants.kieuDang)) {
					KieuDang entity = new KieuDang();
					entity.setTenKieuDang(value[0].toString());
					kieuDangService.save(entity);
					List<KieuDang> loadData = kieuDangService.selectAllKieuDangExist();
					model.addAttribute("lstKieuDang", loadData);
					model.addAttribute("messageSuccess", "Thêm mới kiểu dáng thành công");
				} else if (option[0].equalsIgnoreCase(OptionContants.phongCach)) {
					PhongCach entity = new PhongCach();
					entity.setTenPhongCach(value[0].toString());
					phongCachService.save(entity);
					List<PhongCach> loadData = phongCachService.selectAllPhongCachExist();
					model.addAttribute("lstPhongCach", loadData);
					model.addAttribute("messageSuccess", "Thêm mới phong cách thành công");
				} else if (option[0].equalsIgnoreCase(OptionContants.mauSac)) {
					String[] maMauSac = request.getParameterValues("maMauSac");
					if (!maMauSac[0].isEmpty()) {
						MauSac entity = new MauSac();
						entity.setTenMauSac(value[0].toString());
						entity.setMaMauSac(maMauSac[0].toString());
						mauSacService.save(entity);
						List<MauSac> loadData = mauSacService.selectAllMauSacExist();
						model.addAttribute("lstMauSac", loadData);
						model.addAttribute("messageSuccess", "Thêm mới màu sắc thành công");
					}
				}
			} else {
				model.addAttribute("messageDanger", "Tên giá trị thuộc tính không được để trống");
			}
		} else {
			model.addAttribute("messageDanger", "Lưu giá trị thuộc tính sản phẩm thất bại");
		}
		model.addAttribute("sanPhamManageDTO", sanPhamManageDTO);
		return "admin/product/addProduct";
	}

	public void deleteAllByIds(ModelMap model, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String[] ids = request.getParameterValues("productIds");
		if (ids != null) {
			for (String item : ids) {
				Optional<SanPhamChiTiet> opt = sanPhamChiTietService.findById(Long.parseLong(item));
				if (opt.isPresent()) {
//						if(!StringUtils.isEmpty(opt.get().getImage())) {
//							storageService.delete(opt.get().getImage());
//						}
					sanPhamChiTietService.delete(opt.get());
				}
			}
		} else
			model.addAttribute("messageDanger", "Bạn chưa chọn ô checkbox nào");
	}

	public void showViewBeforeSearch(ModelMap model, SPAndSPCTSearchDto dataSearch, Optional<Integer> page,
			Optional<Integer> size) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(10);

		Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
		Page<SanPham> resultPage = null;
		Optional<SPAndSPCTSearchDto> optDataSearch = Optional.of(dataSearch);
		if (optDataSearch.isPresent()) {
			resultPage = sanPhamService.searchProductExist(dataSearch, pageable);
			model.addAttribute("dataSearch", dataSearch);
		}

		int totalPages = resultPage.getTotalPages();
		if (totalPages > 0) {
			int start = Math.max(1, currentPage - 2);
			int end = Math.min(currentPage + 2, totalPages);
			if (totalPages > 5) {
				if (end == totalPages) {
					start = end - 5;
				} else if (start == 1) {
					end = start + 5;
				}
			}
			List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		model.addAttribute("sanPhamPage", resultPage);
	}

	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?");
	}

}
