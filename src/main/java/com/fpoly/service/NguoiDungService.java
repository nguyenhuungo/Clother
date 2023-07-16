package com.fpoly.service;

import com.fpoly.entity.NguoiDung;
import com.fpoly.repository.INguoiDungPaginRespository;
import com.fpoly.repository.NguoiDungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class NguoiDungService {
    private static NguoiDungRepository nguoiDungRepository;
    private static Map<Integer, NguoiDung> nguoiDungMap;
    private final INguoiDungPaginRespository iNguoiDungPaginRespository;
    @Autowired
    public NguoiDungService(NguoiDungRepository nguoiDungRepository, INguoiDungPaginRespository iNguoiDungPaginRespository) {
        this.nguoiDungRepository = nguoiDungRepository;
        this.iNguoiDungPaginRespository = iNguoiDungPaginRespository;
    }

    public List<NguoiDung> getAllUsers() {
        return nguoiDungRepository.findAll();
    }

    public NguoiDung getEdit(Long id) {
        return nguoiDungRepository.findById(id).get();
    }

    public void edit(Integer id, NguoiDung productsEntity) {
        nguoiDungMap.put(id, productsEntity);
    }

    public void save(NguoiDung nguoiDung) {
        nguoiDungRepository.save(nguoiDung);
    }

    public Page<NguoiDung> getUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return iNguoiDungPaginRespository.findAll(pageable);
    }
    public Page<NguoiDung> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return iNguoiDungPaginRespository.GetAll(pageable);
    }
    public void saveNguoiDung(NguoiDung nguoiDung) {
        nguoiDungRepository.save(nguoiDung);
    }
    public NguoiDung getNguoiDungById(Long id) {
        if (id != null) {
            Optional<NguoiDung> optionalNguoiDung = nguoiDungRepository.findById(id);
            if (optionalNguoiDung.isPresent()) {
                return optionalNguoiDung.get();
            }
        }
        return null;
    }
    public void updateUserStatus(Long id, int trangThai) {
        NguoiDung user = nguoiDungRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Người dùng không tồn tại"));
        user.setTrangThai(trangThai);
        nguoiDungRepository.save(user);
    }
}
