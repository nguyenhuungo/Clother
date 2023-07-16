package com.fpoly.controller.admin.HoaDonController.TrangThaiHoaDon;

import com.fpoly.entity.HoaDon;
import com.fpoly.repository.GiaoDichRepository;
import com.fpoly.repository.HoaDonRepoditory2;
import com.fpoly.repository.HoaDonRepository;
import com.fpoly.service.GiaoDichService;
import com.fpoly.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
public class TatCaDonController {
    @Autowired
    HoaDonRepository hoaDonRepository;

    @Autowired
    HoaDonRepoditory2 hoaDonRepository2;

    @Autowired
    GiaoDichRepository giaoDichRepository;

    @Autowired
    GiaoDichService giaoDichService;

    @Autowired
    HoaDonService hoaDonService;

    @RequestMapping("/admin/DonHang/TatCaDon")
    public String getAllHoaDonStatus(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<HoaDon> getAllHoaDon = hoaDonRepository2.findAll(pageable);

        model.addAttribute("getAllHoaDon", getAllHoaDon.getContent());
        model.addAttribute("totalPages", getAllHoaDon.getTotalPages());

        return "admin/hoadon/TrangThaiHoaDon/TatcaDon";
    }

    @RequestMapping("/search")
    public String searchHoaDonByDate(@RequestParam("searchDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate searchDate, Model model) {
        Date searchDateUtil = java.sql.Date.valueOf(searchDate);
        List<HoaDon> hoaDonList = hoaDonService.searchByDate(searchDateUtil);

        model.addAttribute("getAllHoaDon", hoaDonList);

        return "admin/hoadon/TrangThaiHoaDon/TatcaDon";
    }

}
