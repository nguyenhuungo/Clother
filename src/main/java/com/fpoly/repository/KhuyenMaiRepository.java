package com.fpoly.repository;

import com.fpoly.entity.KhuyenMai;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface KhuyenMaiRepository extends JpaRepository<KhuyenMai,Long> {
    @Query("SELECT p FROM KhuyenMai p WHERE " +
            "(:keyword IS NULL OR :keyword = '' OR p.tenKhuyenMai LIKE CONCAT('%', :keyword, '%')) " +
            "AND (:status = 'ALL' OR (:status = 'ON' AND p.trangThai = true) OR (:status = 'off' AND p.trangThai = false)) " +
            "AND p.phanTramGiam <= :end AND p.phanTramGiam > :start " +
            "AND (:startDate IS NULL AND :endDate IS NULL OR (p.ngayBatDau <= :startDate AND p.ngayKetThuc >= :endDate)) " +
            "AND p.xoa = false")
    Page<KhuyenMai> findVoucher(String keyword, String status, Integer start, Integer end, Date startDate, Date endDate, Pageable pageable);

    @Modifying
    @Query("update  KhuyenMai v set v.trangThai = :status where v.id in :ids")
    void updateStatusByDate(List<Long> ids, boolean status);
}
