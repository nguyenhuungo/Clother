package com.fpoly.controller.admin.HoaDonController.TrangThaiHoaDon;

import com.fpoly.entity.GiaoDich;
import com.fpoly.entity.HoaDon;
import com.fpoly.entity.TrangThai;
import com.fpoly.repository.GiaoDichRepository;
import com.fpoly.repository.HoaDonRepoditory2;
import com.fpoly.repository.HoaDonRepository;
import com.fpoly.service.GiaoDichService;
import com.fpoly.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class ChoXacNhanController {
    @Autowired
    HoaDonRepository hoaDonRepository;

    @Autowired
    HoaDonRepoditory2 hoaDonRepoditory2;

    @Autowired
    GiaoDichRepository giaoDichRepository;

    @Autowired
    GiaoDichService giaoDichService;

    @Autowired
    HoaDonService hoaDonService;

    @RequestMapping("admin/DonHang/ChoXacNhanDonHang")
    public String getHoaDonChoXacNhan(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<HoaDon> choXacNhan = hoaDonRepoditory2.findByTrangThaiHoaDonListTrangThai(1, pageable);

        model.addAttribute("choXacNhan", choXacNhan.getContent());
        model.addAttribute("pageChoXacNhan", choXacNhan.getTotalPages());

        return "admin/hoadon/TrangThaiHoaDon/ChoXacNhan";
    }

    @RequestMapping("/updateXacNhan/{id}")
    public ResponseEntity<String> updateXacNhan(@PathVariable("id") Long hoaDonId) {
        Optional<HoaDon> optionalHoaDon = hoaDonRepository.findById(hoaDonId);
        if (optionalHoaDon.isPresent()) {
            HoaDon hoaDon = optionalHoaDon.get();
            TrangThai tt = new TrangThai();
            tt.setId(2L);
            hoaDon.setTrangThai(tt);
            hoaDon.setNgayCapNhat(new Date());
            hoaDon.setNguoiCapNhat("hduong"); // Cập nhật người cập nhật (thay "hduong" bằng giá trị tương ứng)
            hoaDonRepository.save(hoaDon);
            String message = "Xác nhận thành công";
            GiaoDich gd = new GiaoDich();
            gd.setHoaDon(hoaDon);
            gd.setNguoiDung(hoaDon.getNguoiDung().getId());
            gd.setNgayCapNhat(new Date());
            gd.setNgayTao(new Date());
            gd.setNguoiCapNhat("ABC");
            gd.setNguoiTao("ABC");
            gd.setTrangThai(tt);
//            System.out.println(gd);
            giaoDichRepository.save(gd);
            return ResponseEntity.ok(message);
        } else {
            String errorMessage = "Không tìm thấy hóa đơn";
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping("/updateHuyDon/{id}")
    public ResponseEntity<String> updateHuyDon(@PathVariable("id") Long hoaDonId) {
        Optional<HoaDon> optionalHoaDon = hoaDonRepository.findById(hoaDonId);
        if (optionalHoaDon.isPresent()) {
            HoaDon hoaDon = optionalHoaDon.get();
            TrangThai tt = new TrangThai();
            tt.setId(5L);
            hoaDon.setTrangThai(tt);
            hoaDon.setNgayCapNhat(new Date());
            hoaDon.setNguoiCapNhat("hduong"); // Cập nhật người cập nhật (thay "hduong" bằng giá trị tương ứng)
            hoaDonRepository.save(hoaDon);
            GiaoDich gd = new GiaoDich();
            gd.setHoaDon(hoaDon);
            gd.setNguoiDung(hoaDon.getNguoiDung().getId());
            gd.setNgayCapNhat(new Date());
            gd.setNgayTao(new Date());
            gd.setNguoiCapNhat("ABC");
            gd.setNguoiTao("ABC");
            gd.setTrangThai(tt);
//            System.out.println(gd);
            giaoDichRepository.save(gd);
            String message = "Hủy đơn thành công";
            return ResponseEntity.ok(message);

        } else {
            String errorMessage = "Không tìm thấy hóa đơn";
            return ResponseEntity.notFound().build();
        }
    }

    //XÁC NHẬN TẤT CẢ
    @RequestMapping("/updateXacNhanAll")
    public ResponseEntity<String> updateXacNhanAll() {
        // Lấy danh sách tất cả hóa đơn chưa được xác nhận
        List<HoaDon> hoaDonList = hoaDonRepository.findByTrangThaiHoaDonListTrangThai(1);

        // Kiểm tra xem danh sách hóa đơn có rỗng hay không
        if (!hoaDonList.isEmpty()) {
            // Duyệt qua từng hóa đơn và cập nhật trạng thái
            for (HoaDon hoaDon : hoaDonList) {
                TrangThai tt = new TrangThai();
                tt.setId(2L);
                hoaDon.setTrangThai(tt);
                hoaDon.setNgayCapNhat(new Date());
                hoaDon.setNguoiCapNhat("hduong"); // Cập nhật người cập nhật (thay "hduong" bằng giá trị tương ứng)
                hoaDonRepository.save(hoaDon);
                GiaoDich gd = new GiaoDich();
                gd.setHoaDon(hoaDon);
                gd.setNguoiDung(hoaDon.getNguoiDung().getId());
                gd.setNgayCapNhat(new Date());
                gd.setNgayTao(new Date());
                gd.setNguoiCapNhat("ABC");
                gd.setNguoiTao("ABC");
                gd.setTrangThai(tt);
//                System.out.println(hoaDon);
                giaoDichRepository.save(gd);
//                return ResponseEntity.ok(message);
            }
            String message = "Xác nhận tất cả thành công";
            return ResponseEntity.ok(message);
        } else {
            String errorMessage = "Không tìm thấy hóa đơn chưa xác nhận";
            return ResponseEntity.notFound().build();
        }
    }

    //HỦY TẤT CẢ
    @RequestMapping("/updateHuyAll")
    public ResponseEntity<String> updateHuyAll() {
        // Lấy danh sách tất cả hóa đơn chưa được xác nhận
        List<HoaDon> hoaDonList = hoaDonRepository.findByTrangThaiHoaDonListTrangThai(1);

        // Kiểm tra xem danh sách hóa đơn có rỗng hay không
        if (!hoaDonList.isEmpty()) {
            // Duyệt qua từng hóa đơn và cập nhật trạng thái
            for (HoaDon hoaDon : hoaDonList) {
                TrangThai tt = new TrangThai();
                tt.setId(5L);
                hoaDon.setTrangThai(tt);
                hoaDon.setNgayCapNhat(new Date());
                hoaDon.setNguoiCapNhat("hduong"); // Cập nhật người cập nhật (thay "hduong" bằng giá trị tương ứng)
                hoaDonRepository.save(hoaDon);
                GiaoDich gd = new GiaoDich();
                gd.setHoaDon(hoaDon);
                gd.setNguoiDung(hoaDon.getNguoiDung().getId());
                gd.setNgayCapNhat(new Date());
                gd.setNgayTao(new Date());
                gd.setNguoiCapNhat("ABC");
                gd.setNguoiTao("ABC");
                gd.setTrangThai(tt);
//                System.out.println(gd);
                giaoDichRepository.save(gd);
            }

            String message = "Xác nhận tất cả thành công";
            return ResponseEntity.ok(message);
        } else {
            String errorMessage = "Không tìm thấy hóa đơn chưa xác nhận";
            return ResponseEntity.notFound().build();
        }
    }
}
