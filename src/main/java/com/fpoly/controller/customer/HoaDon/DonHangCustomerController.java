package com.fpoly.controller.customer.HoaDon;

import com.fpoly.entity.HoaDon;
import com.fpoly.repository.HoaDonRepository;
import com.fpoly.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class DonHangCustomerController {
    @Autowired
    HoaDonService hoaDonService;

    @Autowired
    HoaDonRepository hoaDonRepository;
    @RequestMapping("customer/DonHang")
    public String getAllHoaDonStatus(Model model) {
        List<HoaDon> getAllHoaDon = hoaDonService.getAll();
//        System.out.println(getAllHoaDon);
        List<HoaDon> hoaDonList0 = hoaDonRepository.findByTrangThaiHoaDonListTrangThai(1);
        List<HoaDon> hoaDonList1 = hoaDonRepository.findByTrangThaiHoaDonListTrangThai(2);
        List<HoaDon> hoaDonList2 = hoaDonRepository.findByTrangThaiHoaDonListTrangThai(3);
        List<HoaDon> hoaDonList3 = hoaDonRepository.findByTrangThaiHoaDonListTrangThai(4);
        List<HoaDon> hoaDonList4 = hoaDonRepository.findByTrangThaiHoaDonListTrangThai(5);
        model.addAttribute("getAllHoaDon", getAllHoaDon);
        model.addAttribute("hoaDonList0", hoaDonList0);
        model.addAttribute("hoaDonList1", hoaDonList1);
        model.addAttribute("hoaDonList2", hoaDonList2);
        model.addAttribute("hoaDonList3", hoaDonList3);
        model.addAttribute("hoaDonList4", hoaDonList4);
        return "customer/HoaDon/ListHoaDon";
    }
}
