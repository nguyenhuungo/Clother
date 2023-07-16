package com.fpoly.controller.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fpoly.entity.HinhAnh;
import com.fpoly.entity.SanPham;
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

@Controller(value="HomeControllerOfCustomer")
@RequestMapping("/customer/home")
public class HomeController {
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
	
	@GetMapping("/images/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename){
		Resource file = storageService.loadAsResource(filename);
		
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}
	
	@RequestMapping("")
	public String home(ModelMap model) {
		List<HinhAnh> lstHinhAnh = hinhAnhService.getHinhAnhChinhExist();
		int i=0;
		int j=0;
		List<List<HinhAnh>> resultLst = new ArrayList<>();
		do {
			List<HinhAnh> lstTenHinhAnh = new ArrayList<>();
			for (j = i; j < lstHinhAnh.size()-1; j++) {
				if(lstHinhAnh.get(j).getSanPham().getId().equals(lstHinhAnh.get(i).getSanPham().getId())) {
					lstTenHinhAnh.add(lstHinhAnh.get(j));
				}else{
					i=j;
					break;
				}
			}

			resultLst.add(lstTenHinhAnh);
			if(j == lstHinhAnh.size()) break;
		} while (j< lstHinhAnh.size()-1);
		
		model.addAttribute("resultLst", resultLst);
		return "/customer/home/home";
	}
}
