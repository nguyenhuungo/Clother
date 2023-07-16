package com.fpoly.service;

import com.fpoly.entity.HoaDonChiTiet;
import com.fpoly.repository.HoaDonChiTietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HoaDonChiTietService {
    private final HoaDonChiTietRepository hoaDonChiTietRepository;

    @Autowired
    public HoaDonChiTietService(HoaDonChiTietRepository hoaDonChiTietRepository) {
        this.hoaDonChiTietRepository = hoaDonChiTietRepository;
    }

    public List<HoaDonChiTiet> findByHoaDonId(Long hoaDonId) {
        return hoaDonChiTietRepository.findByHoaDonId(hoaDonId);
    }
}
