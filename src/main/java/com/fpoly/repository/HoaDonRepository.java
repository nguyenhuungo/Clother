package com.fpoly.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fpoly.entity.HoaDon;

import java.util.Date;
import java.util.List;

@Repository
public interface HoaDonRepository extends CrudRepository<HoaDon, Long> {
    @Query(value = "select * from hoa_don where trang_thai_id = ?1", nativeQuery = true)
    List<HoaDon> findByTrangThaiHoaDonListTrangThai(int trangThai);
    @Query(value = "SELECT * FROM hoa_don WHERE loai_hoa_don = :loai", nativeQuery = true)
    List<HoaDon> finHDByLoaiHD(@Param("loai") Integer loai);

    List<HoaDon> findByNgayTao(Date ngayTao);
}


