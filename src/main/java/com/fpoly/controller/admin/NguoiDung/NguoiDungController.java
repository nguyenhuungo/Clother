package com.fpoly.controller.admin.NguoiDung;

import com.fpoly.entity.NguoiDung;
import com.fpoly.repository.NguoiDungRepository;
import com.fpoly.service.NguoiDungService;
import com.fpoly.service.SendMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.*;

@Controller
public class NguoiDungController {
    @Autowired
    NguoiDungService nguoiDungService;
    @Autowired
    NguoiDungRepository nguoiDungRepository;
    @Autowired
    private JavaMailSender mailSender;
    //List
    @GetMapping("/admin/NguoiDung")
    public String getUsers(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "4") int size,
            Model model
    ) {
        Page<NguoiDung> users = nguoiDungService.getAll(page - 1, size);
        System.out.println(users);
        model.addAttribute("users", users.getContent());
        model.addAttribute("totalPages", users.getTotalPages());
        return "admin/NguoiDung/list/NguoiDung";
    }
    //Add
    @RequestMapping("/NguoiDung/themMoi")
    public String themNguoiDung(Model model) {
        model.addAttribute("nguoiDung", new NguoiDung());
        return "admin/NguoiDung/crud/ThemNguoiDung";
    }
    private static final String digits = "0123456789";
    private static final String ALPHA_NUMERIC = digits;
    private static Random generator = new Random();

    public String randomMa(int soKyTu) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < soKyTu; i++) {
            int number = randomNumber(0, ALPHA_NUMERIC.length() - 1);
            char ch = ALPHA_NUMERIC.charAt(number);
            sb.append(ch);
        }
        return sb.toString();
    }

    public static int randomNumber(int min, int max) {
        return generator.nextInt((max - min) + 1) + min;
    }

    @PostMapping("/themMoi")
    public String addNguoiDung(@ModelAttribute("nguoiDung") NguoiDung nguoiDung, Model model) throws MessagingException {
        boolean isValid = true;

        // Check tên người dùng
        if (nguoiDung.getTenNguoiDung().isEmpty()) {
            model.addAttribute("msgName", "Không được để trống");
            isValid = false;
        }
        // Check email
        if (nguoiDung.getEmail().isEmpty()) {
            model.addAttribute("msgEmail", "Không được để trống");
            isValid = false;
        } else if (nguoiDungRepository.findByEmail(nguoiDung.getEmail()) != null) {
            model.addAttribute("msgEmail", "Email đã tồn tại");
            isValid = false;
        }

        // Check số điện thoại
        if (nguoiDung.getSoDienThoai().isEmpty()) {
            model.addAttribute("msgsodienthoai", "Không được để trống");
            isValid = false;
        } else if (nguoiDungRepository.findBysoDienThoai(nguoiDung.getSoDienThoai()) != null) {
            model.addAttribute("msgsodienthoai", "Số điện thoại đã tồn tại");
            isValid = false;
        }

        if (isValid) {
            Integer maxId = nguoiDungRepository.getMaxId();
            int id;
            String ma;

            if (maxId != null) {
                id = maxId + 1;
                ma = "NV" + id;
            } else {
                id = 1;
                ma = "NV" + id;
            }

            // Generate random code
            String randomCode = randomMa(6); // Change the number of characters as per your requirement

            // Lưu mã người dùng vào đối tượng nguoiDung
            nguoiDung.setMaNguoiDung(ma);
            nguoiDung.setDaXoa(false);
            nguoiDung.setNgayCapNhat(new Date());
            nguoiDungRepository.save(nguoiDung);

            try {
                sendEmailNotification(nguoiDung, randomCode);
            } catch (MessagingException e) {
                // Xử lý lỗi gửi email
                // You can add appropriate error handling code here
            }

            return "redirect:/admin/NguoiDung";
        } else {
            return "admin/NguoiDung/crud/ThemNguoiDung";
        }
    }

    private void sendEmailNotification(NguoiDung nguoiDung, String randomCode) throws MessagingException {
        String email = nguoiDung.getEmail();

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, false);

        helper.setFrom("datn.ud15@gmail.com");
        helper.setTo(email); // Địa chỉ email của người dùng
        helper.setSubject("Thông báo thêm thành công");
        helper.setText("Người dùng mới đã được thêm thành công.\nMật khẩu của bạn là: " + randomCode);

        mailSender.send(message);
    }

    //Update
    @RequestMapping("/chinhSua/{id}")
    public String chinhSuaNguoiDung(@PathVariable("id") Long id, Model model) {
        NguoiDung nguoiDung = nguoiDungService.getNguoiDungById(id);
        model.addAttribute("nguoiDungEdit", nguoiDung);
        return "admin/NguoiDung/crud/ChinhSuaNguoiDung";
    }
    @PostMapping("/chinhSua/save")
    public String editNguoiDung(@ModelAttribute("nguoiDungEdit") NguoiDung nguoiDungEdit, Model model) {
        boolean isValid = true;
        //Chenk tên người dùng
        if (nguoiDungEdit.getTenNguoiDung().isEmpty()) {
            model.addAttribute("msgName", "Không được để trống");
            isValid = false;
        }
        //Chenk eamil
        if (nguoiDungEdit.getEmail().isEmpty()) {
            model.addAttribute("msgEmail", "Không được để trống");
            isValid = false;
        }
        //Chenk sdt
        if (nguoiDungEdit.getSoDienThoai().isEmpty()) {
            model.addAttribute("msgSoDienThoai", "Không được để trống");
            isValid = false;
        }
        if (isValid) {
            NguoiDung existingNguoiDung = nguoiDungService.getNguoiDungById(nguoiDungEdit.getId());
            existingNguoiDung.setTenNguoiDung(nguoiDungEdit.getTenNguoiDung());
            existingNguoiDung.setEmail(nguoiDungEdit.getEmail());
            existingNguoiDung.setSoDienThoai(nguoiDungEdit.getSoDienThoai());
            nguoiDungService.saveNguoiDung(existingNguoiDung);
            return "redirect:/admin/NguoiDung";
        } else {
            return "admin/NguoiDung/crud/ChinhSuaNguoiDung";
        }
    }
    //Delete
    @RequestMapping("xoa/{id}")
    public String delete(Model model, @PathVariable("id") Long id) {
        Optional<NguoiDung> optionalNguoiDung = nguoiDungRepository.findById(id);
        if (optionalNguoiDung.isPresent()) {
            NguoiDung nguoiDung = optionalNguoiDung.get();
            nguoiDung.setDaXoa(true);
            nguoiDungRepository.save(nguoiDung);
        }
        model.addAttribute("listProducts", nguoiDungRepository.findAll());
        return "redirect:/admin/NguoiDung";
    }
    //Search
    @GetMapping("/admin/search")
    public String searchNguoiDung(@RequestParam("id") Long id, Model model) {
        Optional<NguoiDung> searchResult = nguoiDungRepository.findById(id);
        if (searchResult.isPresent()) {
            List<NguoiDung> resultList = new ArrayList<>();
            resultList.add(searchResult.get());
            model.addAttribute("listNguoiDungAll", resultList);
        } else {
            model.addAttribute("listNguoiDungAll", Collections.emptyList());
        }
        return "admin/NguoiDung/list/NguoiDung";
    }
    //Status
    @PostMapping("/updateStatus")
    public ResponseEntity<String> updateStatus(@RequestParam("userId") Long id, @RequestParam("status") int trangThai) {
        try {
            nguoiDungService.updateUserStatus(id, trangThai);
            return ResponseEntity.ok("Cập nhật trạng thái thành công");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Người dùng không tồn tại");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi cập nhật trạng thái");
        }
    }
}
