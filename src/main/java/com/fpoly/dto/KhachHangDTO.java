package com.fpoly.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KhachHangDTO extends BaseDTO<KhachHangDTO> {
		
	
	    private String email;

	    private String matKhau;

	    private String hoTen;

	    private Integer soLanMua;
	    
	    private String soDienThoai;

	    private Integer trangThai  = 2;
	    
	    private int page ;
	    
	    private int limit ;
	    
	    private int totalPages; 
	    
	    private int totalItems;
	    
	    private String input = "" ;
	    
	    @NotBlank(message="Bạn chưa chon file !")
	    private String pathExcel ;
	    
	    private List<KhachHangDTO> listKhachHangDTO = new ArrayList<KhachHangDTO>();
	    
		private List<DiaChiDTO> listDiaChi = new ArrayList<DiaChiDTO>();
}
