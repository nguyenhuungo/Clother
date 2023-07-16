package com.fpoly.dto;

//import com.fpoly.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NguoiDungDTO extends BaseDTO<NguoiDungDTO> {
	
	private String tenDangNhap;
	
	private String matKhau;
	
	private String email;
	
	private String tenNguoiDung;

	private String soDienThoai;
	
//	private Role role;

	private int trangThai;

	private Boolean daXoa;
	
	
}
