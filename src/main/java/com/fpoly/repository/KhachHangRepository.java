package com.fpoly.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fpoly.entity.KhachHang;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang,Long> {
	
	
	
	@Query(value="SELECT k FROM KhachHang k WHERE k.trangThai=?1 ")
	Page<KhachHang> findAllByTrangThaiCoPhanTrang(int trangThai,Pageable pageable);
	
	@Query(value="SELECT k FROM KhachHang k WHERE k.trangThai =:trangThai "
			+ "AND k.soDienThoai LIKE %:input% "
			+ "OR  k.email LIKE %:input% "
			+ "OR k.hoTen LIKE %:input% ")
	Page<KhachHang> findAllByTrangThaiVaSoDienThoaiCoPhanTrang(@Param("trangThai")int trangThai
			,@Param("input") String input 
			, Pageable pageable);
	
	
	@Query(value="SELECT k FROM KhachHang k WHERE k.email=?1")
	KhachHang findByEmail(String  email);
	
	@Modifying
	@Query(value="UPDATE KhachHang k SET k.trangThai = 1 WHERE k.id=?1")
	void capNhatTrangThaiThanhHoatDongTheoMa(long id);
	
	
	@Modifying
	@Query(value="UPDATE KhachHang k SET k.trangThai = 0 WHERE k.id=?1")
	void capNhatTrangThaiThanhKhongHoatDongTheoMa(long id);
	
	
	@Query(value="SELECT k FROM KhachHang k WHERE k.soDienThoai LIKE %:input% "
			+ "OR  k.email LIKE %:input% "
			+ "OR k.hoTen LIKE %:input% ")
	Page<KhachHang> findAllBySoDienThoaiCoPhanTrang(@Param("input")String input , Pageable pageable);

	@Query(value="SELECT count(k) FROM KhachHang k WHERE k.trangThai=?1")
	int countByTrangThai(Integer trangThai);

	@Query(value="SELECT count(k) FROM KhachHang k WHERE"
			+ " k.soDienThoai LIKE %:input% "
			+ "OR  k.email LIKE %:input% "
			+ "OR k.hoTen LIKE %:input% "
			)
	int countByInput(@Param("input")String input);

	@Query(value="SELECT count(k) FROM KhachHang k WHERE k.trangThai =:trangThai AND "
			+ "k.soDienThoai LIKE %:input%   "
			+ "OR  k.email LIKE %:input% "
			+ "OR k.hoTen LIKE %:input% "
			)
	int countByInputVaTrangThai(@Param("input")String input, @Param("trangThai")Integer trangThai);
}
