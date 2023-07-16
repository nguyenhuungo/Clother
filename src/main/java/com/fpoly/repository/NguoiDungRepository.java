package com.fpoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fpoly.entity.NguoiDung;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface NguoiDungRepository extends JpaRepository<NguoiDung, Long> {
    @Query(value = "select Max(id) from nguoi_dung", nativeQuery = true)
    Integer getMaxId();

    @Query(value = "select * from nguoi_dung where da_xoa = false", nativeQuery = true)
    List<NguoiDung> GetAll();

    @Modifying
    @Query(value = "UPDATE nguoi_dung SET da_xoa = true WHERE id = :id", nativeQuery = true)
    void xoaNguoiDung(@Param("id") Long id);

    @Query(value = "select * from nguoi_dung where trang_thai = ?1;", nativeQuery = true)
    List<NguoiDung>findTrangThai(int trangThai);
    NguoiDung findByEmail(String email);
    NguoiDung findBysoDienThoai(String sDT);

    
    @Modifying
	@Query(value="UPDATE NguoiDung n SET n.trangThai = 1 WHERE n.id=?1")
	void capNhatTrangThaiThanhHoatDongTheoMa(Long id);

    @Modifying
	@Query(value="UPDATE NguoiDung n SET n.trangThai = 2 WHERE n.id=?1")
	void capNhatTrangThaiThanhKhongHoatDongTheoMa(Long id);


}
