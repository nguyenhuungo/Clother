package com.fpoly.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dia_chi")
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(callSuper=false)
public class DiaChi extends BaseEntity implements Serializable {

	@Column(name = "dia_chi",columnDefinition = "nvarchar(255) not null")
	private String diaChi;
	
	@Column(name="ho_ten")
	private String hoTen ; 
	
	@Column(name="so_dien_thoai")
	private String soDienThoai ;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="khach_hang_id")
	private KhachHang khachHang ;

}
