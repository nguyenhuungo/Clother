package com.fpoly.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ly_do_tra_hang")
public class LyDoTraHang extends BaseEntity implements Serializable {

    @Column(name = "ly_do", columnDefinition = "text")
    private String lyDo;

    @OneToMany(mappedBy = "lyDoTraHang", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<TraHang> traHang;

    @ManyToOne
    @JoinColumn(name = "hinh_anh_id", insertable = false, updatable = false)
    private HinhAnh hinhAnhTraHang;

    @Override
    public String toString() {
        return "LyDoTraHang{" +
                "lyDo='" + lyDo + '\'' +
                ", traHang=" + traHang.size() +
                ", hinhAnhTraHang=" + hinhAnhTraHang.getId() +
                '}';
    }
}
