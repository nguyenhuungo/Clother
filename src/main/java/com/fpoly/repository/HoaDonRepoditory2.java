package com.fpoly.repository;

import com.fpoly.entity.HoaDon;
import com.fpoly.entity.HoaDonChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoaDonRepoditory2 extends PagingAndSortingRepository<HoaDon, Long> {
    Page<HoaDon> findAll(Pageable pageable);

    @Query(value = "SELECT * FROM hoa_don WHERE trang_thai_id = ?1",
            countQuery = "SELECT COUNT(*) FROM hoa_don WHERE trang_thai_id = ?1",
            nativeQuery = true)
    Page<HoaDon> findByTrangThaiHoaDonListTrangThai(int trangThai, Pageable pageable);
}
