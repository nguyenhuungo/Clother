package com.fpoly.service;
import com.fpoly.entity.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public interface HoaDonService{
    List<HoaDon> getAll();

    Page<HoaDon> getByStatus(int trangThai, Pageable pageable);

    Page<HoaDon> getAll(Pageable pageable);

    List<HoaDon> searchByDate(Date searchDate);

}
