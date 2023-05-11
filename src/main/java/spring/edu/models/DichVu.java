package spring.edu.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "DICHVU")
@Getter
@Setter
public class DichVu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "MaDV", nullable = false, unique = true)
	@NotBlank
	@Pattern(regexp = "^DV[0-9]{3}", message = "Ma Dich Vu format: DVxxx(x: 0-9)")
	private String maDV;
	
	@Column(name = "TenDV", nullable = true, columnDefinition = "nvarchar(255)")
	@Length(max = 255)
	private String tenDV;
	
	@Column(name = "DonViTinh", nullable = false, columnDefinition = "varchar(50)")
	@NotBlank
	@Length(max=255)
	private String donViTinh;
	
	@Column(name = "DonGia", nullable = false, columnDefinition = "money")
	@NotNull
	@Digits(integer = 19, fraction = 4)
	@Range(min = 1000, message = "Don gia it nhat 1000 Dong")
	private double donGia;
	
	@OneToMany(mappedBy = "dichVu")
	private Set<SuDungDichVu> suDungDichVu;
}
