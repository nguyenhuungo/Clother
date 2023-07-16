package com.fpoly.RestController;

import com.fpoly.entity.HoaDon;
import com.fpoly.repository.HoaDonRepository;
import com.fpoly.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/DonHang")
public class HoaDonRestController {
    @Autowired
    HoaDonRepository hoaDonRepository;
    @Autowired
    HoaDonService hoaDonService;

//    @GetMapping(value = "/rest/admin/order/status/{status}")
//    public List<HoaDon> findByStatus(@PathVariable("status") Integer trangThai) {
//        List<HoaDon> sttHoaDon = hoaDonRepository.findByStatus0(trangThai);
//        return sttHoaDon;
//    }
}
