package spring.edu.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ACCOUNT")
@Getter
@Setter
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer account_id;
	@Column(unique = true, nullable = false)
	private String account;
	private String password;
	
	
	/* 
	----------Id-----------------
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	--------Ma ID-------------
	@Column(name = "MaDV", nullable = false, unique = true)
	@NotBlank
	@Pattern(regexp = "^DV[0-9]{3}", message = "Ma Dich Vu format: DVxxx(x: 0-9)")
	private String maDV;
	
	-----------String------------
	@Column(name = "TenKH", nullable = false, columnDefinition = "nvarchar(255)")
	@NotBlank
	@Length(max = 255)
	private String tenKH;
	
	
	@Column(name = "TrangThai", nullable = false, columnDefinition = "nvarchar(255)")
	@NotBlank
	@Pattern(regexp = "^(ranh)|(ban)|(dung duoc)|(dang sua chua)$")
	@Length(max = 255)
	private String trangThai;
	
	----------Integer------------
	@Column(name = "SoLuong", nullable = false, columnDefinition = "int")
	@NotNull
	@Digits(integer = 10, fraction = 0)
	@Range(min = 1, max = 2147483647, message = "So luong dich vu su dung toi thieu: 1")
	private Integer soLuong;
	
	
	
	------------Double------------
	@Column(name = "DonGia", nullable = false, columnDefinition = "money")
	@NotNull
	@Digits(integer = 19, fraction = 4)
	@Range(min = 1000, message = "Don gia it nhat 1000 Dong")
	private double donGia;
	
	
	------------------Date time-----------------
	@Temporal(TemporalType.DATE)
	@Column(name = "NgayBatDauSuDung", nullable = false, columnDefinition = "date")
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ngayBatDauSuDung;
	
	
	@Column(name = "NgaySuDung", nullable = false, columnDefinition = "date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private LocalDate ngaySuDung;
	
	
	
	@Temporal(TemporalType.TIME)
	@Column(name = "GioBatDauSuDung", nullable = false, columnDefinition = "time(0)")
	@NotNull
	@DateTimeFormat(pattern = "HH:mm:ss")
	private Date gioBatDauSuDung;
	
	@Column(name = "GioSuDung", nullable = false, columnDefinition = "time(7)")
	@DateTimeFormat(pattern = "HH:mm:ss")
	@NotNull
	private LocalTime gioSuDung;
	
	---------------One to many----------------
	@OneToMany (mappedBy = "khachHang")
	private Set<SuDungDichVu> suDungDichVu;
	
	@OneToMany (mappedBy = "khachHang")
	private Set<SuDungMay> suDungMay;
	
	
	
	
	----------------Many to one---------------
	@ManyToOne
	@JoinColumn(name = "MaKH")
	private KhachHang khachHang;
	
	@ManyToOne
	@JoinColumn(name = "MaKH")
	private KhachHang khachHang;
	
	
	
	
	
	
	
	
	
	
	
	 * */
}
