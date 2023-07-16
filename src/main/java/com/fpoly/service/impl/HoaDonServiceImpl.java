package com.fpoly.service.impl;

import com.fpoly.entity.HoaDon;
import com.fpoly.repository.HoaDonRepoditory2;
import com.fpoly.repository.HoaDonRepository;
import com.fpoly.service.HoaDonService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HoaDonServiceImpl implements HoaDonService {
    private final HoaDonRepository hoaDonRepository;

    private HoaDonRepoditory2 hoaDonRepository2;
    private  JdbcTemplate jdbcTemplate;

    public HoaDonServiceImpl(HoaDonRepository hoaDonRepository) {
        this.hoaDonRepository = hoaDonRepository;
    }

    @Override
    public List<HoaDon> getAll() {
        return (List<HoaDon>)hoaDonRepository.findAll();
    }

    @Override
    public Page<HoaDon> getByStatus(int trangThai, Pageable pageable){
        return hoaDonRepository2.findByTrangThaiHoaDonListTrangThai(trangThai, pageable);
    }
    @Override
    public Page<HoaDon> getAll(Pageable pageable) {
        return hoaDonRepository2.findAll(pageable);
    }

    @Override
    public List<HoaDon> searchByDate(Date searchDate) {
        return hoaDonRepository.findByNgayTao(searchDate);
    }
}
