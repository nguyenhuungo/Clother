package com.fpoly.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class KhuyenMaiDTO extends BaseDTO<KhuyenMaiDTO> {

    @NotBlank(message = "Tên không được để trống")
    private String tenKhuyenMai;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Ngày bắt đầu không được để trống")
    private Date ngayBatDau;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Ngày kết thúc không được để trống")
    private Date ngayKetThuc;

    @Min(value = 0, message = "Phần trăm không được nhỏ hơn 0")
    private int phanTramGiam;
    @Min(value = 0, message = "Giá trị không được nhỏ hơn 0")
    private int giaTriToiThieu;

    private boolean trangThai;
}
