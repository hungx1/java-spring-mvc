package spring.edu.models;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "SUDUNGDICHVU")
@Getter
@Setter
public class SuDungDichVu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "MaKH")
	private KhachHang khachHang;
	
	@ManyToOne
	@JoinColumn(name = "MaDV")
	private DichVu dichVu;
	
	@Column(name = "NgaySuDung", nullable = false, columnDefinition = "date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private LocalDate ngaySuDung;
	
	@Column(name = "GioSuDung", nullable = false, columnDefinition = "time(7)")
	@DateTimeFormat(pattern = "HH:mm:ss")
	@NotNull
	private LocalTime gioSuDung;
	
	@Column(name = "SoLuong", nullable = false, columnDefinition = "int")
	@NotNull
	@Digits(integer = 10, fraction = 0)
	@Range(min = 1, max = 2147483647, message = "So luong dich vu su dung toi thieu: 1")
	private Integer soLuong;
}
