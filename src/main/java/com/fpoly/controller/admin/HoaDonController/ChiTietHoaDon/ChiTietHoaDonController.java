package com.fpoly.controller.admin.HoaDonController.ChiTietHoaDon;

import com.fpoly.entity.GiaoDich;
import com.fpoly.entity.HoaDon;
import com.fpoly.repository.GiaoDichRepository;
import com.fpoly.repository.HoaDonRepository;
import com.fpoly.service.GiaoDichService;
import com.fpoly.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ChiTietHoaDonController {
    @Autowired
    HoaDonService hoaDonService;
    @Autowired
    HoaDonRepository hoaDonRepoditory;
    @Autowired
    GiaoDichService giaoDichService;

    @Autowired
    GiaoDichRepository giaoDichRepository;

    //CHỜ XÁC NHẬN
    @RequestMapping("ChiTietHoaDon/ChoXacNhan/hoa-don-id={id}")
    public String ChoXacNhan(@PathVariable("id") Long id, Model model) {
        HoaDon hoaDon = hoaDonRepoditory.findById(id).get();
        List<GiaoDich> timeLineChoXacNhan = giaoDichRepository.findByTrangThaiIdAndHoaDonId(1, id);
        model.addAttribute("timeLineChoXacNhan", timeLineChoXacNhan);
        model.addAttribute("hoaDon", hoaDon);
        return "admin/hoadon/ChiTiethoaDon/CTChoXacNhan";
    }

    //CHỜ GIAO HÀNG
    @RequestMapping("ChiTietHoaDon/ChoGiaoHang/hoa-don-id={id}")
    public String ChoGiaoHang(@PathVariable("id") Long id, Model model) {
        HoaDon hoaDon = hoaDonRepoditory.findById(id).get();
        List<GiaoDich> timeLineChoXacNhan = giaoDichRepository.findByTrangThaiIdAndHoaDonId(1, id);
        List<GiaoDich> timeLineChoGiaoHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(2, id);
        model.addAttribute("timeLineChoXacNhan", timeLineChoXacNhan);
        model.addAttribute("timeLineChoGiaoHang", timeLineChoGiaoHang);
        model.addAttribute("hoaDon", hoaDon);
        return "admin/hoadon/ChiTiethoaDon/CTChoGiaohang";
    }

    //ĐANG GIAO HÀNG
    @RequestMapping("ChiTietHoaDon/DangGiaoHang/hoa-don-id={id}")
    public String DangGiaoHang(@PathVariable("id") Long id, Model model) {
        HoaDon hoaDon = hoaDonRepoditory.findById(id).get();
        List<GiaoDich> timeLineChoXacNhan = giaoDichRepository.findByTrangThaiIdAndHoaDonId(1, id);
        List<GiaoDich> timeLineChoGiaoHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(2, id);
        List<GiaoDich> timeLineDangGiaoHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(3, id);
        model.addAttribute("timeLineChoXacNhan", timeLineChoXacNhan);
        model.addAttribute("timeLineChoGiaoHang", timeLineChoGiaoHang);
        model.addAttribute("timeLineDangGiaoHang", timeLineDangGiaoHang);
        model.addAttribute("hoaDon", hoaDon);
        return "admin/hoadon/ChiTiethoaDon/CTDangGiaoHang";
    }

    //ĐÃ GIAO HÀNG
    @RequestMapping("ChiTietHoaDon/DaGiaoHang/hoa-don-id={id}")
    public String DaGiaoHang(@PathVariable("id") Long id, Model model) {
        HoaDon hoaDon = hoaDonRepoditory.findById(id).get();
        List<GiaoDich> timeLineChoXacNhan = giaoDichRepository.findByTrangThaiIdAndHoaDonId(1, id);
        List<GiaoDich> timeLineChoGiaoHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(2, id);
        List<GiaoDich> timeLineDangGiaoHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(3, id);
        List<GiaoDich> timeLineDaGiaoHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(4, id);
        model.addAttribute("timeLineChoXacNhan", timeLineChoXacNhan);
        model.addAttribute("timeLineChoGiaoHang", timeLineChoGiaoHang);
        model.addAttribute("timeLineDangGiaoHang", timeLineDangGiaoHang);
        model.addAttribute("timeLineDaGiaoHang", timeLineDaGiaoHang);
        model.addAttribute("hoaDon", hoaDon);
        return "admin/hoadon/ChiTiethoaDon/CTDaGiao";
    }
    //ĐÃ HỦY
    @RequestMapping("ChiTietHoaDon/DaHuy/hoa-don-id={id}")
    public String DaHuy(@PathVariable("id") Long id, Model model) {
        HoaDon hoaDon = hoaDonRepoditory.findById(id).get();
        List<GiaoDich> timeLineChoXacNhan = giaoDichRepository.findByTrangThaiIdAndHoaDonId(1, id);
        List<GiaoDich> timeLineHuyDonHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(5, id);
        model.addAttribute("timeLineChoXacNhan", timeLineChoXacNhan);
        model.addAttribute("timeLineHuyDonHang", timeLineHuyDonHang);
        model.addAttribute("hoaDon", hoaDon);
        return "admin/hoadon/ChiTiethoaDon/CTDaHuy";
    }
    //TẤT CẢ ĐƠN
    @RequestMapping("ChiTietHoaDon/TatCaDon/hoa-don-id={id}")
    public String TatCaDon(@PathVariable("id") Long id, Model model) {
        HoaDon hoaDon = hoaDonRepoditory.findById(id).get();
        List<GiaoDich> timeLineChoXacNhan = giaoDichRepository.findByTrangThaiIdAndHoaDonId(1, id);
        List<GiaoDich> timeLineChoGiaoHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(2, id);
        List<GiaoDich> timeLineDangGiaoHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(3, id);
        List<GiaoDich> timeLineDaGiaoHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(4, id);
        List<GiaoDich> timeLineHuyDonHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(5, id);
        model.addAttribute("timeLineChoXacNhan", timeLineChoXacNhan);
        model.addAttribute("timeLineChoGiaoHang", timeLineChoGiaoHang);
        model.addAttribute("timeLineDangGiaoHang", timeLineDangGiaoHang);
        model.addAttribute("timeLineDaGiaoHang", timeLineDaGiaoHang);
        model.addAttribute("timeLineHuyDonHang", timeLineHuyDonHang);
        model.addAttribute("hoaDon", hoaDon);
        return "admin/hoadon/ChiTiethoaDon/CTTatCaDon";
    }

}
