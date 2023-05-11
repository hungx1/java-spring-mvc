package spring.edu.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "KHACHHANG")
@Getter
@Setter
public class KhachHang {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name= "MaKH", nullable = false, unique = true)
	@NotBlank
	@Pattern(regexp = "^KH[0-9]{5}$", message = "MaKH format: KHxxxxx (x: 0-9)")
	private String maKH;
	
	@Column(name = "TenKH", nullable = false, columnDefinition = "nvarchar(255)")
	@NotBlank
	@Length(max = 255)
	private String tenKH;
	
	@Column(name = "DiaChi", nullable = true, columnDefinition = "nvarchar(255)")
	@Length(max = 255)
	private String diaChi;
	
	@Column(name = "SoDienThoai", nullable = true, columnDefinition = "varchar(14)")
	private String soDienThoai;
	
	@Column(name = "DiaChiEmail", nullable = true, columnDefinition = "varchar(255)")
	private String diaChiEmail;
	
	@OneToMany (mappedBy = "khachHang")
	private Set<SuDungDichVu> suDungDichVu;
	
	@OneToMany (mappedBy = "khachHang")
	private Set<SuDungMay> suDungMay;
}
